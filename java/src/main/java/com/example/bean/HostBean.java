package com.example.bean;

/**
 * Created by Longwj on 2017/8/2.
 */

public class HostBean {
    private String groups;
    private String dials;
    private String settings;

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getDials() {
        return dials;
    }

    public void setDials(String dials) {
        this.dials = dials;
    }

    public String getSettings() {
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
