package com.level;

import com.level.exceptions.ParseException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by huangshif on 17-6-6.
 */
public class LexerTest {

    @Test
    public void testLexer() throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        Lexer l = new Lexer(new FileReader(classLoader.getResource("while.stone").getFile()));
        // TODO: 改成对比数组
        for (Token t ; (t = l.read())!= Token.EOF;) {
            System.out.println("=> " + t.getText());
        }
    }
}
