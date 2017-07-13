package com.chimu.mylib.bean;

import java.util.List;

/**
 * Created by longwj on 2017/3/1.
 */

public class InfoBean {

    @Override
    public String toString() {
        return "InfoBean{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * error : 0
     * message : ok
     * data : [{"title":"一起装修网成功上市","imagesrc":"http://appmanager.17house.com/upload/20161219/5857a5dbf1dc1_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20161219/5857a5dc162dd_t.jpg","tid":"","type":4,"banner_url":"http://www.17house.com/news/260806.html"},{"title":"新春装修公益大讲堂","imagesrc":"http://appmanager.17house.com/upload/20170228/58b58cf38e47a_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20170228/58b58cf3ab57f_t.jpg","tid":"","type":4,"banner_url":"http://wap.17house.com/tuan/2856.html?xmt=zimeit&xmtkey=wb"},{"title":"装修省40%的秘笈，看这里！","imagesrc":"http://appmanager.17house.com/upload/20170225/58b1714a22e66_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20170225/58b1714a6480b_t.jpg","tid":"","type":4,"banner_url":"http://tuan.17house.com/bj/2855k0s30e2554.html"},{"title":"0元装修","imagesrc":"http://appmanager.17house.com/upload/20161109/5822e89f15dc6_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20161109/5822e89f371ab_t.jpg","tid":"","type":4,"banner_url":"http://wap.17house.com/daikuan/"},{"title":"全包装修80平米仅需5.4万","imagesrc":"http://appmanager.17house.com/upload/20170202/5892d5196f2aa_t.png","imagesrc2":"http://appmanager.17house.com/upload/20170202/5892d519a076d_t.png","tid":"","type":4,"banner_url":"http://beijing.17zhuangxiu.com/zhengzhuang/"}]
     */

    private int error;
    private String message;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", imagesrc='" + imagesrc + '\'' +
                    ", imagesrc2='" + imagesrc2 + '\'' +
                    ", tid='" + tid + '\'' +
                    ", type=" + type +
                    ", banner_url='" + banner_url + '\'' +
                    '}';
        }

        /**
         * title : 一起装修网成功上市
         * imagesrc : http://appmanager.17house.com/upload/20161219/5857a5dbf1dc1_t.jpg
         * imagesrc2 : http://appmanager.17house.com/upload/20161219/5857a5dc162dd_t.jpg
         * tid :
         * type : 4
         * banner_url : http://www.17house.com/news/260806.html
         */

        private String title;
        private String imagesrc;
        private String imagesrc2;
        private String tid;
        private int type;
        private String banner_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImagesrc() {
            return imagesrc;
        }

        public void setImagesrc(String imagesrc) {
            this.imagesrc = imagesrc;
        }

        public String getImagesrc2() {
            return imagesrc2;
        }

        public void setImagesrc2(String imagesrc2) {
            this.imagesrc2 = imagesrc2;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }
    }
}
