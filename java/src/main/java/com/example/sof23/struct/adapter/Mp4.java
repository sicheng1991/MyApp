package com.example.sof23.struct.adapter;

/**
 * Created by Longwj on 2017/8/21.
 */

public class Mp4 implements IMp4 {
    @Override
    public void payMp3() {
        System.out.println("Mp4 MP3");
    }

    @Override
    public void PayMp4() {
        System.out.println("Mp4�в���MP4");
    }
}
