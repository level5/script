package com.level.ast;

import com.level.Environment;
import com.level.ast.ASTree;
import com.level.exceptions.StoneException;

import java.util.List;

/**
 * Created by huangshi on 6/11/2017.
 */
public class Arguments extends Postfix {

    public Arguments(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment callerEnv, Object value) {
        if (!(value instanceof Function)) {
            throw new StoneException("bad function", this);
        }
        Function func = (Function) value;
        ParameterList params = func.parameters;
        if (size() != params.size()) {
            throw new StoneException("bad number of arguments", this);
        }
        Environment newEnv = func.makeEnv();
        int num = 0;
        for(ASTree a: this) {
            params.eval(newEnv, num++, a.eval(callerEnv));
            return func.body().eval(newEnv);
        }
        return null;
    }

    public int size() {
        return numChildren();
    }
}
