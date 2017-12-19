package com.chimu.mylib.base;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public interface BasePresenter extends BaseBus{
    abstract void onCreate();
    abstract void onDestroy();
}
