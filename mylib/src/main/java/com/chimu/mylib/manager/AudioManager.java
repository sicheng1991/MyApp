package com.chimu.mylib.manager;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 录音管理类
 * <p>
 * Created by Longwj on 2017/11/3.
 */

public class AudioManager {
    private static Context context;
    private int sampleRateInHz = 44100;
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private AudioRecord record;
    private boolean isStart;
    private int bufferSize;
    private String path;//录音本地文件
    static AudioManager mAudioManager;
    private Thread recordThread;

    public static AudioManager getInstance(Context context1) {
        context = context1;
        if (mAudioManager == null) {
            synchronized (AudioManager.class) {
                if (mAudioManager == null) {
                    mAudioManager = new AudioManager();
                    mAudioManager.initAudio();
                }
            }
        }
        return mAudioManager;
    }

    private AudioManager() {

    }

    public void savePath(String path) {
        this.path = path;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAudio() {
//        getValidSampleRates();

        //获取最小缓冲区大小
        bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        Log.d("msg", "initAudio: 最小缓冲区大小：" + bufferSize);
        clearEcho();

        record = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, audioFormat, bufferSize * 2);

    }

    public void startRecord() {
        if (path == null) {
            throw new IllegalStateException("请设置存储路径");
        }

//        destroyThread();
        isStart = true;
//        if (recordThread == null) {
//            recordThread = new Thread(recordRunnable);
//            recordThread.start();
//        }
        recordThread = new Thread(recordRunnable);
        recordThread.start();
//        writeDate2File();
    }

    public void stopRecord() {
        isStart = false;
        copyWaveFile(path, path + ".wav");//给裸数据加上头文件
    }

    Runnable recordRunnable = new Runnable() {
        @Override
        public void run() {
            byte[] tempBuffer = new byte[bufferSize];
            record.startRecording();
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (isStart) {
                if (null != record) {
                    int bytesRecord = record.read(tempBuffer, 0, bufferSize);
                    if (bytesRecord == AudioRecord.ERROR_INVALID_OPERATION || bytesRecord == AudioRecord.ERROR_BAD_VALUE) {
                        continue;
                    }
                    try {
                        fos.write(tempBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 销毁线程方法
     */
    private void destroyThread() {
        try {
            isStart = false;
            if (null != recordThread && Thread.State.RUNNABLE == recordThread.getState()) {
                try {
                    Thread.sleep(500);
                    recordThread.interrupt();
                } catch (Exception e) {
                    recordThread = null;
                }
            }
            recordThread = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            recordThread = null;
        }
    }

    private void writeDate2File() {
        // new一个byte数组用来存一些字节数据，大小为缓冲区大小
        byte[] audiodata = new byte[bufferSize];
        FileOutputStream fos = null;
        int readsize = 0;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath()+"/myapp/recode");
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);// 建立一个可存取字节的文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        readsize = record.read(audiodata, 0, bufferSize);
        if (AudioRecord.ERROR_INVALID_OPERATION != readsize) {
            try {
                fos.write(audiodata);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fos.close();// 关闭写入流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支持的采样率
     */
    private void getValidSampleRates() {
        for (int rate : new int[]{8000, 11025, 16000, 22050, 44100}) {  // add the rates you wish to check against
            int bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig, audioFormat);
            if (bufferSize > 0) {
                // buffer size is valid, Sample rate supported
                Log.d("msg", "initAudio: 支持的采样率：" + rate);
            }
        }
    }

    /**
     * 消除回声
     */
    private void clearEcho() {
        android.media.AudioManager audioManager = (android.media.AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(android.media.AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);
    }

    /**
     * 改为wav音频文件
     * @param inFileName
     * @param outFileName
     */
    private void copyWaveFile(String inFileName, String outFileName)
    {
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long longSampleRate = sampleRateInHz;
        int channels = 2;
        long byteRate = 16 * sampleRateInHz * channels / 8;

        byte[] data = new byte[bufferSize];
        try
        {
            in = new FileInputStream(inFileName);
            out = new FileOutputStream(outFileName);

            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
            while(in.read(data) != -1)
            {
                out.write(data);
            }

            in.close();
            out.close();
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 这里提供一个头信息。插入这些信息就可以得到可以播放的文件。
     * 为我为啥插入这44个字节，这个还真没深入研究，不过你随便打开一个wav
     * 音频的文件，可以发现前面的头文件可以说基本一样哦。每种格式的文件都有
     * 自己特有的头文件。
     */
    private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                     long totalDataLen, long longSampleRate, int channels, long byteRate)
            throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

}
