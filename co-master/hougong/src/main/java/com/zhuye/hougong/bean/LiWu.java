package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/12/1.
 */

public class LiWu {
    /**
     * data : [{"gift_id":"1","money":"100","name":"彩玫瑰","photo":"/Uploads/Picture/2017-11-09/5a0416c617d78.png"},{"gift_id":"2","money":"30","name":"旋转木马","photo":"/Uploads/Picture/2017-11-09/5a0416d110b07.png"},{"gift_id":"3","money":"40","name":"爱心","photo":"/Uploads/Picture/2017-11-09/5a0416da5db2d.png"},{"gift_id":"4","money":"80","name":"红玫瑰","photo":"/Uploads/Picture/2017-11-09/5a0416e3ec08c.png"},{"gift_id":"5","money":"200","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a0416ed003d0.png"},{"gift_id":"6","money":"500","name":"皇冠","photo":"/Uploads/Picture/2017-11-09/5a041701a3140.png"},{"gift_id":"7","money":"600","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a041701a3140.png"},{"gift_id":"8","money":"1500","name":"钻帮","photo":"/Uploads/Picture/2017-11-09/5a04172145243.png"},{"gift_id":"9","money":"1400","name":"美食","photo":"/Uploads/Picture/2017-11-09/5a04173f876bf.png"},{"gift_id":"10","money":"9000","name":"红酒","photo":"/Uploads/Picture/2017-11-09/5a041756c7cb7.png"},{"gift_id":"11","money":"500","name":"爱卡","photo":"/Uploads/Picture/2017-11-09/5a041768c15b8.png"}]
     * message :
     * code : 200
     */

    private String message;
    private String code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gift_id : 1
         * money : 100
         * name : 彩玫瑰
         * photo : /Uploads/Picture/2017-11-09/5a0416c617d78.png
         */

        private String gift_id;
        private String money;
        private String name;
        private String photo;

        public String getGift_id() {
            return gift_id;
        }

        public void setGift_id(String gift_id) {
            this.gift_id = gift_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
