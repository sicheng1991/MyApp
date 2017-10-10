package com.sicheng.mydatabinding.bean;

import android.databinding.ObservableField;

/**
 *
 * Created by Longwj on 2017/10/10.
 */

public class Student1 {
    public static ObservableField<String> name = new ObservableField<>();
    public Student1(String name){
        this.name.set(name);
    }
}
