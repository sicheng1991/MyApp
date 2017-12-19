package com.chimu.mylib.base;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public interface BaseView {
    void showProgress( String s) ;
    void hintProgress() ;
    void showToast(String s) ;
    void finishView();
}
