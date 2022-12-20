package com.example.test.util;

public interface Generic<T> {

    T genericMethod();

    T get();

    void set(T param);

    void delete(T param);
}
