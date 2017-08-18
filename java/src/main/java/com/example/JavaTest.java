package com.example;

import com.example.jui.MenuDemo;
import com.example.util.Utils;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("main is run");




        new MenuDemo();

        try {
//            Utils.SpeedDial2MD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


