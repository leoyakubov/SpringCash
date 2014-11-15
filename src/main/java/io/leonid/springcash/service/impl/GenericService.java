package io.leonid.springcash.service.impl;

import io.leonid.springcash.dao.impl.GenericDAO;
import io.leonid.springcash.model.BaseEntity;
import io.leonid.springcash.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class GenericService<T extends BaseEntity> implements IService<T> {
    @Autowired
    protected GenericDAO<T> genericDAO;

    @Override
    @Transactional
    public List<T> findAll() {
        return genericDAO.findAll();
    }

    @Override
    @Transactional
    public T findByID(Long id) {
        return genericDAO.findByID(id);
    }

    @Override
    @Transactional
    public Long insert(T entity) {
        return genericDAO.insert(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return genericDAO.update(entity);
    }

    @Override
    @Transactional
    public List<T> insertOrUpdateMultipleEntities(List<T> entities) {
        return genericDAO.insertOrUpdateMultipleEntities(entities);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        genericDAO.delete(entity);
    }
}
