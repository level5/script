package com.level;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Created by huangshi on 6/9/2017.
 */
public class BasicEnv implements Environment {

    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<>();
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }

    @Override
    public void putNew(String name, Object value) {
        throw new NotImplementedException();
    }

    @Override
    public Environment where(String name) {
        throw new NotImplementedException();
    }

    @Override
    public void setOuter(Environment e) {
        throw new NotImplementedException();
    }
}
