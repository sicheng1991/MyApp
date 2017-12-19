package com.chimu.mylib.base;

/**
 *
 * model数据通过回调传递数据
 * Created by Administrator on 2017/12/19 0019.
 */

public interface BaseBus {
    /**回调的数据
     * @param bean
     */
    void transData(BusBean bean);
}
