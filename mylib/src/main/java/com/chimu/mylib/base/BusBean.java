package com.chimu.mylib.base;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class BusBean {
    private int code;//标志
    private Object data;//数据

    public BusBean(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
