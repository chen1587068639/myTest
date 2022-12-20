package com.example.test.util;

public class GenericImpl<T> implements Generic<T> {

    private T param;

    @Override
    public T genericMethod() {
        return null;
    }

    @Override
    public T get() {
        return param;
    }

    @Override
    public void set(T param) {
        this.param = param;
    }

    @Override
    public void delete(T param) {
        this.param = null;
    }
}
