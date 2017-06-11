package com.level.ast;

import com.level.Environment;

import java.util.List;

/**
 * Created by huangshi on 6/11/2017.
 */
public class DefStmnt extends ASTList {

    public DefStmnt(List<ASTree> list) {
        super(list);
    }

    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList) child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    public Object eval(Environment env) {
        env.putNew(name(), new Function(parameters(), body(), env));
        return name();
    }

    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }
}
