package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.ModelMapperUtils;
import org.ccts.balancingact.model.api.BillingRule;
import org.ccts.balancingact.model.api.ProgramGroup;
import org.ccts.balancingact.model.api.Student;
import org.ccts.balancingact.model.db.AdministratorEntity;
import org.ccts.balancingact.model.db.ProgramGroupEntity;
import org.ccts.balancingact.model.db.ProgramGroupServiceEntity;
import org.ccts.balancingact.model.db.ProgramGroupStudentEntity;
import org.ccts.balancingact.model.db.ServiceTaskEntity;
import org.ccts.balancingact.model.db.StudentEntity;
import org.hibernate.criterion.DetachedCriteria;
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
public class ProgramDaoImpl implements ProgramDao {
    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

    @Override
    public List<ProgramGroup> getProgramGroups() {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProgramGroupEntity.class);
        criteria.addOrder(Order.asc("name"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), ProgramGroup.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProgramGroup addProgramGroup(ProgramGroup programGroup) {
        ProgramGroupEntity program = new ProgramGroupEntity();
        program.setName(programGroup.getName());
        program.setAdmin(sessionFactoryTemplate.findById(UUID.fromString(programGroup.getAdministrator().getId()), AdministratorEntity.class));
        sessionFactoryTemplate.save(program);

        return ModelMapperUtils.getInstance().map(program, ProgramGroup.class);
    }

    @Override
    public ProgramGroup getProgramGroup(UUID id) {
        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findById(id, ProgramGroupEntity.class), ProgramGroup.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeProgramGroup(final UUID id) {
        sessionFactoryTemplate.delete(sessionFactoryTemplate.load(id, ProgramGroupEntity.class));
    }

    @Override
    public List<Student> getProgramGroupStudents(UUID programGroupId) {
        DetachedCriteria programGroupStudentsCriteria = DetachedCriteria.forClass(ProgramGroupStudentEntity.class);
        programGroupStudentsCriteria.add(Restrictions.eq("programGroup.id", programGroupId));
        programGroupStudentsCriteria.setProjection(Projections.property("student.id"));

        DetachedCriteria criteria = DetachedCriteria.forClass(StudentEntity.class);
        criteria.add(Subqueries.propertyIn("id", programGroupStudentsCriteria));
        criteria.addOrder(Order.asc("lastName"));
        criteria.addOrder(Order.asc("firstName"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), Student.class);
    }

    @Override
    public List<Student> getEligibleProgramGroupStudents(UUID programGroupId) {
        DetachedCriteria programGroupStudentsCriteria = DetachedCriteria.forClass(ProgramGroupStudentEntity.class);
        programGroupStudentsCriteria.setProjection(Projections.property("student.id"));

        DetachedCriteria criteria = DetachedCriteria.forClass(StudentEntity.class);
        criteria.add(Subqueries.propertyNotIn("id", programGroupStudentsCriteria));
        criteria.addOrder(Order.asc("lastName"));
        criteria.addOrder(Order.asc("firstName"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), Student.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Student> setProgramGroupStudents(UUID programGroupId, List<Student> students) {
        final ProgramGroupEntity group = sessionFactoryTemplate.load(programGroupId, ProgramGroupEntity.class);
        students.stream().forEach(student -> {
            StudentEntity entity = sessionFactoryTemplate.load(UUID.fromString(student.getId()), StudentEntity.class);
            sessionFactoryTemplate.save(new ProgramGroupStudentEntity(group, entity));
        });

        return getProgramGroupStudents(programGroupId);
    }

    @Override
    public List<BillingRule> getProgramGroupBillingRules(UUID programGroupId) {
        DetachedCriteria programGroupBillingCriteria = DetachedCriteria.forClass(ProgramGroupServiceEntity.class);
        programGroupBillingCriteria.add(Restrictions.eq("programGroup.id", programGroupId));
        programGroupBillingCriteria.setProjection(Projections.property("serviceRule.id"));

        DetachedCriteria criteria = DetachedCriteria.forClass(ServiceTaskEntity.class);
        criteria.createAlias("service", "service");
        criteria.add(Subqueries.propertyIn("id", programGroupBillingCriteria));
        criteria.addOrder(Order.asc("service.name"));
        criteria.addOrder(Order.asc("name"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), BillingRule.class);
    }

    @Override
    public List<BillingRule> getEligibleProgramGroupBillingRules(UUID programGroupId) {
        DetachedCriteria programGroupBillingCriteria = DetachedCriteria.forClass(ProgramGroupServiceEntity.class);
        programGroupBillingCriteria.add(Restrictions.eq("programGroup.id", programGroupId));
        programGroupBillingCriteria.setProjection(Projections.property("serviceRule.id"));

        DetachedCriteria criteria = DetachedCriteria.forClass(ServiceTaskEntity.class);
        criteria.createAlias("service", "service");
        criteria.add(Subqueries.propertyNotIn("id", programGroupBillingCriteria));
        criteria.addOrder(Order.asc("service.name"));
        criteria.addOrder(Order.asc("name"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), BillingRule.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<BillingRule> setProgramGroupBillingRules(UUID programGroupId, List<BillingRule> billingRules) {
        final ProgramGroupEntity group = sessionFactoryTemplate.load(programGroupId, ProgramGroupEntity.class);
        billingRules.stream().forEach(billingRule -> {
            ServiceTaskEntity entity = sessionFactoryTemplate.load(UUID.fromString(billingRule.getId()), ServiceTaskEntity.class);
            sessionFactoryTemplate.save(new ProgramGroupServiceEntity(group, entity));
        });

        return getProgramGroupBillingRules(programGroupId);
    }
}
