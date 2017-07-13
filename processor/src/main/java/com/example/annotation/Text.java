package com.example.annotation;

import java.lang.reflect.Method;

/**
 * Created by Longwj on 2017/7/11.
 */

public class Text {
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("cn.trinea.java.test.annotation.App");
            for (Method method : cls.getMethods()) {
                Person person = method.getAnnotation(Person.class);
                if (person != null) {
                    System.out.println("method name:" + person.name());
                    System.out.println("method date:" + person.age());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
