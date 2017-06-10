package com.level;

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
}
