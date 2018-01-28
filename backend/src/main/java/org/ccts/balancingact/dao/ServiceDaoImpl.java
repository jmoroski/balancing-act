package org.ccts.balancingact.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.ModelMapperUtils;
import org.ccts.balancingact.model.api.BankAccountTransaction;
import org.ccts.balancingact.model.api.BillingRule;
import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.Service;
import org.ccts.balancingact.model.db.BankAccountEntity;
import org.ccts.balancingact.model.db.BankAccountTransactionEntity;
import org.ccts.balancingact.model.db.ServiceEntity;
import org.ccts.balancingact.model.db.ServiceTaskEntity;
import org.ccts.balancingact.model.db.ServiceTaskItemEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Service> getServices() {
        DetachedCriteria criteria = DetachedCriteria.forClass(ServiceEntity.class);
        criteria.addOrder(Order.asc("name"));
        List<ServiceEntity> results = sessionFactoryTemplate.findByCriteria(criteria);

        Type apiType = (new ArrayList<Service>()).getClass();
        return ModelMapperUtils.getInstance().map(results, apiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Service getService(final UUID id) {
        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findById(id, ServiceEntity.class), Service.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BillingRule> getBillingRules(UUID serviceId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ServiceTaskEntity.class);
        criteria.add(Restrictions.eq("service.id", serviceId));
        criteria.addOrder(Order.asc("name"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), BillingRule.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public BillingRule getBillingRule(UUID billingRuleId) {
        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findById(billingRuleId, ServiceTaskEntity.class), BillingRule.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BillingRule addBillingRule(BillingRule billingRule) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        ServiceTaskEntity entity = mapper.map(billingRule, ServiceTaskEntity.class);
        entity.setService(sessionFactoryTemplate.findById(UUID.fromString(billingRule.getServiceId()), ServiceEntity.class));
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, BillingRule.class);
    }

    @Override
    public BillingRuleItem addBillingRuleItem(UUID billingRuleId, BillingRuleItem billingRuleItem) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        ServiceTaskItemEntity entity = mapper.map(billingRuleItem, ServiceTaskItemEntity.class);
        entity.setServiceTask(sessionFactoryTemplate.load(billingRuleId, ServiceTaskEntity.class));
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, BillingRuleItem.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BillingRule updateBillingRule(BillingRule billingRule) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        ServiceTaskEntity entity = sessionFactoryTemplate.findById(UUID.fromString(billingRule.getId()), ServiceTaskEntity.class);
        mapper.map(billingRule, entity);

        return billingRule;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeBillingRule(UUID id) {
        sessionFactoryTemplate.delete(sessionFactoryTemplate.load(id, ServiceTaskEntity.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Service addService(Service service) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        ServiceEntity entity = mapper.map(service, ServiceEntity.class);
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, Service.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Service updateService(Service service) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        ServiceEntity entity = sessionFactoryTemplate.findById(UUID.fromString(service.getId()), ServiceEntity.class);
        mapper.map(service, entity);

        return service;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeService(UUID id) {
        this.sessionFactoryTemplate.delete(sessionFactoryTemplate.load(id, ServiceEntity.class));
    }
}
