package com.level;

import com.level.ast.ASTree;
import com.level.ast.NullStmnt;
import com.level.exceptions.ParseException;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by huangshi on 6/10/2017.
 */
public class BasicParserTest {

    ClassLoader classLoader = getClass().getClassLoader();
    Reader reader = new FileReader(classLoader.getResource("while.stone").getFile());

    public BasicParserTest() throws FileNotFoundException {
    }

    @Test
    public void testParser() throws FileNotFoundException, ParseException {
        Lexer l = new Lexer(reader);

        BasicParser bp = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }

    @Test
    public void testFuncParser() throws ParseException, FileNotFoundException {
        reader = new FileReader(classLoader.getResource("fib.stone").getFile());
        Lexer l = new Lexer(reader);

        FuncParser bp = new FuncParser();
        Environment env = new NestedEnv();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            if (!(ast instanceof NullStmnt)) {
                Object result = ast.eval(env);
                System.out.println("=> " + ast.toString() + ": " + result);
            }
        }
    }

    @Test
    public void testRun() throws ParseException {
        Lexer l = new Lexer(reader);
        Environment env = new BasicEnv();

        BasicParser bp = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            if (!(ast instanceof NullStmnt)) {
                Object result = ast.eval(env);
                System.out.println("=> " + ast.toString() + ": " + result);
            }
        }
    }
}
