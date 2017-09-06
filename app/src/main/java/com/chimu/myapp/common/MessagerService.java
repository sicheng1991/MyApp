package com.chimu.myapp.common;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chimu.myapp.activity.MainActivity;

/**
 * Created by Longwj on 2017/9/6.
 */

public class MessagerService extends Service {


    public final static String TAG = "MyService";

    public final static int SERVICEID = 0x0001;
    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == SERVICEID) {
                //接受从客户端传来的消息
                Log.d(TAG, "客服端传来的消息===>>>>>>");
                String str = (String) msg.getData().get("content");
                Log.d(TAG, str);

                //发送数据给客户端
                Message msgTo = Message.obtain();
                msgTo.arg1 = 0X0002;
                Bundle bundle = new Bundle();
                bundle.putString("content", "我是从服务器来的字符串");
                msgTo.setData(bundle);
                try {
                    //注意，这里把数据从服务器发出了
                    msg.replyTo.send(msgTo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreat");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    //客户端
//    Intent service;
//    private void startAndBindService() {
//        service = new Intent(MainActivity.this, MessagerService.class);
//        startService(service);
//        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
//    }
//
//
//    public final static String TAG = "MainActivity";
//    public final static int ACTIVITYID = 0X0002;
//    //客户端的Messnger
//    private Messenger aMessenger = new Messenger(new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.arg1 == ACTIVITYID) {
//                //客户端接受服务端传来的消息
//                Log.d(TAG, "服务端传来了消息=====>>>>>>>");
//                String str = (String) msg.getData().get("content");
//                Log.d(TAG, str);
//            }
//        }
//    });
//
//    //服务端传来的Messenger
//    Messenger sMessenger;
//    ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            sMessenger = new Messenger(service);
//
//            Message message = Message.obtain();
//            message.arg1 = 0x0001;
//            //注意这里，把`Activity`的`Messenger`赋值给了`message`中，当然可能你已经发现这个就是`Service`中我们调用的`msg.replyTo`了。
//            message.replyTo = aMessenger;
//
//            Bundle bundle = new Bundle();
//            bundle.putString("content", "我就是Activity传过来的字符串");
//            message.setData(bundle);
//
//            try {
//                //消息从客户端发出
//                sMessenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            Log.e(TAG, "连接Service失败");
//        }
//    };
}

