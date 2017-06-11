package com.level.ast;

import com.level.Environment;

import java.util.Iterator;
import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class PrimaryExpr extends ASTList {

    public PrimaryExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree operand() {
        return child(0);
    }

    public Postfix postfix(int nest) {
        return (Postfix)child(numChildren() - nest - 1);
    }

    public boolean hasPostfix(int nest) {
        return numChildren() - nest > 1;
    }

    public Object eval(Environment env) {
        return evalSubExpr(env, 0);
    }

    private Object evalSubExpr(Environment env, int nest) {
        if (hasPostfix(nest)) {
            Object target = evalSubExpr(env, nest + 1);
            return postfix(nest).eval(env, target);
        } else {
            return operand().eval(env);
        }
    }


    public static ASTree create(List<ASTree> list) {
        return list.size() == 1 ? list.get(0) : new PrimaryExpr(list);
    }
}
