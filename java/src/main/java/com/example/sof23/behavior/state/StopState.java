package com.example.sof23.behavior.state;

/**
 * Created by Longwj on 2017/8/30.
 */

public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}
