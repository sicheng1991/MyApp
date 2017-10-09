package com.sicheng.mydatabinding.bean;

import android.databinding.Bindable;

/**
 * Created by Longwj on 2017/10/9.
 */

public class Student {

    public String name;

    public int age;

    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
