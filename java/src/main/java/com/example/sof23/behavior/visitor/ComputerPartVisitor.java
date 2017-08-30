package com.example.sof23.behavior.visitor;

/**
 * Created by Longwj on 2017/8/30.
 */

public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
