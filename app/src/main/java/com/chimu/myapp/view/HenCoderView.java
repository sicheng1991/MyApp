package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

/**
 * Created by Longwj on 2017/9/25.
 */

public class HenCoderView extends View {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HenCoderView(Context context) {
        super(context);
        init();
    }
    private Paint paint;
    private Path path;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        path = new Path();

        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);

    }
}
