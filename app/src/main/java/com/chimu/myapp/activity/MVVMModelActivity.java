package com.chimu.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chimu.myapp.bean.Student;

/**
 * Created by Longwj on 2017/10/9.
 */

public class MVVMModelActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Student person = new Student();
        person.setName("迟暮陌路");
        person.setPassword("123456");
        person.setAge(18);
//        binding.setPerson(person);
    }
}
