package com.example.sof23.struct.adapter;

/**
 * 适配器
 *
 1 适配器必须实现原有的旧的接口
 2 适配器对象中持有对新接口的引用，
 当调用旧接口时，将这个调用委托给实现新接口的对象来处理，也就是在适配器对象中组合一个新接口。
 * Created by Longwj on 2017/8/21.
 */

public class PayerAdapter implements IMp3 {
    private IMp4 iMp4;

    public PayerAdapter(IMp4 iMp4) {
        this.iMp4 = iMp4;
    }

    @Override
    public void payMp3() {
        iMp4.payMp3();
    }
    public void payMp4() {
        iMp4.PayMp4();
    }
}
