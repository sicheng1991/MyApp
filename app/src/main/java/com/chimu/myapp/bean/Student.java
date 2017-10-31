package com.chimu.myapp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Longwj on 2017/10/9.
 */

public class Student extends BaseObservable {
    @Bindable
    public String name;
    @Bindable
    public int age;
    @Bindable
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
