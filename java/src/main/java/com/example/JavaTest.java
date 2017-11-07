package com.example;

import com.example.arithmetic.Evaluate;
import com.example.bean.HostBean;
import com.example.bean.SingleInstance;
import com.example.jui.MenuDemo;
import com.example.util.FileUtil;
import com.example.util.NumberUtil;
import com.example.util.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("main is run");

//        SingleInstance singleton = SingleInstance.getSingleton();
//        System.out.println("counter1="+singleton.counter1);
//        System.out.println("counter2="+singleton.counter2);


//        System.out.println(word);

//        String s = NumberUtil.getWord(5397639874L);
//        System.out.println("s:" + s);

        NumberUtil.getReasrch();

//        System.out.println("输入 :");
//        Scanner scan = new Scanner(System.in);
//        String read = scan.nextLine();

        try {
//            Utils.SpeedDial2MD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


