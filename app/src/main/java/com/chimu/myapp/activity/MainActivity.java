package com.chimu.myapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chimu.myapp.R;
import com.chimu.myapp.hook.hook.AMSHookUtil;
import com.chimu.myapp.hook.hook.HookInstrumentationUtil;
import com.chimu.myapp.opengl.MyOpenGLActivity;
import com.chimu.myapp.view.HenCoderView;
import com.chimu.mylib.util.Bencode;
import com.chimu.mylib.util.BitmapUtil;

import com.chimu.mylib.util.bt.Torrent;
import com.example.annotation.Person;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity {
    private Animation animation;
    private ImageView   iv;
    private Button button;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
       iv = (ImageView) findViewById(R.id.img);
        button = (Button) findViewById(R.id.btn_commit);

        HenCoderView henCoderView = new HenCoderView(this);

        henCoderView.animate().translationX(300).setDuration(2000).rotation(1080).scaleX(3);

        ll.addView(henCoderView);


        HookInstrumentationUtil.hook();

    }



    public void OnClick(View v) {
//        AMSHookUtil.hookStartActivity(this);
        Intent intent = new Intent(MainActivity.this, MyOpenGLActivity.class);
        startActivity(intent);

    }

    private  void getInfo(LinkedHashMap map){
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            Log.i("MainActivity",key+":"+ map.get(key));
            if(key.equalsIgnoreCase("info")){
                LinkedHashMap map1 = (LinkedHashMap) map.get(key);

                Iterator iterator1 = map1.keySet().iterator();
                while (iterator1.hasNext()){
                    String key1 = (String) iterator1.next();
                    Log.i("MainActivity",key1+":"+ map1.get(key1));
                    if(key1.equals("pieces")){
                        try {
                            String s = new String(((String) map1.get(key1)).getBytes(),"GBK");

                            Log.i("MainActivity","解析:"+ s);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }



            }
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

