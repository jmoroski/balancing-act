package org.ccts.balancingact.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.InvoiceReport;
import org.ccts.balancingact.model.api.InvoiceReportItem;
import org.ccts.balancingact.model.common.TaskFrequency;
import org.ccts.balancingact.model.db.ProgramGroupServiceEntity;
import org.ccts.balancingact.model.db.ProgramGroupStudentEntity;
import org.ccts.balancingact.model.db.ServiceTaskEntity;
import org.ccts.balancingact.model.db.ServiceTaskItemEntity;
import org.ccts.balancingact.model.db.StudentEntity;
import org.ccts.balancingact.model.db.UserInvoiceEntity;
import org.ccts.balancingact.model.db.UserInvoiceItemEntity;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class InvoiceDaoImpl implements InvoiceDao {
    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @PostConstruct
//    public void createInvoices() {
//        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                try {
//                    createScheduledInvoices(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 04, 30));
//                } catch(Exception e) {
//                    //do nothing
//                }
//            }
//        });
//    }


    @Override
    public List<InvoiceReport> generateProgramGroupInvoiceData(UUID programGroupId, LocalDate rangeStart, LocalDate rangeEnd) {
        DetachedCriteria programStudents = DetachedCriteria.forClass(ProgramGroupStudentEntity.class);
        programStudents.add(Restrictions.eq("programGroup.id", programGroupId));
        programStudents.setProjection(Projections.property("student.id"));

        DetachedCriteria userInvoicesCriteria = DetachedCriteria.forClass(UserInvoiceEntity.class);
        userInvoicesCriteria.createAlias("user", "user");
        userInvoicesCriteria.add(Restrictions.ge("invoiceDate", rangeStart));
        userInvoicesCriteria.add(Restrictions.le("invoiceDate", rangeEnd));
        userInvoicesCriteria.add(Subqueries.propertyIn("user.id", programStudents));
        userInvoicesCriteria.setFetchMode("items", FetchMode.SELECT);
        userInvoicesCriteria.addOrder(Order.asc("user.lastName"));
        userInvoicesCriteria.addOrder(Order.asc("user.firstName"));
        userInvoicesCriteria.addOrder(Order.asc("serviceName"));
        userInvoicesCriteria.addOrder(Order.asc("invoiceDate"));

        List<UserInvoiceEntity> entities = this.sessionFactoryTemplate.findByCriteria(userInvoicesCriteria);
        List<InvoiceReport> invoices = new ArrayList<>();
        for (UserInvoiceEntity entity : entities) {
            InvoiceReport invoice = new InvoiceReport();
            invoice.setClientName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());
            invoice.setCompanyName(entity.getServiceName());
            invoice.setCompanyAddress1(entity.getContactInfo().getAddress1());
            invoice.setCompanyAddress2(entity.getContactInfo().getAddress2());
            invoice.setCompanyCity(entity.getContactInfo().getCity());
            invoice.setCompanyState(entity.getContactInfo().getState());
            invoice.setCompanyZip(entity.getContactInfo().getZip());
            invoice.setInvoiceDate(entity.getInvoiceDate());
            invoice.setDueDate(invoice.getInvoiceDate().plusMonths(1L));
            invoice.setInvoiceNumber(generateInvoiceNumber(entity.getServiceName(), entity.getInvoiceDate(), entity.getId()));
            createItems(invoice, entity);
            invoices.add(invoice);
        }

        return invoices;
    }

    private String generateInvoiceNumber(final String serviceName, final LocalDate invoiceDate, final UUID invoiceId) {
        return serviceName.substring(0, 3).toUpperCase() + invoiceDate.getYear() + "-" + invoiceId.toString().substring(0, 4);
    }

    private void createItems(final InvoiceReport invoice, final UserInvoiceEntity entity) {
        BigDecimal total = BigDecimal.ZERO;
        invoice.setItems(new ArrayList<>());

        for (UserInvoiceItemEntity item : entity.getItems()) {
            InvoiceReportItem reportItem = new InvoiceReportItem(item.getItem().getDescription(),
                    item.getItem().getQuantity(), item.getItem().getRate(), item.getItem().getAmount());
            invoice.getItems().add(reportItem);
            total = total.add(item.getItem().getAmount());
        }

        invoice.setTotal(total);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createScheduledInvoices(final LocalDate rangeStart, final LocalDate rangeEnd) {
        List<ServiceTaskEntity> serviceRules = findCurrentServiceRules(rangeStart, rangeEnd);
        for (ServiceTaskEntity serviceRule : serviceRules) {
            List<LocalDate> invoiceDates = createInvoiceDates(serviceRule, rangeStart, rangeEnd);
            if (invoiceDates.isEmpty()) {
                continue;
            }

            List<StudentEntity> students = findApplicableStudents(serviceRule);
            for (LocalDate invoiceDate : invoiceDates) {
                createInvoices(serviceRule, invoiceDate, students);
            }
        }
    }

    private List<ServiceTaskEntity> findCurrentServiceRules(final LocalDate rangeStart, final LocalDate rangeEnd) {
        Disjunction rangeExclusion = Restrictions.disjunction();
        rangeExclusion.add(Restrictions.gt("serviceRule.startDate", rangeEnd));
        rangeExclusion.add(Restrictions.lt("serviceRule.endDate", rangeStart));

        DetachedCriteria criteria = DetachedCriteria.forClass(ProgramGroupServiceEntity.class);
        criteria.createAlias("serviceRule", "serviceRule");
        criteria.add(Restrictions.not(rangeExclusion));
        criteria.add(Restrictions.ne("serviceRule.frequency", TaskFrequency.None));
        criteria.setProjection(Projections.distinct(Projections.property("serviceRule")));
        criteria.setFetchMode("serviceRule.items", FetchMode.SELECT);

        return this.sessionFactoryTemplate.findByCriteria(criteria);
    }

    private List<StudentEntity> findApplicableStudents(ServiceTaskEntity serviceRule) {
        DetachedCriteria programGroupCriteria = DetachedCriteria.forClass(ProgramGroupServiceEntity.class);
        programGroupCriteria.add(Restrictions.eq("serviceRule.id", serviceRule.getId()));
        programGroupCriteria.setProjection(Projections.property("programGroup.id"));

        DetachedCriteria studentCriteria = DetachedCriteria.forClass(ProgramGroupStudentEntity.class);
        studentCriteria.add(Subqueries.propertyIn("programGroup.id", programGroupCriteria));
        studentCriteria.setProjection(Projections.property("student"));

        return this.sessionFactoryTemplate.findByCriteria(studentCriteria);
    }

    private void createInvoices(final ServiceTaskEntity serviceRule, LocalDate invoiceDate, List<StudentEntity> students) {
        for (StudentEntity student : students) {
            UserInvoiceEntity invoice = new UserInvoiceEntity(student, invoiceDate);
            invoice.setServiceName(serviceRule.getService().getName());
            invoice.setContactInfo(serviceRule.getService().getContactInfo());
            for (ServiceTaskItemEntity item : serviceRule.getItems()) {
                invoice.addItem(item.getItem());
            }

            this.sessionFactoryTemplate.save(invoice);
        }

    }

    private List<LocalDate> createInvoiceDates(final ServiceTaskEntity entity, final LocalDate rangeStart, final LocalDate rangeEnd) {
        List<LocalDate> dates = new ArrayList<>();

        LocalDate date = entity.getStartDate();
        while (date.isBefore(rangeStart)) {
            date = incrementDate(date, entity.getFrequency());
        }

        while (date.isBefore(rangeEnd) || date.equals(rangeEnd)) {
            dates.add(date);
            date = incrementDate(date, entity.getFrequency());
        }

        return dates;
    }

    private LocalDate incrementDate(LocalDate startDate, TaskFrequency period) {
        // supporting monthly and biweekly for now
        if (period == TaskFrequency.Monthly) {
            return startDate.plusMonths(1L);
        } else {
            return startDate.plusWeeks(2L);
        }
    }
}
