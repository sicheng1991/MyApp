package com.example.sof23.behavior.observer;

/**
 * Created by Longwj on 2017/8/23.
 */

public abstract class Observer {
    protected Subscriber subscriber;
    public abstract void update();
}
