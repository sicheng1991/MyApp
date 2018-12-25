package com.chimu.kotlin.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Longwj on 2017/6/28.
 */

public class BActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //java中调用kt类
//        TextUtil.isEmpty("a");
        //companion object， 表示外部类的一个伴生对象，你可以把他理解为外部类自动创建了
        // 一个对象作为自己的field。与上面的类似，Java 在调用时，可以这样写：
//        StringUtils.Companion.isEmpty("1");

//        helloKotlin();
    }
}


