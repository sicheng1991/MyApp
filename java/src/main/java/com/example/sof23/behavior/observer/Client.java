package com.example.sof23.behavior.observer;

/**
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。
 * 比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式
 *
 * 关键代码：在抽象类里有一个 ArrayList 存放观察者们。
 * 优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。
 缺点： 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会
 花费很多时间。 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行
 循环调用，可能导致系统崩溃。 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是
 怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 *
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();

        new HexaObserver(subscriber);
        new OctalObserver(subscriber);
        new BinaryObserver(subscriber);

        System.out.println("First state change: 15");
        subscriber.setState(15);
        System.out.println("Second state change: 10");
        subscriber.setState(10);
    }
}
