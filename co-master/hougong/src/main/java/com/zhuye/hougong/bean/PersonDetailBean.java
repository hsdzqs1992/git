package com.zhuye.hougong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzzy on 2017/11/28.
 */

public class PersonDetailBean implements Serializable{

    /**
     * data : {"type":0,"nickname":"陈昆磊","uid":"7","face":"/Uploads/Picture/2017-11-23/20171123114138.png","sex":"1","age":"21","con":"双子座","city":"郑州市","love":"1","fans":0,"dynamic":"3","img_count":"15","img":["/Uploads/Picture/2017-11-16/201711161728301.png","/Uploads/Picture/2017-11-16/201711161732190.png","/Uploads/Picture/2017-11-23/201711231002140.png","/Uploads/Picture/2017-11-09/020171109105148.png","/Uploads/Picture/2017-11-10/201711101505020.png","/Uploads/Picture/2017-11-09/220171109105148.png","/Uploads/Picture/2017-11-10/201711101505021.png","/Uploads/Picture/2017-11-13/201711131938370.png","/Uploads/Picture/2017-11-09/120171109111522.png","/Uploads/Picture/2017-11-09/020171109111522.png","/Uploads/Picture/2017-11-10/201711101505032.png","/Uploads/Picture/2017-11-13/201711131938381.png","/Uploads/Picture/2017-11-14/201711141831390.png","/Uploads/Picture/2017-11-14/201711141831391.png","/Uploads/Picture/2017-11-16/201711161728300.png"],"guanzhu":1,"black":0}
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
         * type : 0
         * nickname : 陈昆磊
         * uid : 7
         * face : /Uploads/Picture/2017-11-23/20171123114138.png
         * sex : 1
         * age : 21
         * con : 双子座
         * city : 郑州市
         * love : 1
         * fans : 0
         * dynamic : 3
         * img_count : 15
         * img : ["/Uploads/Picture/2017-11-16/201711161728301.png","/Uploads/Picture/2017-11-16/201711161732190.png","/Uploads/Picture/2017-11-23/201711231002140.png","/Uploads/Picture/2017-11-09/020171109105148.png","/Uploads/Picture/2017-11-10/201711101505020.png","/Uploads/Picture/2017-11-09/220171109105148.png","/Uploads/Picture/2017-11-10/201711101505021.png","/Uploads/Picture/2017-11-13/201711131938370.png","/Uploads/Picture/2017-11-09/120171109111522.png","/Uploads/Picture/2017-11-09/020171109111522.png","/Uploads/Picture/2017-11-10/201711101505032.png","/Uploads/Picture/2017-11-13/201711131938381.png","/Uploads/Picture/2017-11-14/201711141831390.png","/Uploads/Picture/2017-11-14/201711141831391.png","/Uploads/Picture/2017-11-16/201711161728300.png"]
         * guanzhu : 1
         * black : 0
         */

        private int type;
        private String nickname;
        private String uid;
        private String face;
        private String sex;
        private String age;
        private String con;
        private String city;
        private String love;
        private int fans;
        private String dynamic;
        private String img_count;
        private int guanzhu;
        private int black;
        private List<String> img;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

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

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public String getImg_count() {
            return img_count;
        }

        public void setImg_count(String img_count) {
            this.img_count = img_count;
        }

        public int getGuanzhu() {
            return guanzhu;
        }

        public void setGuanzhu(int guanzhu) {
            this.guanzhu = guanzhu;
        }

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }
    }
}
