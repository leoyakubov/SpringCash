package io.leonid.springcash.service;

import io.leonid.springcash.dao.GenericDAO;
import io.leonid.springcash.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by leonid on 14.08.14.
 */
@Service
public class GenericService<T extends BaseEntity> {
    @Autowired
    protected GenericDAO<T> genericDAO;

    @Transactional
    public List<T> findAll() {
        return genericDAO.findAll();
    }

    @Transactional
    public T findByID(Integer id) {
        return genericDAO.findByID(id);
    }

    @Transactional
    public T insertOrUpdate(T entity) {
        return genericDAO.insertOrUpdate(entity);
    }

    @Transactional
    public void delete(T entity) {
        genericDAO.delete(entity);
    }
}
