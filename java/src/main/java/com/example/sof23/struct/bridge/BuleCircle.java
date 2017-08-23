package com.example.sof23.struct.bridge;

/**
 * Created by Longwj on 2017/8/23.
 */

public class BuleCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: Bule, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
