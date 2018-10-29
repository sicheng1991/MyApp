package com.sicheng.game.snakegame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by yangzteL on 2018/10/26 0026.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    private int textX = 10;
    private int textY = 50;

    private Thread thread;
    private boolean flag;
    private Canvas canvas;


    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        thread = new Thread(this);
        thread.start();

    }


    private void myDraw() {
        try{
            canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                canvas.drawColor(Color.WHITE);
                canvas.clipRect(0,0,80,80);
                canvas.drawText("MySurfaceView",textX,textY,paint);
            }

        }catch (Exception e){

        }finally {
            if(canvas != null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
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
        textX = (int) event.getX();
        textY = (int) event.getY();
//        myDraw();
        return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        while (flag){
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try{
                if(end - start < 50){
                    thread.sleep(50 - (end - start));
                }
            }catch (Exception e){

            }

        }

    }

    /**
     * game logic
     */
    private void logic(){

    }
}
