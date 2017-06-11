package com.level;

import java.util.HashMap;

/**
 * Created by huangshi on 6/11/2017.
 */
public class NestedEnv implements Environment {

    protected HashMap<String, Object> values;
    protected Environment outer;

    public NestedEnv() {
        this(null);
    }

    public NestedEnv(Environment e) {
        values = new HashMap<>();
        outer = e;
    }

    public void setOuter(Environment e) {
        outer = e;
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        e.putNew(name, value);

    }

    @Override
    public void putNew(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Environment where(String name) {
        if (values.get(name) != null) {
            return this;
        } else if (outer == null) {
            return null;
        } else {
            return outer.where(name);
        }
    }

    /**
     * 我在想着里如果变量的值就是null该怎么办？
     *
     * @param name
     * @return
     */
    @Override
    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null) {
            return outer.get(name);
        } else {
            return v;
        }
    }
}
