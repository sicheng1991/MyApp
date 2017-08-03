package com.example.bean;

/**
 * Created by Longwj on 2017/8/2.
 */

public class HostBean {
    private Object groups;
    private Object dials;
    private Object settings;

    public Object getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Object getDials() {
        return dials;
    }

    public void setDials(String dials) {
        this.dials = dials;
    }

    public Object getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "HostBean{" +
                "groups='" + groups + '\'' +
                ", dials='" + dials + '\'' +
                ", settings='" + settings + '\'' +
                '}';
    }
}
