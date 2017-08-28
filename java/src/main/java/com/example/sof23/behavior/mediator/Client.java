package com.example.sof23.behavior.mediator;

/**
 * 中介者模式
 * 意图：用一个中介对象来封装一系列的对象交互，中介者使各对象
 * 不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 我们通过聊天室实例来演示中介者模式。实例中，多个用户可以向聊天室发送消息，聊天室
 * 向所有的用户显示消息。我们将创建两个类 ChatRoom 和 User。User 对象使用 ChatRoom
 * 方法来分享他们的消息。
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
