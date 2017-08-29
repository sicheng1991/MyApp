package com.example.sof23.behavior.strategy;

/**
 * Created by Longwj on 2017/8/29.
 */

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
