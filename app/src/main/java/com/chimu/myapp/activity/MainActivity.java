package com.chimu.myapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chimu.myapp.R;
import com.chimu.myapp.hook.hook.AMSHookUtil;
import com.chimu.myapp.hook.hook.HookInstrumentationUtil;
import com.chimu.myapp.opengl.MyOpenGLActivity;
import com.chimu.myapp.view.HenCoderView;
import com.chimu.mylib.util.Bencode;
import com.chimu.mylib.util.BitmapUtil;

import com.chimu.mylib.util.TryCatchUtil;
import com.chimu.mylib.util.bt.Torrent;
import com.chimu.mylib.widget.RuleView;
import com.example.annotation.Person;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final int PERMISSION_REQUEST_CODE = 10001;
    private Animation animation;
    private ImageView iv;
    private Button button;
    private HorizontalScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);
        LinearLayout ll = (LinearLayout) findViewById(R.id.scollview1);
//        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
//        iv = (ImageView) findViewById(R.id.img);
//        button = (Button) findViewById(R.id.btn_commit);
        scrollView = (HorizontalScrollView) findViewById(R.id.scollview);
            findViewById(R.id.tv_animation).setOnClickListener(this);
//        String s = null;
//        s.contains("1");

        TryCatchUtil.doCatch(new TryCatchUtil.iTryCatch() {
            @Override
            public void ETry() {
                String s = null;
                s.contains("1");
            }

            @Override
            public void ECatch() {
                Log.i("MainActivity","ECatch:"+"报错来了啊");
            }

            @Override
            public void EFinally() {
                Log.i("MainActivity","ECatch:"+"报错来了啊111");
            }
        });
//        ll.addView(new RuleView(this));

        HookInstrumentationUtil.hook();


        //


//        List<TextView> list = new ArrayList<EditText>();

//        List<? extends TextView> list1 = new ArrayList<EditText>();
//        List<? super TextView> list2 = new ArrayList<View>();


    }


    public void OnClick(View v) {
        AMSHookUtil.hookStartActivity(this);
        Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
        startActivity(intent);

//        check();
//
    }

    private void check() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermissionsrp()) {
                // 对于高于或等于23的API代码导向装置
                // 执行具体逻辑
            } else {
                requestPermissionsrp(); //代码去执行权限申请
            }
        } else {
            //面向API的23以下设备的代码
            // 执行具体逻辑
        }
    }

    private boolean checkPermissionsrp() {
        int result2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);
        if (result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermissionsrp() {
        boolean permissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE);
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_PHONE_STATE)) {
            new AlertDialog.Builder(this)
                    .setMessage("跳转到设置页面")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent); // 打开系统设置界面
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "权限允许，您可以继续执行");
                } else {
                    Log.e("value", "权限被拒绝，您不能继续执行。");
                }
                break;
        }
    }


    private void getInfo(LinkedHashMap map) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Log.i("MainActivity", key + ":" + map.get(key));
            if (key.equalsIgnoreCase("info")) {
                LinkedHashMap map1 = (LinkedHashMap) map.get(key);

                Iterator iterator1 = map1.keySet().iterator();
                while (iterator1.hasNext()) {
                    String key1 = (String) iterator1.next();
                    Log.i("MainActivity", key1 + ":" + map1.get(key1));
                    if (key1.equals("pieces")) {
                        try {
                            String s = new String(((String) map1.get(key1)).getBytes(), "GBK");

                            Log.i("MainActivity", "解析:" + s);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_animation:
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
        }
    }
}

