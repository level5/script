package com.level.ast;

import com.level.Token;

/**
 * Created by huangshif on 17-6-9.
 */
public class StringLiteral extends ASTLeaf {

    public StringLiteral(Token t) {
        super(t);
    }

    public String value() {
        return token.getText();
    }
}
