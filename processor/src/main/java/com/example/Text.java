package com.example;

import com.example.annotation.Info;
import com.example.annotation.Person;

import java.lang.reflect.Method;

/**
 * 注解
 * Created by Longwj on 2017/7/11.
 */

public class Text {
    public static void main(String[] args) {
        System.out.println("method date:shishi");
        try {
            Class cls = Class.forName("com.example.MyClass");
            for (Method method : cls.getMethods()) {
                Info info = method.getAnnotation(Info.class);
                if (info != null) {
                    System.out.println("method name:" + info.name());
                    System.out.println("method date:" + info.age());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
