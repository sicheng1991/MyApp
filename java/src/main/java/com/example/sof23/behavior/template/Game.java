package com.example.sof23.behavior.template;

/**
 * Created by Longwj on 2017/8/29.
 */

public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();


    public final void play(){

        initialize();

        startPlay();

        endPlay();
    }
}
