package com.sicheng.mydatabinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sicheng.mydatabinding.bean.Student;
import com.sicheng.mydatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        final Student student = new Student();
        student.setAge(40);
        student.setName("迟暮陌路");
        binding.setStudent(student);
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName("sicheng");
            }
        });
    }
}
