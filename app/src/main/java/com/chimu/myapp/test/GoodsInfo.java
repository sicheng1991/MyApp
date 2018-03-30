package com.chimu.myapp.test;

import com.google.gson.annotations.Expose;

public class GoodsInfo implements Cloneable {
    private Long id;

    private String epc;
    private int epcNum;




    public GoodsInfo(Long id, String epc, int epcNum) {
        this.id = id;
        this.epc = epc;
        this.epcNum = epcNum;
    }

    public GoodsInfo(String epc, int epcNum) {
        this.epc = epc;
        this.epcNum = epcNum;
    }

    public GoodsInfo() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public Long getId() {

        return id;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpcNum(int epcNum) {
        this.epcNum = epcNum;
    }

    public int getEpcNum() {

        return epcNum;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", epc='" + epc + '\'' +
                ", epcNum=" + epcNum +
                ", isContains=" + isContains +
                '}';
    }


    private boolean isContains;//是否包含
    public GoodsInfo clone() {
        GoodsInfo o = null;
        try {
            o = (GoodsInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public boolean isContains() {
        return isContains;
    }

    public void setContains(boolean contains) {
        isContains = contains;
    }
}
