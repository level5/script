package com.level.exceptions;

import com.level.Token;

import java.io.IOException;

/**
 * Created by huangshif on 17-6-6.
 */
public class ParseException extends Exception {

    public ParseException(Token t) {
        this("", t);
    }

    public ParseException(String msg, Token t) {
        super("syntax error around " + location(t));
    }

    private static String location(Token t) {
        if (t == Token.EOF) {
            return "the last line";
        } else {
            return "\"" + t.getText() + "\" at line " + t.getLineNumer();
        }
    }

    public ParseException(IOException e) {
        super(e);
    }

    public ParseException(String msg) {
        super(msg);
    }
}
