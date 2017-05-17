package com.example.chimu.myeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private Button button2;
    private String Msg;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = (Button) findViewById(R.id.button2);

        textView = (TextView) findViewById(R.id.msg);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                startActivity(intent);
            }
        });

        //订阅
        EventBus.getDefault().register(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }


/**
 * ThreadMode总共四个：
 NAIN UI主线程
 BACKGROUND 后台线程
 POSTING 和发布者处在同一个线程
 ASYNC 异步线程
 *
 * */
    //发布事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100)   //UI线程执行，优先级100，优先级和广播类似
    public void  onMyEvent(String  msg){

        textView.setText(msg);
        Log.i("msg","msg:"+msg);
        // EventBus.getDefault().cancelEventDelivery(myEvent) ;//优先级高的订阅者可以终止事件往下传递
    }


}
