package com.example.chimu.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HUMISI on 2017/2/24.
 */

public class OtherActivity extends AppCompatActivity implements View.OnClickListener{
    private String Msg;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                //发布事件
                EventBus.getDefault().post("这是从Activiti2发送的事件消息");

                //发送粘性事件
                //   EventBus.getDefault().postSticky(new MyEvent());
                // 对于粘性广播我们都比较清楚属于常驻广播，对于EventBus粘性事件也类似，我们如果不再需要该粘性事件我们可以移除
                //   EventBus.getDefault().removeStickyEvent(new MyEvent());
                // 或者调用移除所有粘性事件
                //   EventBus.getDefault().removeAllStickyEvents();

                break;
            case R.id.button2:
                finish();
                break;
        }
    }
}
