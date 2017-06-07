package com.level.ast;

import java.util.Iterator;

/**
 * Created by huangshif on 17-6-7.
 */
public abstract class ASTree implements Iterable<ASTree> {

    public abstract  ASTree child(int i);

    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    public Iterator<ASTree> iterator() {
        return children();
    }
}
