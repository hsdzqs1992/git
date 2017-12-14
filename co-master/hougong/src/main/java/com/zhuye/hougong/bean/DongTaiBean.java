package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/11/29.
 */

public class DongTaiBean {

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
         * uid : 24
         * nickname : 14725836914_34
         * face :
         * sex : 0
         * age : 18
         * content : 法规和地方
         * city : 北京市
         * dynamic_id : 81
         * zan : 0
         * gift : 0
         * create_time : 11/29 15:11
         * zan_id : 0
         * ping_count : 0
         * photo : []
         * photo_type : 1
         */

        private String uid;
        private String nickname;
        private String face;
        private String sex;
        private String age;
        private String content;
        private String city;
        private String dynamic_id;
        private String zan;
        private String gift;
        private String create_time;
        private int zan_id;
        private int ping_count;
        private int photo_type;
        private List<?> photo;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDynamic_id() {
            return dynamic_id;
        }

        public void setDynamic_id(String dynamic_id) {
            this.dynamic_id = dynamic_id;
        }

        public String getZan() {
            return zan;
        }

        public void setZan(String zan) {
            this.zan = zan;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getZan_id() {
            return zan_id;
        }

        public void setZan_id(int zan_id) {
            this.zan_id = zan_id;
        }

        public int getPing_count() {
            return ping_count;
        }

        public void setPing_count(int ping_count) {
            this.ping_count = ping_count;
        }

        public int getPhoto_type() {
            return photo_type;
        }

        public void setPhoto_type(int photo_type) {
            this.photo_type = photo_type;
        }

        public List<?> getPhoto() {
            return photo;
        }

        public void setPhoto(List<?> photo) {
            this.photo = photo;
        }
    }
}
