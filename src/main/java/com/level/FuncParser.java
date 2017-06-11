package com.level;

import com.level.ast.Arguments;
import com.level.ast.DefStmnt;
import com.level.ast.ParameterList;

import static com.level.Parser.rule;

/**
 * Created by huangshi on 6/10/2017.
 */
public class FuncParser extends BasicParser {

    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class)
            .ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class)
            .sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Arguments.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        primary.insertChoice(def);
    }

}
