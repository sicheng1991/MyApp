package com.example.chimu.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.chimu.kotlin.util.StringUtils;
import com.example.chimu.kotlin.util.TextUtil;

import static com.example.chimu.kotlin.util.TextUtilKt.helloKotlin;

/**
 * Created by Longwj on 2017/6/28.
 */

public class BActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //java中调用kt类
        TextUtil.isEmpty("a");
        //companion object， 表示外部类的一个伴生对象，你可以把他理解为外部类自动创建了
        // 一个对象作为自己的field。与上面的类似，Java 在调用时，可以这样写：
        StringUtils.Companion.isEmpty("1");

        helloKotlin();
    }
}


