package com.level;

import java.util.HashSet;

import com.level.Parser.Operators;
import com.level.ast.NumberLiteral;

import static com.level.Parser.rule;
import com.level.ast.*;

/**
 * Created by huangshif on 17-6-9.
 */
public class BasicParser {

    HashSet<String> reserved = new HashSet<>();

    Operators operators = new Operators();

    Parser expr0 = rule();

    Parser primary = rule(PrimaryExpr.class)
            .or(
                    rule().sep("(").ast(expr0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Name.class, reserved),
                    rule().string(StringLiteral.class)
            );

    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);
}
