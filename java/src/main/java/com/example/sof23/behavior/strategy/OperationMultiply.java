package com.example.sof23.behavior.strategy;

/**
 * Created by Longwj on 2017/8/29.
 */

public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
