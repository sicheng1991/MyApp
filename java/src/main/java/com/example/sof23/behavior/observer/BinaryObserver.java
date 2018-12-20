package com.example.sof23.behavior.observer;

/**
 * Created by Longwj on 2017/8/23.
 */

public class BinaryObserver extends Observer {
    public BinaryObserver(Subscriber subject){
        this.subscriber = subject;
        this.subscriber.attach(this);
    }

    @Override
    public void update() {
        System.out.println( " String: "
                + Integer.toBinaryString( subscriber.getState() ) );
    }
}
