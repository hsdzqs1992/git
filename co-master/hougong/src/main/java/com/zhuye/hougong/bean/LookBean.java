package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class LookBean {
    /**
     * data : [{"v_id":"28","uid":"59","sex":"1","age":"18","nickname":"拉面主","face":"/Uploads/Picture/2017-12-09/20171209133903.png","create_time":"12-15 14:12"}]
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
         * v_id : 28
         * uid : 59
         * sex : 1
         * age : 18
         * nickname : 拉面主
         * face : /Uploads/Picture/2017-12-09/20171209133903.png
         * create_time : 12-15 14:12
         */

        private String v_id;
        private String uid;
        private String sex;
        private String age;
        private String nickname;
        private String face;
        private String create_time;

        public String getV_id() {
            return v_id;
        }

        public void setV_id(String v_id) {
            this.v_id = v_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
