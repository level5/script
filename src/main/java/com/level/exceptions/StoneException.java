package com.level.exceptions;

import com.level.ast.ASTList;
import com.level.ast.ASTree;

/**
 * Created by huangshif on 17-6-6.
 */
public class StoneException extends RuntimeException {
    public StoneException(String s) {
        super(s);
    }

    public StoneException(String msg, ASTree t) {
        super(msg + " " + t.location());
    }
}
