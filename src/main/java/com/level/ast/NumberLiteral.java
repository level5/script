package com.level.ast;

import com.level.Environment;
import com.level.Token;

/**
 * Created by huangshif on 17-6-7.
 */
public class NumberLiteral extends ASTLeaf {

    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token.getNumber();
    }

    @Override
    public Object eval(Environment env) {
        return value();
    }
}
