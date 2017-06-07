package com.level.ast;

import java.util.List;

/**
 * Created by huangshif on 17-6-7.
 */
public class BinaryExpr extends ASTList {

    public BinaryExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree left() {
        return children.get(0);
    }

    public ASTree right() {
        return children.get(2);
    }

    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }

}
