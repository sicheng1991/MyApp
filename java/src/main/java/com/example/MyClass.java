package com.example;

import com.example.util.FileUtil;
import com.example.util.Utils;
import com.sun.jmx.mbeanserver.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("main is run");
//        File file = new File("chimu.txt");
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            Utils.SpeedDial2MD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


