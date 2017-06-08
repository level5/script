package com.level;

import com.level.ast.ASTLeaf;
import com.level.ast.ASTree;
import com.level.ast.BinaryExpr;
import com.level.ast.NumberLiteral;
import com.level.exceptions.ParseException;

import java.util.Arrays;

/**
 * Created by huangshif on 17-6-8.
 *
 * factor: Number | "(" expression ")"
 * term: factor { ("*" | "/") factor }
 * expression: term { ("+" | "-") term }
 */
public class ExprParser {

    private Lexer lexer;

    public ExprParser(Lexer p) {
        lexer = p;
    }

    public ASTree expression() throws ParseException {
        ASTree left = term();

        while (isToken("+") || isToken("-")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = term();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }

    private ASTree term() throws ParseException {
        ASTree left = factor();

        while (isToken("*") || isToken("/")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = factor();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }

    private ASTree factor() throws ParseException {
        if (isToken("(")) {
            token("(");
            ASTree e = expression();
            token(")");
            return e;
        }

        Token t = lexer.read();
        if (!t.isNumber()) {
            throw new ParseException(t);
        }
        NumberLiteral n = new NumberLiteral(t);
        return n;
    }

    private void token(String name) throws ParseException {
        Token t = lexer.read();
        if (!(t.isIdentifier() && name.equals(t.getText()))) {
            throw new ParseException(t);
        }
    }

    private boolean isToken(String name) throws ParseException {
        Token t = lexer.peek(0);
        return t.isIdentifier() && name.equals(t.getText());
    }
}
