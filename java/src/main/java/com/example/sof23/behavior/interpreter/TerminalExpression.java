package com.example.sof23.behavior.interpreter;

/**
 * Created by Longwj on 2017/8/30.
 */

public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
