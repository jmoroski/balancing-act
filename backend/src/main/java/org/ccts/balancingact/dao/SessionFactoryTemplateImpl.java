package org.ccts.balancingact.dao;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.ccts.balancingact.model.db.Persistable;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public class SessionFactoryTemplateImpl implements SessionFactoryTemplate {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <T extends Persistable> void delete(T entity) {
        Objects.nonNull(entity);

        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public <T> List<T> findByCriteria(DetachedCriteria criteria) {
        Objects.nonNull(criteria);

        @SuppressWarnings("unchecked")
        List<T> results = criteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();

        return results;
    }

    @Override
    public <T> T findUniqueByCriteria(DetachedCriteria criteria) {
        Objects.nonNull(criteria);

        @SuppressWarnings("unchecked")
        T result = (T) criteria.getExecutableCriteria(sessionFactory.getCurrentSession()).uniqueResult();

        return result;
    }

    @Override
    public <T extends Persistable> List<T> findAll(Class<T> entityClass) {
        Objects.nonNull(entityClass);

        return findByCriteria(DetachedCriteria.forClass(entityClass));
    }

    @Override
    public <T extends Persistable> T findById(UUID id, Class<T> entityClass) {
        Objects.nonNull(id);
        Objects.nonNull(entityClass);

        return entityClass.cast(sessionFactory.getCurrentSession().get(entityClass, id));
    }

    @Override
    public <T extends Persistable> T load(UUID id, Class<T> entityClass) {
        Objects.nonNull(id);
        Objects.nonNull(entityClass);

        return entityClass.cast(sessionFactory.getCurrentSession().load(entityClass, id));
    }

    @Override
    public <T extends Persistable> void save(T entity) {
        Objects.nonNull(entity);

        sessionFactory.getCurrentSession().persist(entity);
    }
}
