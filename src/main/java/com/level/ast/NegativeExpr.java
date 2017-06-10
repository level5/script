package com.level.ast;

import com.level.Environment;
import com.level.exceptions.StoneException;

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

    @Override
    public Object eval(Environment env) {
        Object v = operand().eval(env);
            if (v instanceof Integer) {
                return new Integer(-((Integer) v).intValue());
            } else {
                throw new StoneException("bad type for -", this);
            }
    }
}
