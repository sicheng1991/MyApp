package com.chimu.myapp.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Longwj on 2017/9/21.
 */

public class MyGlServiceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGlServiceView(Context context) {
        super(context);
        // 创建一个OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
}
