package com.example.sof23.behavior.mediator;

/**

 * Created by Longwj on 2017/8/28.
 */

public class Client {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
