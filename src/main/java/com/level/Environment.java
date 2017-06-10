package com.level;

/**
 * Created by huangshi on 6/9/2017.
 */
public interface Environment {
    void put(String name, Object value);
    Object get(String name);
}
