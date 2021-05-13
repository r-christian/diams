package com.christian.diams.dao.base;

import java.util.List;

public interface IGenericDAO<T> {
    public List<T> findAll();

    public void update(T object);

    public T get(Long id);

    public void delete(T object);

    public void insert(T object);

    public boolean exists(Long id);
}
