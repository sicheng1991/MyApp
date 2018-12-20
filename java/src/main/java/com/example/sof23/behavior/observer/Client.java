package com.example.sof23.behavior.observer;

/**

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
