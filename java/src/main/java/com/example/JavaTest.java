package com.example;

import com.example.bean.HostBean;
import com.example.jui.MenuDemo;
import com.example.util.FileUtil;
import com.example.util.Utils;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("main is run");
        HostBean bean = new HostBean();
        new MenuDemo();

        try {
//            Utils.SpeedDial2MD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


