package com.level.ast;

import com.level.Environment;
import com.level.NestedEnv;

/**
 * Created by huangshi on 6/11/2017.
 */
public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;

    public Function(ParameterList parameters, BlockStmnt body, Environment env) {
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmnt body() {
        return body;
    }

    public Environment makeEnv() {
        return new NestedEnv(env);
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
