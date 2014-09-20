package io.leonid.springcash.dao;

import io.leonid.springcash.model.BaseEntity;

import java.util.List;

/**
 * Created by leonid on 20.09.14.
 */
public interface IDAO<T extends BaseEntity> {
    List<T> findAll();

    T findByID(Integer id);

    T insertOrUpdate(T entity);

    void delete(T entity);
}
