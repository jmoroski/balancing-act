package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.db.Persistable;
import org.hibernate.criterion.DetachedCriteria;

public interface SessionFactoryTemplate {
    <T extends Persistable> void delete(T entity);
    <T> List<T> findByCriteria(DetachedCriteria criteria);
    <T> T findUniqueByCriteria(DetachedCriteria criteria);
    <T extends Persistable> List<T> findAll(Class<T> entityClass);
    <T extends Persistable> T findById(UUID id, Class<T> entityClass);
    <T extends Persistable> T load(UUID id, Class<T> entityClass);
    <T extends Persistable> void save(T entity);
}
