package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class PaiHangBean {

    /**
     * data : [{"uid":"7","count":"1440","ranking":1,"nickname":"陈昆磊","face":"/Uploads/Picture/2017-11-23/20171123114138.png","sex":"1","age":"21"},{"uid":"8","count":"840","ranking":2,"nickname":"13460300870","face":"","sex":"0","age":"18"}]
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
         * uid : 7
         * count : 1440
         * ranking : 1
         * nickname : 陈昆磊
         * face : /Uploads/Picture/2017-11-23/20171123114138.png
         * sex : 1
         * age : 21
         */

        private String uid;

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        private String love;
        private String count;
        private int ranking;
        private String nickname;
        private String face;
        private String sex;
        private String age;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
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
    }
}
