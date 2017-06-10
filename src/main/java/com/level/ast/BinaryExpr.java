package com.level.ast;

import com.level.Environment;
import com.level.exceptions.StoneException;

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

    @Override
    public Object eval(Environment env) {
        String op = operator();
        if (op == "=") {
            Object right = right().eval(env);
            return computeAssign(env, right);
        }
        Object left = left().eval(env);
        Object right = right().eval(env);
        return computeOp(left, op, right);
    }

    private Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer)left, op, (Integer)right);
        } else {
            if (op.equals("+")) {
                return String.valueOf(left) + String.valueOf(right);
            } else if (op.equals("==")) {
                if (left == null) {
                    return right == null ? TRUE : FALSE;
                } else {
                    return left.equals(right) ? TRUE : FALSE;
                }
            } else {
                throw new StoneException("bad type", this);
            }
        }
    }

    private Object computeNumber(Integer left, String op, Integer right) {
        int a = left;
        int b = right;
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return a * b;
        } else if (op.equals("/")) {
            return a / b;
        } else if (op.equals("%")) {
            return a % b;
        } else if (op.equals("==")) {
            return a == b ? TRUE : FALSE;
        } else if (op.equals(">")) {
            return a > b ? TRUE : FALSE;
        } else if (op.equals("<")) {
            return a < b ? TRUE : FALSE;
        } else {
            throw new StoneException("bad operator", this);
        }
    }


    private Object computeAssign(Environment env, Object right) {
        ASTree l = left();
        if (l instanceof Name) {
            env.put(((Name)l).name(), right);
            return right;
        } else {
            throw new StoneException("bad assignment", this);
        }
    }

}
