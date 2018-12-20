package com.example.sof23.struct.bridge;

/**

 *
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new BuleCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}
