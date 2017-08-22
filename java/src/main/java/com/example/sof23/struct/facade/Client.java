package com.example.sof23.struct.facade;

/**
 *
 * 外观模式：隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 * 这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 *
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
