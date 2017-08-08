package com.example.bean;

/**
 * Created by Longwj on 2017/8/2.
 */

public class Dialbean {

    /**
     * id : 2
     * title :
     * url : https://www.baidu.com/
     * thumbnail :
     * ts_created : 1481089755
     * visits : 533
     * visits_morning : 207
     * visits_afternoon : 260
     * visits_evening : 66
     * visits_night : 0
     * position : 0
     * idgroup : 0
     */

    private int id;
    private String title;
    private String url;
    private String thumbnail;
    private int ts_created;
    private int visits;
    private int visits_morning;
    private int visits_afternoon;
    private int visits_evening;
    private int visits_night;
    private int position;
    private int idgroup;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getTs_created() {
        return ts_created;
    }

    public void setTs_created(int ts_created) {
        this.ts_created = ts_created;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getVisits_morning() {
        return visits_morning;
    }

    public void setVisits_morning(int visits_morning) {
        this.visits_morning = visits_morning;
    }

    public int getVisits_afternoon() {
        return visits_afternoon;
    }

    public void setVisits_afternoon(int visits_afternoon) {
        this.visits_afternoon = visits_afternoon;
    }

    public int getVisits_evening() {
        return visits_evening;
    }

    public void setVisits_evening(int visits_evening) {
        this.visits_evening = visits_evening;
    }

    public int getVisits_night() {
        return visits_night;
    }

    public void setVisits_night(int visits_night) {
        this.visits_night = visits_night;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    @Override
    public String toString() {
        return "Dialbean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", ts_created=" + ts_created +
                ", visits=" + visits +
                ", visits_morning=" + visits_morning +
                ", visits_afternoon=" + visits_afternoon +
                ", visits_evening=" + visits_evening +
                ", visits_night=" + visits_night +
                ", position=" + position +
                ", idgroup=" + idgroup +
                '}';
    }
}
