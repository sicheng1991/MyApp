package com.chimu.mylib.db.dbmanager;

import android.os.Message;

/**
 * Handler 信息体
 */

public class MessageInfo<T> {

    public int what;

    public DbRun<T> dbRun;

    public T model;

    public MessageInfo() {
    }

    public Message build(){
        Message msg = new Message();
        msg.what = this.what;
        msg.obj = this;
        return msg;
    }
}
