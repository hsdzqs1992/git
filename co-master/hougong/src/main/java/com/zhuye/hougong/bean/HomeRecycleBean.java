package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class HomeRecycleBean {

    /**
     * data : [{"uid":"7","face":"/Uploads/Picture/2017-11-08/20171108104839.png","nickname":"Aslkdjasd","sex":"p2","age":"17","voice_money":"70","video_money":"40","stealth":"0","love":0},{"uid":"8","face":"","nickname":"13460300870","sex":"0","age":"18","voice_money":"0","video_money":"0","stealth":"0","love":0}]
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
         * face : /Uploads/Picture/2017-11-08/20171108104839.png
         * nickname : Aslkdjasd
         * sex : p2
         * age : 17
         * voice_money : 70
         * video_money : 40
         * stealth : 0
         * love : 0
         */

        private String uid;
        private String face;
        private String nickname;
        private String sex;
        private String age;
        private String voice_money;
        private String video_money;
        private String stealth;
        private int love;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

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

        public String getVoice_money() {
            return voice_money;
        }

        public void setVoice_money(String voice_money) {
            this.voice_money = voice_money;
        }

        public String getVideo_money() {
            return video_money;
        }

        public void setVideo_money(String video_money) {
            this.video_money = video_money;
        }

        public String getStealth() {
            return stealth;
        }

        public void setStealth(String stealth) {
            this.stealth = stealth;
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }
    }
}
