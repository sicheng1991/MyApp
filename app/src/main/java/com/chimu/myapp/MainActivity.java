package com.chimu.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chimu.mylib.activity.MyCameraActivity;
import com.chimu.mylib.util.AnnotationUtil;
import com.example.annotation.Person;

@Person(name = "龙文江", age = 35)
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity",
            "onCreate(MainActivity.java:20)"+"");

    }

    //Arouter页面跳转
    public void OnClick(View v) {
//            a2. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//        ARouter.getInstance().build("/test/ActivityB").navigation();

////        2. 跳转并携带参数
//        ARouter.getInstance().build("/test/ActivityB")
//                .withLong("key1", 666L)
//                .withString("key3", "888")
//                .withSerializable("key3", new Integer(101))
//                .navigation();

//        int x = 2;
//        int y = 3;
//        Toast.makeText(this,x +" X " + y +" = " + (x * y),Toast.LENGTH_SHORT).show();
//        Intent intent =  new Intent(this, MyCameraActivity.class);
//        startActivity(intent);
        String s = AnnotationUtil.getPerson(this);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}

