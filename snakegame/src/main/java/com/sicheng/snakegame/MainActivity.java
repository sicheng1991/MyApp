package com.sicheng.snakegame;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sicheng.snakegame.util.MetricUtil;
import com.sicheng.snakegame.view.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_main;
    private List<Block> body;
    private List<Block> border;//边界
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
        body = new ArrayList<>();
        for (int i = 4; i >= 0; i--) {
            Block b = new Block(this, i * blockSize, blockSize);
            body.add(b);
            fl_main.addView(b);
        }
        timeContral();
    }

    private void setBorder() {
        border = new ArrayList<>();
        for (int i = 0; i < blockNum; i++) {
            for (int j = 0; j < blockNum; j++) {
                if (i == 0 || i == blockNum - 1 || j == 0 || j == blockNum - 1) {
                    Block block = new Block(this, i * blockSize, j * blockSize, Color.RED);
                    border.add(block);
                    fl_main.addView(block);
                }
            }
        }
    }
    Timer timer;
    private void timeContral() {
        timer = new Timer();
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
                float x = body.get(0).getX();
                float y = body.get(0).getY();
                Block newBlock = createNewBlock(x,y);
                if(check(newBlock)){
                    Block oldBlock = body.get(body.size() -1);
                    fl_main.addView(newBlock);
                    body.add(0, newBlock);
                    body.remove(oldBlock);
                    fl_main.removeView(oldBlock);
                }else{
                    Toast.makeText(MainActivity.this, "你失败了啊", Toast.LENGTH_SHORT).show();
                    timer.cancel();
                }

            }
        }
    };
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private int direction = 1;

    private void directionContral(int direction){
        this.direction = direction;
    }
    private Block createNewBlock(float oldX,float oldY){

        float newX = oldX;
        float newY = oldY;
        if(direction == UP){
            newY = oldY - blockSize;
        }else if(direction == DOWN){
            newY = oldY + blockSize;
        }else if(direction == RIGHT){
            newX = oldX + blockSize;
        }else if(direction == LEFT){
            newX = oldX - blockSize;
        }
        Block block = new Block(MainActivity.this, newX, newY);
        return block;
    }
    private boolean check(Block newBlock){
        for (Block block : body) {
            if(block.getY() == newBlock.getY() && block.getX() == newBlock.getX()){
                return false;
            }
        }
        for (Block block : border) {
            if(block.getY() == newBlock.getY() && block.getX() == newBlock.getX()){
                return false;
            }
        }
        return true;
    }

}
