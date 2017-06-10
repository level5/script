package com.level.ast;

import com.level.Environment;

import java.util.Iterator;

/**
 * Created by huangshif on 17-6-7.
 */
public abstract class ASTree implements Iterable<ASTree> {

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public abstract Object eval(Environment env);

    public abstract  ASTree child(int i);

    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    public Iterator<ASTree> iterator() {
        return children();
    }
}
