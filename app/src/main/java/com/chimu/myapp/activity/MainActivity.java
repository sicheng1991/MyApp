package com.chimu.myapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.chimu.myapp.R;
import com.chimu.myapp.hook.hook.HookInstrumentationUtil;
import com.chimu.mylib.activity.Camera2Activity;
import com.chimu.mylib.base.BaseActivity;

import com.chimu.mylib.common.RxjavaOperator;
import com.chimu.mylib.util.Rxjava;
import com.example.annotation.Person;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

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

        findViewById(R.id.tv_animation).setOnClickListener(this);
        findViewById(R.id.tv_camera2).setOnClickListener(this);



        HookInstrumentationUtil.hook();

    }

    @Override
    public int contentView() {
        return R.layout.activity_main;
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
                break;
            case R.id.tv_camera2:
                test();

//
//                Intent intent1 = new Intent(MainActivity.this, Camera2Activity.class);
//                startActivity(intent1);
                break;

        }
    }

    private void test() {
        RxjavaOperator.test();
    }
}

