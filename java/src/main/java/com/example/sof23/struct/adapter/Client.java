package com.example.sof23.struct.adapter;

/**

 * Created by Longwj on 2017/8/21.
 */

public class Client {
    public static void main(String[] args){
        PayerAdapter adapter = new PayerAdapter(new Mp4());
        adapter.payMp3();
        adapter.payMp4();
    }
}
