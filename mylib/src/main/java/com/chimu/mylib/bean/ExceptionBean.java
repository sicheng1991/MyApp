package com.chimu.mylib.bean;

/**
 * 错误收集bean
 * Created by Longwj on 2017/10/25.
 */

public final class ExceptionBean {
    private int id;
    private String page_name;//crash位置
    private String exception_name;//错误名称
    private String exception_stack;//详细信息
    private String crash_type;//1：try_catch 2:崩溃
    private String app_version;//app版本
    private String os_versin;//Android系统版本号
    private String device_model;//手机型号
    private String device_mac;//设备MAC
    private String network_type;//网络类型
    private String channel_id;//渠道号
    private String  memory_info;//内存信息
    private String crash_name;//当前异常名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getException_name() {
        return exception_name;
    }

    public void setException_name(String exception_name) {
        this.exception_name = exception_name;
    }

    public String getException_stack() {
        return exception_stack;
    }

    public void setException_stack(String exception_stack) {
        this.exception_stack = exception_stack;
    }

    public String getCrash_type() {
        return crash_type;
    }

    public void setCrash_type(String crash_type) {
        this.crash_type = crash_type;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getOs_versin() {
        return os_versin;
    }

    public void setOs_versin(String os_versin) {
        this.os_versin = os_versin;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getMemory_info() {
        return memory_info;
    }

    public void setMemory_info(String memory_info) {
        this.memory_info = memory_info;
    }

    public String getCrash_name() {
        return crash_name;
    }

    public void setCrash_name(String crash_name) {
        this.crash_name = crash_name;
    }

    @Override
    public String toString() {
        return "ExceptionBean{" +
                "id=" + id +
                ", page_name='" + page_name + '\'' +
                ", exception_name='" + exception_name + '\'' +
                ", exception_stack='" + exception_stack + '\'' +
                ", crash_type='" + crash_type + '\'' +
                ", app_version='" + app_version + '\'' +
                ", os_versin='" + os_versin + '\'' +
                ", device_model='" + device_model + '\'' +
                ", device_mac='" + device_mac + '\'' +
                ", network_type='" + network_type + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", memory_info='" + memory_info + '\'' +
                ", crash_name='" + crash_name + '\'' +
                '}';
    }
}
