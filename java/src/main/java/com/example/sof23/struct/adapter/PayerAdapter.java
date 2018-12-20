package com.example.sof23.struct.adapter;

/**

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
