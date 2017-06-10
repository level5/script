package com.level.ast;

import com.level.Environment;

import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class BlockStmnt extends ASTList {

    public BlockStmnt(List<ASTree> list) {
        super(list);
    }

    public Object eval(Environment env) {
        Object result = 0;
        for (ASTree t: this) {
            if (!(t instanceof NullStmnt)) {
                result = t.eval(env);
            }
        }
        return result;
    }

}
