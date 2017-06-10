package com.level.ast;

import com.level.Environment;

import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class WhileStmnt extends ASTList {

    public WhileStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree body() {
        return child(1);
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for(;;) {
            Object c = condition().eval(env);
            if (c instanceof Integer && ((Integer) c).intValue() == FALSE) {
                return result;
            } else {
                result = body().eval(env);
            }
        }
    }

    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
}
