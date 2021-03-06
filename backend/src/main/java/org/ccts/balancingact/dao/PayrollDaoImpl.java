package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.ModelMapperUtils;
import org.ccts.balancingact.model.api.PayrollCompany;
import org.ccts.balancingact.model.api.PayrollRule;
import org.ccts.balancingact.model.api.PayrollRuleItem;
import org.ccts.balancingact.model.db.PayrollEntity;
import org.ccts.balancingact.model.db.PayrollTaskEntity;
import org.ccts.balancingact.model.db.PayrollTaskItemEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PayrollDaoImpl implements PayrollDao {
    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

    @Override
    public List<PayrollCompany> getPayrollCompanies() {
        DetachedCriteria criteria = DetachedCriteria.forClass(PayrollEntity.class);
        criteria.addOrder(Order.asc("name"));
        List<PayrollEntity> results = sessionFactoryTemplate.findByCriteria(criteria);

        return ModelMapperUtils.mapList(results, PayrollCompany.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PayrollCompany addPayrollCompany(PayrollCompany payrollCompany) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        PayrollEntity entity = mapper.map(payrollCompany, PayrollEntity.class);
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, PayrollCompany.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PayrollCompany updatePayrollCompany(PayrollCompany payrollCompany) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        PayrollEntity entity = sessionFactoryTemplate.findById(UUID.fromString(payrollCompany.getId()), PayrollEntity.class);
        mapper.map(payrollCompany, entity);

        return payrollCompany;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removePayrollCompany(UUID id) {
        this.sessionFactoryTemplate.delete(sessionFactoryTemplate.load(id, PayrollEntity.class));

    }

    @Override
    public PayrollCompany getPayrollCompany(UUID id) {
        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findById(id, PayrollEntity.class), PayrollCompany.class);
    }

    @Override
    public List<PayrollRule> getPayrollRules(UUID payrollCompanyId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PayrollTaskEntity.class);
        criteria.addOrder(Order.asc("name"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), PayrollRule.class);
    }

    @Override
    public PayrollRule getPayrollRule(UUID payrollRuleId) {
        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findById(payrollRuleId, PayrollTaskEntity.class), PayrollRule.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PayrollRule addPayrollRule(PayrollRule payrollRule) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        PayrollTaskEntity entity = mapper.map(payrollRule, PayrollTaskEntity.class);
        entity.setPayroll(sessionFactoryTemplate.findById(UUID.fromString(payrollRule.getPayrollId()), PayrollEntity.class));
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, PayrollRule.class);
    }

    @Override
    public PayrollRule updatePayrollRule(PayrollRule payrollRule) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PayrollRuleItem addPayrollRuleItem(UUID payrollRuleId, PayrollRuleItem payrollRuleItem) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        PayrollTaskItemEntity entity = mapper.map(payrollRuleItem, PayrollTaskItemEntity.class);
        entity.setPayrollTask(sessionFactoryTemplate.load(payrollRuleId, PayrollTaskEntity.class));
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, PayrollRuleItem.class);
    }

    @Override
    public void removePayrollRule(UUID id) {
        // TODO Auto-generated method stub

    }
}
