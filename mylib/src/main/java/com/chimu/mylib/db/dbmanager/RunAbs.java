package com.chimu.mylib.db.dbmanager;

/**
 * Created by luorucai on 2017/5/5.
 */
public abstract class RunAbs<T> {

    public abstract T run() throws Exception;

    public abstract void onMainThread(T data) throws Exception;
}
