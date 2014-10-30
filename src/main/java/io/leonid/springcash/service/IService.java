package io.leonid.springcash.service;

import io.leonid.springcash.model.BaseEntity;

import java.util.List;

/**
 * Created by leonid on 20.09.14.
 */
public interface IService<T extends BaseEntity> {
    List<T> findAll();

    T findByID(Integer id);

    T insertOrUpdate(T entity);

    List<T> insertOrUpdateMultipleEntities(List<T> entities);

    void delete(T entity);
}
