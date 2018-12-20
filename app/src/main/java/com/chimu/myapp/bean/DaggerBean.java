package com.chimu.myapp.bean;


import javax.inject.Inject;

import dagger.Component;

/**
 * Created by yangzteL on 2018/12/19 0019.
 */


public class DaggerBean {

    @Inject
    public DaggerBean(){

    }

    private String name;
    private int age;

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
}
