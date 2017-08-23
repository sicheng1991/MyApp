package com.example.sof23.struct.bridge;

/**
 * Created by Longwj on 2017/8/23.
 */

public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
