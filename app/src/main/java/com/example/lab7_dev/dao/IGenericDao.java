package com.example.lab7_dev.dao;

import java.util.List;

public interface IGenericDao<T> {
    boolean insert(T object);
    boolean modify(T object);
    boolean remove(T object);
    T searchById(int id);
    List<T> retrieveAll();
}