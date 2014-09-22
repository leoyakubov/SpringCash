package io.leonid.springcash.dao.impl;

import io.leonid.springcash.dao.IDAO;
import io.leonid.springcash.model.BaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leonid on 14.08.14.
 */
@Repository
public class GenericDAO<T extends BaseEntity> implements IDAO<T> {
    protected Class<T> persistentClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public GenericDAO() {
    }

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(persistentClass).list();
    }

    @Override
    public T findByID(final Integer id) {
        if (id == null) {
            return null;
        }

        T entity = (T) sessionFactory.getCurrentSession().get(persistentClass, id);

        return entity;
    }

    @Override
    public T insertOrUpdate(final T entity) {
        if (entity.getId() == null) {
            Integer id = (Integer) sessionFactory.getCurrentSession().save(entity);
            entity.setId(id);

            return entity;
        } else {
            sessionFactory.getCurrentSession().merge(entity);

            return entity;
        }
    }

    @Override
    public void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
