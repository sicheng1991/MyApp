package com.example.sof23.behavior.chainofresponsibility;

/**
 * Created by Longwj on 2017/8/25.
 */

public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
