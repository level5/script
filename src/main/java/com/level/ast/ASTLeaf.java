package com.level.ast;

import com.level.Environment;
import com.level.Token;
import com.level.exceptions.StoneException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangshif on 17-6-7.
 */

public class ASTLeaf extends ASTree {

    private static List<ASTree> empty = Collections.<ASTree>emptyList();

    protected Token token;

    public ASTLeaf(Token t) {
        token = t;
    }

    @Override
    public Object eval(Environment env) {
        throw new StoneException("cannot eval: " + toString(), this);
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumer();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token token() {
        return token;
    }
}
