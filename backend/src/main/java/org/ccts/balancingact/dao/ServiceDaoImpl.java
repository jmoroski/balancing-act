package org.ccts.balancingact.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.Service;
import org.ccts.balancingact.model.db.ServiceEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
        return new ModelMapper().map(results, apiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Service addService(Service service) {
        ModelMapper mapper = new ModelMapper();
        ServiceEntity entity = mapper.map(service, ServiceEntity.class);
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, Service.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Service updateService(Service service) {
        ModelMapper mapper = new ModelMapper();
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
