package com.level;

import com.level.ast.ASTree;
import com.level.ast.NullStmnt;
import com.level.exceptions.ParseException;

import java.io.Reader;

/**
 * Created by huangshi on 6/10/2017.
 */
public class BasicInterpreter {

    public static void run(BasicParser bp, Environment env, Reader reader) throws ParseException {

        Lexer lexer = new Lexer(reader);

        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
