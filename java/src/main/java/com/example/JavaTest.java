package com.example;

import com.example.bean.HostBean;
import com.example.bean.SingleInstance;
import com.example.jui.MenuDemo;
import com.example.util.FileUtil;
import com.example.util.Utils;
import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("main is run");
        HostBean bean = new HostBean();

        SingleInstance singleton = SingleInstance.getSingleton();
        System.out.println("counter1="+singleton.counter1);
        System.out.println("counter2="+singleton.counter2);

        try {
//            Utils.SpeedDial2MD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


