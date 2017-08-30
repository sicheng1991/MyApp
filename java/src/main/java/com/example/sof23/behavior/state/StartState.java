package com.example.sof23.behavior.state;

/**
 * Created by Longwj on 2017/8/30.
 */

public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
