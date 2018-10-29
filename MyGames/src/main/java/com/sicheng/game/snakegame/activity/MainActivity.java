package com.sicheng.game.snakegame.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;

import com.sicheng.game.snakegame.R;
import com.sicheng.game.snakegame.util.StatusBarUtil;
import com.sicheng.game.snakegame.view.GestureSurfaceView;
import com.sicheng.game.snakegame.view.ImgScaleSurfaceView;
import com.sicheng.game.snakegame.view.MySurfaceView;
import com.sicheng.game.snakegame.view.MyView;



/**
 * Game
 * Created by yangzteL on 2018/10/25 0025.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
////        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GestureSurfaceView(this));
//        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black_383838));
        StatusBarUtil.setStatusBar(this,getResources().getColor(R.color.white),true);
    }
}
