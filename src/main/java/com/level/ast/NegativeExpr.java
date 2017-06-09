package com.level.ast;

import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class NegativeExpr extends ASTList {

    public NegativeExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree operand() {
        return child(0);
    }

    public String toString() {
        return "-" + operand();
    }
}
