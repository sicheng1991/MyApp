package com.example.sof23.behavior.chainofresponsibility;

/**
 * Created by Longwj on 2017/8/25.
 */

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
