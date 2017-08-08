package com.chimu.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chimu.myapp.common.BaseActivity;
import com.chimu.myapp.view.ScollView;
import com.chimu.mylib.activity.MyCameraActivity;
import com.chimu.mylib.util.AnnotationUtil;
import com.chimu.mylib.util.FileUtil;
import com.chimu.mylib.util.SPUtils;
import com.example.annotation.Person;

import java.io.File;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ll.addView(new ScollView(this));

    }

    //Arouter页面跳转
    public void OnClick(View v) {
//        aRouter();

        String s1 = AnnotationUtil.getPerson(this);
        SPUtils spUtils = new SPUtils(this, "tessssst");
        spUtils.put("text", "测试呢");
        String s = "";
        File f = new File(Environment.getDataDirectory().getPath() + "/data/" + this.getPackageName() + "/");
        FileUtil.copyPath(f, Environment.getExternalStorageDirectory());
        Toast.makeText(this, s1, Toast.LENGTH_SHORT).show();
    }

    private void aRouter() {
        //            a2. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
        ARouter.getInstance().build("/test/ActivityB").navigation();

//        2. 跳转并携带参数
        ARouter.getInstance().build("/test/ActivityB")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .withSerializable("key3", new Integer(101))
                .navigation();

        int x = 2;
        int y = 3;
        Toast.makeText(this, x + " X " + y + " = " + (x * y), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyCameraActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart:" + activityState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume:" + activityState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause:" + activityState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop:" + activityState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart:" + activityState);
    }
}

