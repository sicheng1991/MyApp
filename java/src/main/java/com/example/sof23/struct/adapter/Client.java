package com.example.sof23.struct.adapter;

/**
 * 适配器模式:适配器模式把一个类的接口变换成客户端所期待的另一种接口，
 * 从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * Created by Longwj on 2017/8/21.
 */

public class Client {
    public static void main(String[] args){
        PayerAdapter adapter = new PayerAdapter(new Mp4());
        adapter.payMp3();
        adapter.payMp4();
    }
}
