package com.example.sof23.behavior.state;

/**
 * Created by Longwj on 2017/8/30.
 */

public class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
