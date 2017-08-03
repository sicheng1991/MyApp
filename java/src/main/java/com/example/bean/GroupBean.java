package com.example.bean;

/**
 * Created by Longwj on 2017/8/2.
 */

public class GroupBean {

    /**
     * id : 3
     * title : 琦彩科技
     * color : FFFFFF
     * position : 2
     */

    private int id;
    private String title;
    private String color;
    private int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", position=" + position +
                '}';
    }
}
