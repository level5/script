package com.level;

import com.level.ast.ASTLeaf;
import com.level.ast.ASTree;
import com.level.ast.BinaryExpr;
import com.level.ast.NumberLiteral;
import com.level.exceptions.ParseException;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by huangshif on 17-6-8.
 *
 * factor: Number | "(" expression ")"
 * term: factor { ("*" | "/") factor }
 * expression: term { ("+" | "-") term }
 *
 */
public class OpPrecedenceParser {

    private Lexer lexer;

    protected HashMap<String, Precedence> operators;

    public static class Precedence {
        int value;
        boolean leftAssoc;

        public Precedence(int v, boolean a) {
            value = v;
            leftAssoc = a;
        }
    }

    public OpPrecedenceParser(Lexer p) {
        lexer = p;

        operators = new HashMap<>();
        operators.put("<", new Precedence(1, true));
        operators.put(">", new Precedence(1, true));
        operators.put("+", new Precedence(2, true));
        operators.put("-", new Precedence(2, true));
        operators.put("*", new Precedence(3, true));
        operators.put("/", new Precedence(3, true));
        operators.put("^", new Precedence(4, false));
    }

    public ASTree expression() throws ParseException {
        ASTree right = factor();

        Precedence next;
        while((next = nextOperator()) != null) {
            right = doShift(right, next.value);
        }
        return right;
    }

    private ASTree doShift(ASTree left, int prec) throws ParseException {
        ASTLeaf op = new ASTLeaf(lexer.read());
        ASTree right = factor();
        Precedence next;
        while ((next = nextOperator()) != null && rightIsExpr(prec, next)) {
            right = doShift(right, next.value);
        }
        return new BinaryExpr(Arrays.asList(left, op, right));
    }

    private ASTree factor() throws ParseException {
        if (isToKen("(")) {
            token("(");
            ASTree e = expression();
            token("(");
            return e;
        }

        Token t = lexer.read();
        if (!t.isNumber()) {
            throw new ParseException(t);
        }
        return new NumberLiteral(t);
    }

    private void token(String name) throws ParseException {
        Token t = lexer.read();
        if (!(t.isIdentifier() && name.equals(t.getText()))) {
            throw new ParseException(t);
        }
    }

    private boolean isToKen(String name) throws ParseException {
        Token t = lexer.peek(0);
        return t.isIdentifier() && name.equals(t.getText());
    }

    private boolean rightIsExpr(int prec, Precedence next) {
        if (next.leftAssoc) {
            return prec < next.value;
        } else {
            return prec <= next.value;
        }
    }

    private Precedence nextOperator() throws ParseException {
        Token t = lexer.peek(0);
        if (t.isIdentifier()) {
            return operators.get(t.getText());
        } else {
            return null;
        }
    }
}
