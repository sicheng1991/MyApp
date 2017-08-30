package com.example.sof23.behavior.visitor;

/**
 * Created by Longwj on 2017/8/30.
 */

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
