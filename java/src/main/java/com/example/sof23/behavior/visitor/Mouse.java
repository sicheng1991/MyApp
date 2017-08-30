package com.example.sof23.behavior.visitor;

/**
 * Created by Longwj on 2017/8/30.
 */

public class Mouse  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

