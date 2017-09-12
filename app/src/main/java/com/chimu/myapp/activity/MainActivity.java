package com.chimu.myapp.activity;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chimu.myapp.R;
import com.chimu.myapp.common.EvilInstrumentation;
import com.chimu.myapp.common.MessagerService;
import com.chimu.myapp.common.aidl.LocalService;
import com.chimu.myapp.common.aidl.RomoteService;
import com.chimu.mylib.util.BitmapUtil;
import com.example.annotation.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity {
    private Animation animation;
    private ImageView   iv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
       iv = (ImageView) findViewById(R.id.img);
        button = (Button) findViewById(R.id.btn_commit);
        Bitmap bitmap =  BitmapFactory.decodeResource(this.getResources(),R.mipmap.c1);
        iv.setImageBitmap(BitmapUtil.getRoundBitmap(bitmap,10,true,false,false,true));



        hook();

//        双进程拉起
//        Intent i1 = new Intent(this, LocalService.class);
//        startService(i1);
//        Intent i2 = new Intent(this, RomoteService.class);
//        startService(i2);
    }

    private void hook() {
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);


            // 拿到原始的 mInstrumentation字段
            Field mInstrumentationField = activityThreadClass.getField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

            // 创建代理对象
            Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);

            // 偷梁换柱
            mInstrumentationField.set(currentActivityThread, evilInstrumentation);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public void OnClick(View v) {
        startActivity(new Intent(this,TestLockActivity.class));
//        startAndBindService();
    }
    Intent service;
    private void startAndBindService() {
        service = new Intent(MainActivity.this, MessagerService.class);
        startService(service);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    public final static String TAG = "MainActivity";
    public final static int ACTIVITYID = 0X0002;
    //客户端的Messnger
    private Messenger aMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == ACTIVITYID) {
                //客户端接受服务端传来的消息
                Log.d(TAG, "服务端传来了消息=====>>>>>>>");
                String str = (String) msg.getData().get("content");
                Log.d(TAG, str);
            }
        }
    });

    //服务端传来的Messenger
    Messenger sMessenger;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sMessenger = new Messenger(service);

            Message message = Message.obtain();
            message.arg1 = 0x0001;
            //注意这里，把`Activity`的`Messenger`赋值给了`message`中，当然可能你已经发现这个就是`Service`中我们调用的`msg.replyTo`了。
            message.replyTo = aMessenger;

            Bundle bundle = new Bundle();
            bundle.putString("content", "我就是Activity传过来的字符串");
            message.setData(bundle);

            try {
                //消息从客户端发出
                sMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "连接Service失败");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

