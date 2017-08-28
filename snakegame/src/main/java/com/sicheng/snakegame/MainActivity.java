package com.sicheng.snakegame;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.sicheng.snakegame.util.MetricUtil;
import com.sicheng.snakegame.view.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_main;
    private List<Block> blocks;
    private int blockSize;
    private int blockNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);

        init();
    }

    private void init() {
        blockNum = 48;
        blockSize = MetricUtil.getWindowWith(this) / blockNum;
        setBorder();
        blocks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Block b = new Block(this, i * blockSize, blockSize);
            blocks.add(b);
            fl_main.addView(b);
        }
        timeContral();
    }

    private void setBorder() {
        for (int i = 0; i < blockNum; i++) {
            for (int j = 0; j < blockNum; j++) {
                if (i == 0 || i == blockNum - 1 || j == 0 || j == blockNum - 1) {
                    fl_main.addView(new Block(this, i * blockSize, j * blockSize, Color.RED));
                }
            }
        }
    }

    private void timeContral() {
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(FLASH_VIEW);
            }
        };
        timer.schedule(tt,600,600);
    }

    private static final int FLASH_VIEW = 1001;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLASH_VIEW) {
                float x = blocks.get(0).getX();
                float y = blocks.get(0).getY();
                Block newBlock = new Block(MainActivity.this, x + blockSize, y);
                Block oldBlock = blocks.get(blocks.size() -1);
                fl_main.addView(newBlock);
                blocks.add(0, newBlock);
                blocks.remove(oldBlock);
                fl_main.removeView(oldBlock);
            }
        }
    };
}
