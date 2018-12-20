package com.example.sof23.struct.facade;

/**

 * Created by Longwj on 2017/8/21.
 */

public class Client {
   public static void main(String[] a){
       ShapeMaker shapeMaker = new ShapeMaker();

       shapeMaker.drawCircle();
       shapeMaker.drawRectangle();
       shapeMaker.drawSquare();
   }
}
