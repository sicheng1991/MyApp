package com.chimu.myapp;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.annotation.Person;

@Person(name = "龙文江", age = 35)
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AssetManager assetManager = getAssets();








    }


    //Arouter页面跳转
    public void OnClick(View v) {
//            1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//        ARouter.getInstance().build("/test/ActivityB").navigation();

////        2. 跳转并携带参数
//        ARouter.getInstance().build("/test/ActivityB")
//                .withLong("key1", 666L)
//                .withString("key3", "888")
//                .withSerializable("key3", new Integer(101))
//                .navigation();
        Toast.makeText(this,"成功了成功了成功了",Toast.LENGTH_SHORT).show();
    }

}

