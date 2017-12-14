package com.zhuye.hougong.bean;

/**
 * Created by zzzy on 2017/11/27.
 */

public class PersonInfoBean {

    /**
     * data : {"face":"","nickname":"11111111110_12","sex":"0","age":"18","con":"","city":"","city_code":"","level":"0","voice_open":"p1","voice_money":"0","video_open":"p1","video_money":"0","interest":0,"love":0,"trends":0,"usertype":"0"}
     * message :
     * code : 200
     */

    private DataBean data;
    private String message;
    private String code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * face :
         * nickname : 11111111110_12
         * sex : 0
         * age : 18
         * con :
         * city :
         * city_code :
         * level : 0
         * voice_open : p1
         * voice_money : 0
         * video_open : p1
         * video_money : 0
         * interest : 0
         * love : 0
         * trends : 0
         * usertype : 0
         */

        private String face;
        private String nickname;
        private String sex;
        private String age;
        private String con;
        private String city;
        private String city_code;
        private String level;
        private String voice_open;
        private String voice_money;
        private String video_open;
        private String video_money;
        private int interest;
        private int love;
        private int trends;
        private String usertype;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getCon() {
            return con;
        }

        public void setCon(String con) {
            this.con = con;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getVoice_open() {
            return voice_open;
        }

        public void setVoice_open(String voice_open) {
            this.voice_open = voice_open;
        }

        public String getVoice_money() {
            return voice_money;
        }

        public void setVoice_money(String voice_money) {
            this.voice_money = voice_money;
        }

        public String getVideo_open() {
            return video_open;
        }

        public void setVideo_open(String video_open) {
            this.video_open = video_open;
        }

        public String getVideo_money() {
            return video_money;
        }

        public void setVideo_money(String video_money) {
            this.video_money = video_money;
        }

        public int getInterest() {
            return interest;
        }

        public void setInterest(int interest) {
            this.interest = interest;
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }

        public int getTrends() {
            return trends;
        }

        public void setTrends(int trends) {
            this.trends = trends;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }
    }
}
