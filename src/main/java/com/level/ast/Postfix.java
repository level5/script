package com.level.ast;

import com.level.Environment;

import java.util.List;

/**
 * Created by huangshi on 6/11/2017.
 */
public abstract class Postfix extends ASTList {

    public Postfix(List<ASTree> list) {
        super(list);
    }

    public abstract Object eval(Environment env, Object value);

}
