package com.example.bean;

import sun.rmi.runtime.Log;

/**
 * Created by Longwj on 2017/9/18.
 */


public class SingleInstance {
    private static SingleInstance singleton = new SingleInstance();

    public static int counter1;
    public static int counter2 = 0;

    private SingleInstance() {
        counter1++;
        counter2++;
        System.out.println("Ö´ÐÐ");
    }

    public static SingleInstance getSingleton() {
        return singleton;
    }
}
