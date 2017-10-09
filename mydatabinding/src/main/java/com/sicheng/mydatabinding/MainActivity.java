package com.sicheng.mydatabinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sicheng.mydatabinding.bean.Student;
import com.sicheng.mydatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        Student student = new Student();
        student.setAge(18);
        student.setName("迟暮陌路");
        binding.setStudent(student);
    }
}
