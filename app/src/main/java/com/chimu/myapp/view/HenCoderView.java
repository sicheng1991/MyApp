package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.chimu.myapp.R;

/**
 * Created by Longwj on 2017/9/25.
 */

public class HenCoderView extends View {
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HenCoderView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private Paint paint;
    private Path path;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
        path = new Path();

//        // 使用 path 对图形进行描述（这段描述代码不必看懂）
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
    }

    /**
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
//        canvas.drawRect(new Rect(100,100,600,600),paint);

//        canvas.clipRect(200,200,300,300);
//
//        canvas.save();
//        canvas.translate(200, 0);
//        Path path1 =  new Path();
//        path1.addCircle(200,200,100, Path.Direction.CW);
//        canvas.clipPath(path1);
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.b1);
//        canvas.drawBitmap(bitmap,100,100,paint);
//        canvas.rotate(45, 200, 200);
//        canvas.restore();

        canvas.save();
        Camera camera = new Camera();
        camera.setLocation(0,0,-20);
        camera.rotateX(60); // 旋转 Camera 的三维空间
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.b1);

        canvas.translate(bitmap.getWidth() / 2 + 100, bitmap.getHeight() / 2 + 100); // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-(bitmap.getWidth() / 2 + 100), -(bitmap.getHeight() / 2 + 100)); // 旋转之前把绘制内容移动到轴心（原点）
        canvas.drawBitmap(bitmap, 100, 100, paint);
        canvas.restore();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
