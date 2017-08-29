package com.sicheng.snakegame;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sicheng.snakegame.util.MetricUtil;
import com.sicheng.snakegame.view.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout fl_main;
    private List<Block> body;
    private List<Block> border;//边界
    private List<Block> eat;//吃的
    private Button btn_up;
    private Button btn_down;
    private Button btn_right;
    private Button btn_left;
    private Button btn_begin;
    private Button btn_reset;
    private TextView tv_len;
    private int blockSize;
    private int blockNum;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        init();

    }

    private void initView() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_begin = (Button) findViewById(R.id.btn_begin);
        tv_len = (TextView) findViewById(R.id.tv_len);

        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_begin.setOnClickListener(this);
    }

    private void init() {
        isRunning = true;
        direction = 1;
        blockNum = 48;
        blockSize = MetricUtil.getWindowWith(this) / blockNum;
        setBorder();
        body = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            Block b = new Block(this, i * blockSize, blockSize);
            body.add(b);
            fl_main.addView(b);
        }

        eat = new ArrayList<>();
        Block eatBlock = creatEatThing();
        eat.add(eatBlock);
        fl_main.addView(eatBlock);
        Block eatBlock1 = creatEatThing();
        eat.add(eatBlock1);
        fl_main.addView(eatBlock1);
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
//    Timer timer;
    private void timeContral() {
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(FLASH_VIEW);
            }
        };
        timer.schedule(tt,400);
    }


    private static final int FLASH_VIEW = 1001;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLASH_VIEW) {
                float x = body.get(0).getX();
                float y = body.get(0).getY();
                Block newBlock = createNewBlock(x,y);
                if(isEat(newBlock)){
                    Block oldBlock = body.get(body.size() -1);
                    fl_main.addView(newBlock);
                    body.add(0, newBlock);
                    tv_len.setText("长度：" + body.size());
//                    body.remove(oldBlock);
//                    fl_main.removeView(oldBlock);
                    if(isRunning){
                        timeContral();
                    }
                }else if(check(newBlock)){
                    Block oldBlock = body.get(body.size() -1);
                    fl_main.addView(newBlock);
                    body.add(0, newBlock);
                    body.remove(oldBlock);
                    fl_main.removeView(oldBlock);
                    if(isRunning){
                        timeContral();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "你失败了", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private int direction = 1;

    private void directionContral(int direction1){
        if((direction1 == UP || direction1 == DOWN) && (direction == UP || direction == DOWN)){
            return;
        }
        if((direction1 == RIGHT || direction1 == LEFT) && (direction == RIGHT || direction == LEFT)){
            return;
        }
        this.direction = direction1;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_up:
                directionContral(UP);
                break;
            case R.id.btn_down:
                directionContral(DOWN);
                break;
            case R.id.btn_left:
                directionContral(LEFT);
                break;
            case R.id.btn_right:
                directionContral(RIGHT);
                break;
            case R.id.btn_begin:
                isRunning = !isRunning;
                if(isRunning){
                    timeContral();
                }
                break;
            case R.id.btn_reset:
                    reset();
                break;

        }
    }

    /**
     *
     * @return
     */
    private  Block creatEatThing(){
        Block eatBlock = null;
        do{
            int x = (int) ((Math.random() * 47294723) % blockNum);
            int y = (int) ((Math.random() * 28774869) % blockNum);
            Log.i("msggggg", "creatEatThing: "+x + ":" + y);
            eatBlock = new Block(this,x * blockSize,y * blockSize,Color.GREEN);
        }while (!check(eatBlock));
        return eatBlock;
    }
    private boolean isEat(Block block){
        for(Block b : eat){
            if(b.getY() == block.getY() && b.getX() == block.getX()){
                fl_main.removeView(b);
                Block bb = creatEatThing();
                eat.add(bb);
                fl_main.addView(bb);
                return true;
            }
        }
        return false;
    }
    private void reset(){
        fl_main.removeAllViews();
        init();
    }
}
