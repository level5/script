package com.level.ast;

import com.level.Environment;
import com.level.Token;
import com.level.exceptions.StoneException;

/**
 * Created by huangshif on 17-6-7.
 */
public class Name extends ASTLeaf{

    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token.getText();
    }

    @Override
    public Object eval(Environment env) {
        Object value = env.get(name());
        if (value == null) {
            throw new StoneException("undefined name: " + name(), this);
        }
        return value;
    }
}
