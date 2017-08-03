package com.chimu.mylib.db.dbmanager;

/**
 * Created by luorucai on 2017/5/5.
 */
public class DbRun <T> extends RunAbs<T> {

    /**
     * 异步线程执行
     */
    @Override
    public T run() throws Exception {
        return null;
    }

    /**
     * UI线程执行
     * @param data run方法返回参数
     */
    @Override
    public void onMainThread(T data) throws Exception {

    }
}
