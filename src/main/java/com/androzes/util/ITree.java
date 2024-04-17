package com.androzes.util;

public interface ITree<T> {

    void insert(T object);

    boolean delete(T object);

    boolean exists(T object);
    
}
