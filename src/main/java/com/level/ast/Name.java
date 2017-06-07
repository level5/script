package com.level.ast;

import com.level.Token;

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
}
