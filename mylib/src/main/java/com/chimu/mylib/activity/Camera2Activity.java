package com.chimu.mylib.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chimu.mylib.R;
import com.chimu.mylib.base.BaseActivity;
import com.chimu.mylib.manager.AudioManager;
import com.chimu.mylib.util.FileUtil;
import com.chimu.mylib.util.ToastUtil;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by Longwj on 2017/11/2.
 */

public class Camera2Activity extends BaseActivity implements SurfaceHolder.Callback {

    FrameLayout flPreview;
    Button btCapture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private boolean mIsRecording = false;

    private Camera mCamera;
    private SurfaceView surfaceView;
    private Button btn;
    private Button start_audio;
    private Button stop_audio;
    private Button btn_video;
    private boolean is_video;

    private SurfaceHolder holder;
    private MediaRecorder mMediaRecorder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCamera();
        onInitView();
    }

    @Override
    public int contentView() {
        return R.layout.activity_camera;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    /**
     *
     */
    private void initCamera() {

        if (!checkCameraHardware(this)) {
            ToastUtil.showShort(this, "没有相机");
            return;
        }
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//        mPreview = new CameraPreview(this, mCamera);

        FrameLayout preview = (FrameLayout) findViewById(R.id.fl_preview);
        btn = (Button) findViewById(R.id.bt_capture);
        start_audio = (Button) findViewById(R.id.bt_start_audio);
        stop_audio = (Button) findViewById(R.id.bt_stop_audio);
        btn_video = (Button) findViewById(R.id.bt_video);

        mCamera.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                Log.i("msg", "原始图片.size:" + (data == null ? 0 : data.length));
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动对焦后拍照
                is_video = !is_video;
                if(is_video){
                    startMediaRecorder();
                }else{
                    stopMediaRecorder();
                }

            }
        });

        setPreviewFormat(mCamera, mCamera.getParameters());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动对焦后拍照
                mCamera.autoFocus(autoFocusCallback);
            }
        });

        start_audio.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                  //开始录音
                AudioManager am = AudioManager.getInstance(Camera2Activity.this);
                am.savePath(Environment.getExternalStorageDirectory().getPath() + "/myapp/mp33333");
                am.startRecord();
            }
        });
        stop_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束录音
               AudioManager.getInstance(Camera2Activity.this).stopRecord();
            }
        });
    }

    /**
     * Check if this device has a mCamera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a mCamera
            return true;
        } else {
            // no mCamera on this device
            return false;
        }
    }


    /**
     * 自动对焦 对焦成功后 就进行拍照
     */
    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {//对焦成功
                camera.takePicture(new Camera.ShutterCallback() {//按下快门
                    @Override
                    public void onShutter() {
                        //按下快门瞬间的操作
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {//是否保存原始图片的信息

                    }
                }, pictureCallback);
            }
        }
    };

    /**
     * 获取图片
     */
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (resource == null) {
                Toast.makeText(Camera2Activity.this, "拍照失败", Toast.LENGTH_SHORT).show();
                return;
            }
            final Matrix matrix = new Matrix();
            matrix.setRotate(90);
            final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
            File mPhotoFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            try {
                FileUtil.saveFile(mPhotoFile.getPath(),data);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.i("msg", "bitmap.size:" + bitmap.getByteCount());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();//
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();//
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void onInitView() {
        flPreview = (FrameLayout) findViewById(R.id.fl_preview);
        btCapture = (Button) findViewById(R.id.bt_capture);
    }

    public static void setPreviewFormat(Camera camera, Camera.Parameters parameters) {
        //设置预览回调的图片格式
        try {
            parameters.setPreviewFormat(ImageFormat.NV21);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置预览帧率
     *
     * @param camera
     * @param fps
     */
    public static void setCameraFps(Camera camera, int fps) {
        Camera.Parameters params = camera.getParameters();
        int[] range = adaptPreviewFps(fps, params.getSupportedPreviewFpsRange());
        params.setPreviewFpsRange(range[0], range[1]);
        camera.setParameters(params);
    }

    private static int[] adaptPreviewFps(int expectedFps, List<int[]> fpsRanges) {
        expectedFps *= 1000;
        int[] closestRange = fpsRanges.get(0);
        int measure = Math.abs(closestRange[0] - expectedFps) + Math.abs(closestRange[1] - expectedFps);
        for (int[] range : fpsRanges) {
            if (range[0] <= expectedFps && range[1] >= expectedFps) {
                int curMeasure = Math.abs(range[0] - expectedFps) + Math.abs(range[1] - expectedFps);
                if (curMeasure < measure) {
                    closestRange = range;
                    measure = curMeasure;
                }
            }
        }
        return closestRange;
    }

    /**
     * 录像
     */

    private void startMediaRecorder() {
        mCamera.unlock();
        mIsRecording = true;
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.reset();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        CamcorderProfile mCamcorderProfile = CamcorderProfile.get(Camera.CameraInfo.CAMERA_FACING_BACK,
                CamcorderProfile.QUALITY_HIGH);
        mMediaRecorder.setProfile(mCamcorderProfile);
        mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).getPath());
        mMediaRecorder.setPreviewDisplay(holder.getSurface());

        try {
            mMediaRecorder.prepare();
        } catch (Exception e) {
            mIsRecording = false;
            Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            mCamera.lock();
        }
        mMediaRecorder.start();
    }

    /**
     * 保存
     *
     * @param type
     * @return
     */
    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()+"/myapp/camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("linc", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    protected void releaseCamera() {
        if(mCamera!=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    private void stopMediaRecorder() {
        if (mMediaRecorder != null) {
            if (mIsRecording) {
                mMediaRecorder.stop();
                //mCamera.lock();
                mMediaRecorder.reset();
                mMediaRecorder.release();
                mMediaRecorder = null;
                mIsRecording = false;
                try {
                    mCamera.reconnect();
                } catch (IOException e) {
                    Toast.makeText(this, "reconect fail", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    }
}
