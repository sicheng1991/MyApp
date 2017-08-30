package com.example.sof23.behavior.memento;

/**
 * Created by Longwj on 2017/8/30.
 */

public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
