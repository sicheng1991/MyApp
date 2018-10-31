package com.sicheng.game.snakegame.view;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.sicheng.game.snakegame.R;

/**
 * 手势识别
 * Created by yangzteL on 2018/10/29 0026.
 */

public class GestureSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    private int textX = 10;
    private int textY = 50;

    private Thread thread;
    private boolean flag;
    private Canvas canvas;
    private Canvas canvasClip;


    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
    }

    public GestureSurfaceView(Context context) {
        super(context);
        init();
//        sensor();



    }

    private void sensor() {
        Activity activity = getActivity(getContext());
        if(activity != null){
            SensorManager sm = (SensorManager) activity.getSystemService(Service.SENSOR_SERVICE);
            Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            SensorEventListener sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    Log.i("MySurfaceView","onSensorChanged" + event.accuracy + ":" + event.timestamp
                            + ":" + event.values[0]+ ":" + event.values[1]+ ":" + event.values[2]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    Log.i("MySurfaceView","onAccuracyChanged,精度" + accuracy);
                }
            };
            sm.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }


    //通过context获取Activity
    private Activity getActivity(Context context){
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return getActivity(wrapper.getBaseContext());
        } else {
            return null;
        }
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
//                canvas.clipRect(0,0,80,80);
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
