package com.sicheng.game.snakegame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sicheng.game.snakegame.R;

/**
 * 操纵杆
 * Created by yangzteL on 2018/10/26 0026.
 */

public class OperaSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    private static final int GAME_MENU = 0;//菜单
    private static final int GAMEING = 1;//游戏中
    private static final int GAME_STOP = -1;//暂停
    private static final int GAME_WIN = 2;//胜利
    private static final int GAME_LOST = 3;//失败


    private Thread thread;
    private boolean flag;
    private Canvas canvas;
    private Bitmap bitmapBg;
    private float smallCenterX;
    private float smallCenterY;
    private int angel;
    private float bigCenterX = 400;
    private int bigCenterY = 400;
    private float bigCenterR = 250;


    public OperaSurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        initGame();

        thread = new Thread(this);
        thread.start();
    }

    private void initGame() {
        flag = true;
        bitmapBg = BitmapFactory.decodeResource(getResources(), R.mipmap.plane_bg);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("MySurfaceView","surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        Log.i("MySurfaceView","surfaceDestroyed");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            smallCenterX = bigCenterX;
            smallCenterY = bigCenterY;
        }else {
            int pointX = (int) event.getX();
            int pointY = (int) event.getY();
            //是否在大圆内
            if(Math.sqrt(Math.pow(bigCenterX - pointX,2)+
                    Math.pow(bigCenterY - pointY,2) ) <= bigCenterR){
                smallCenterY = pointY;
                smallCenterX = pointX;
            }else {
                setSmallCircleXY(bigCenterX,bigCenterY,bigCenterR,getRad(bigCenterX,bigCenterY,pointX,pointY));
            }
        }
        return true;
    }


    private void myDraw() {
        try{
            canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                canvas.drawBitmap(bitmapBg,0,0,paint);
                paint.setColor(getResources().getColor(R.color.black_666666));
                canvas.drawCircle(bigCenterX, bigCenterY,bigCenterR,paint);
                paint.setColor(getResources().getColor(R.color.black_222222));
                canvas.drawCircle(smallCenterX,smallCenterY,50,paint);
            }

        }catch (Exception e){

        }finally {
            if(canvas != null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
    /**
     * game logic
     */
    private void logic(){
//        边上旋转
//        angel++;
//        if(angel >= 360){
//            angel = 0;
//        }
//        setSmallCircleXY(bigCenterX, bigCenterY,bigCenterR,angel * Math.PI /180);
    }

    private double getRad(float px1,float py1,float px2,float py2){
        float x = px2 - px1;
        float y = py2 - py1;
        //斜边

        float hypotenuse = (float) Math.sqrt(Math.pow(x,2) + Math.pow(y ,2));
        float cosAngle = x / hypotenuse;
        float rad = (float) Math.acos(cosAngle);
        Log.i("MySurfaceView","getRad：" + rad);
        if(py2 < py1){
            rad = - rad;
        }
        return rad;
    }

    @Override
    public void run() {
        while (flag){
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try{
                if(end - start < 30){
                    thread.sleep(30 - (end - start));
                }
            }catch (Exception e){

            }
        }
    }

    public void setSmallCircleXY(float centerX,float centerY,float R,double rad){
        smallCenterX = (float)(R * Math.cos(rad)) + centerX;
        smallCenterY = (float)(R * Math.sin(rad)) + centerY;
    }
}
