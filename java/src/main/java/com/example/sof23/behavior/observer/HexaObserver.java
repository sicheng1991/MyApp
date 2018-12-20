package com.example.sof23.behavior.observer;

/**
 * Created by Longwj on 2017/8/23.
 */

public class HexaObserver extends Observer {
    public HexaObserver(Subscriber subscriber){
        this.subscriber = subscriber;
        this.subscriber.attach(this);
    }

    @Override
    public void update() {
        System.out.println( " String: "
                + Integer.toHexString( subscriber.getState() ) );
    }
}
