package com.example.sof23.struct.bridge;

/**
 * 桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模
 * 式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。
 这两种类型的类可被结构化改变而互不影响。
 我们通过下面的实例来演示桥接模式（Bridge Pattern）的用法。其中，可以使用相同的抽象类方法
 但是不同的桥接实现类，来画出不同颜色的圆。

 优点： 1、抽象和实现的分离。 2、优秀的扩展能力。 3、实现细节对客户透明。
 缺点：桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，
 要求开发者针对抽象进行设计与编程。
 * 桥接模式
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
