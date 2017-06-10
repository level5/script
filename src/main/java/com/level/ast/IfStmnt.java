package com.level.ast;

import com.level.Environment;

import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class IfStmnt extends ASTList {

    public IfStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree thenBlock() {
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    public Object eval(Environment env) {
        Object c = condition().eval(env);
        if (c instanceof Integer && ((Integer) c).intValue() != FALSE) {
            return thenBlock().eval(env);
        } else {
            ASTree b = elseBlock();
            if (b == null) {
                return 0;
            } else {
                return b.eval(env);
            }
        }
    }

    public String toString() {
        return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
    }
}
