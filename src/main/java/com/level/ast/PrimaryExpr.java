package com.level.ast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by huangshi on 6/9/2017.
 */
public class PrimaryExpr extends ASTList {

    public PrimaryExpr(List<ASTree> list) {
        super(list);
    }

    public static ASTree create(List<ASTree> list) {
        return list.size() == 1 ? list.get(0) : new PrimaryExpr(list);
    }
}
