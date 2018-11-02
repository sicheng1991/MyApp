package com.sicheng.game.snakegame.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sicheng.game.snakegame.R;
import com.sicheng.game.snakegame.box2d.Box2DSurfaceView;
import com.sicheng.game.snakegame.util.StatusBarUtil;




/**
 * Game
 * Created by yangzteL on 2018/10/25 0025.
 */

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new Box2DSurfaceView(this));

//        setContentView(new TextView(this));
//        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black_383838));
        StatusBarUtil.setStatusBar(this,getResources().getColor(R.color.white),true);
//        StatusBarUtil.setTranslucentForImageView(this,255,null);
//        StatusBarUtil.setDarkMode(this);

    }
}


