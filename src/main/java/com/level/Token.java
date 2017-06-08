package com.level;

import com.level.exceptions.StoneException;

/**
 * Created by huangshif on 17-6-6.
 */
public abstract class Token {

    public static final Token EOF = new Token(-1) {};

    public static final String EOL = "\\n";

    public int lineNumer;

    protected Token(int line) {
        this.lineNumer = line;
    }

    public int getLineNumer() {
        return lineNumer;
    }

    public boolean isIdentifier() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public int getNumber() {
        throw new StoneException("not number exception");
    }

    public String getText() {
        return "";
    }
}
