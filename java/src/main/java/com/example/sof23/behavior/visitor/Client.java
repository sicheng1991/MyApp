package com.example.sof23.behavior.visitor;

/**

 * Created by Longwj on 2017/8/30.
 */

public class Client {

    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
