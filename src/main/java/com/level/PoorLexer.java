package com.level;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by huangshi on 6/7/2017.
 */
public class PoorLexer {

    private Reader reader;

    private static final int EMPTY = -1;

    private int lastChar = EMPTY;

    public PoorLexer(Reader r) {
        reader = r;
    }

    private int getChar() throws IOException {
        if (lastChar == EMPTY) {
            return reader.read();
        } else {
            int c = lastChar;
            lastChar = EMPTY;
            return c;
        }
    }

    private void ungetChar(int c) {
        lastChar = c;
    }

    /**
     *
     * 处理的结构是 \s*([0-9][0-9]*|[A-Za-z][A-Za-z0-9]*|=|==)
     *
     *
     * @return
     * @throws IOException
     */
    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        // 处理 \s*
        do {
            c = getChar();
        } while(isSpace(c));

        if (c < 0) {
            return null; // end of text
        }

        // 处理 == 和 =
        if (c == '=') {
            c = getChar();
            if (c == '=') {
                return "==";
            } else {
                ungetChar(c);
                return "=";
            }
        }


        if (isDigit(c)) {           // 处理 [0-9][0-9]*
            do {
                sb.append((char)c);
                c = getChar();
            } while (isDigit(c));
        } else if (isLetter(c)) {   // 处理 [A-Za-Z][A-Za-z0-9]*
            do {
                sb.append((char)c);
                c = getChar();
            } while (isLetter(c) || isDigit(c));
        } else {
            throw new IOException();
        }

        if (c >= 0) {
            ungetChar(c);
        }
        return sb.toString();
    }

    private boolean isSpace(int c) {
        return 0 <= c && c <= ' ';
    }

    private boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private boolean isLetter(int c) {
        return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
    }
}
