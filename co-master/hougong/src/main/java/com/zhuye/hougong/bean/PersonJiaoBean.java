package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/12/1.
 */

public class PersonJiaoBean {
    /**
     * code : 200
     * data : {"age":"21","city":"郑州市","dynamic_content":"","dynamic_count":"3","dynamic_img":["/Uploads/Picture/2017-11-23/201711231208500.png","/Uploads/Picture/2017-11-23/201711231208501.png"],"feature":["音乐"],"gift":[{"money":"40","name":"爱心","photo":"/Uploads/Picture/2017-11-09/5a0416da5db2d.png"},{"money":"200","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a0416ed003d0.png"},{"money":"600","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a041701a3140.png"},{"money":"100","name":"彩玫瑰","photo":"/Uploads/Picture/2017-11-09/5a0416c617d78.png"},{"money":"200","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a0416ed003d0.png"}],"gift_count":"11","img":["/Uploads/Picture/2017-11-16/201711161728301.png","/Uploads/Picture/2017-11-16/201711161732190.png","/Uploads/Picture/2017-11-23/201711231002140.png","/Uploads/Picture/2017-11-09/020171109105148.png","/Uploads/Picture/2017-11-10/201711101505020.png","/Uploads/Picture/2017-11-09/220171109105148.png","/Uploads/Picture/2017-11-10/201711101505021.png","/Uploads/Picture/2017-11-13/201711131938370.png","/Uploads/Picture/2017-11-09/120171109111522.png","/Uploads/Picture/2017-11-09/020171109111522.png","/Uploads/Picture/2017-11-10/201711101505032.png","/Uploads/Picture/2017-11-13/201711131938381.png","/Uploads/Picture/2017-11-14/201711141831390.png","/Uploads/Picture/2017-11-14/201711141831391.png","/Uploads/Picture/2017-11-16/201711161728300.png"],"level":0,"nickname":"陈昆磊","sex":"1","uid":"7","video_money":"40","voice_money":"70"}
     * message :
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public static class DataBean {
        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        /**
         * age : 21
         * city : 郑州市
         * dynamic_content :
         * dynamic_count : 3
         * dynamic_img : ["/Uploads/Picture/2017-11-23/201711231208500.png","/Uploads/Picture/2017-11-23/201711231208501.png"]
         * feature : ["音乐"]
         * gift : [{"money":"40","name":"爱心","photo":"/Uploads/Picture/2017-11-09/5a0416da5db2d.png"},{"money":"200","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a0416ed003d0.png"},{"money":"600","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a041701a3140.png"},{"money":"100","name":"彩玫瑰","photo":"/Uploads/Picture/2017-11-09/5a0416c617d78.png"},{"money":"200","name":"钻戒","photo":"/Uploads/Picture/2017-11-09/5a0416ed003d0.png"}]
         * gift_count : 11
         * img : ["/Uploads/Picture/2017-11-16/201711161728301.png","/Uploads/Picture/2017-11-16/201711161732190.png","/Uploads/Picture/2017-11-23/201711231002140.png","/Uploads/Picture/2017-11-09/020171109105148.png","/Uploads/Picture/2017-11-10/201711101505020.png","/Uploads/Picture/2017-11-09/220171109105148.png","/Uploads/Picture/2017-11-10/201711101505021.png","/Uploads/Picture/2017-11-13/201711131938370.png","/Uploads/Picture/2017-11-09/120171109111522.png","/Uploads/Picture/2017-11-09/020171109111522.png","/Uploads/Picture/2017-11-10/201711101505032.png","/Uploads/Picture/2017-11-13/201711131938381.png","/Uploads/Picture/2017-11-14/201711141831390.png","/Uploads/Picture/2017-11-14/201711141831391.png","/Uploads/Picture/2017-11-16/201711161728300.png"]
         * level : 0
         * nickname : 陈昆磊
         * sex : 1
         * uid : 7
         * video_money : 40
         * voice_money : 70
         */

        private String age;
        private String face;
        private String city;
        private String dynamic_content;
        private String dynamic_count;
        private String gift_count;
        private int level;
        private String nickname;
        private String sex;
        private String uid;
        private String video_money;
        private String voice_money;
        private String jtlv;
        private String hx_username;
        private int mian_type;

        public String getJtlv() {
            return jtlv;
        }

        public void setJtlv(String jtlv) {
            this.jtlv = jtlv;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public int getMian_type() {
            return mian_type;
        }

        public void setMian_type(int mian_type) {
            this.mian_type = mian_type;
        }

        private List<String> dynamic_img;
        private List<String> feature;
        private List<GiftBean> gift;
        private List<String> img;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDynamic_content() {
            return dynamic_content;
        }

        public void setDynamic_content(String dynamic_content) {
            this.dynamic_content = dynamic_content;
        }

        public String getDynamic_count() {
            return dynamic_count;
        }

        public void setDynamic_count(String dynamic_count) {
            this.dynamic_count = dynamic_count;
        }

        public String getGift_count() {
            return gift_count;
        }

        public void setGift_count(String gift_count) {
            this.gift_count = gift_count;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getVideo_money() {
            return video_money;
        }

        public void setVideo_money(String video_money) {
            this.video_money = video_money;
        }

        public String getVoice_money() {
            return voice_money;
        }

        public void setVoice_money(String voice_money) {
            this.voice_money = voice_money;
        }

        public List<String> getDynamic_img() {
            return dynamic_img;
        }

        public void setDynamic_img(List<String> dynamic_img) {
            this.dynamic_img = dynamic_img;
        }

        public List<String> getFeature() {
            return feature;
        }

        public void setFeature(List<String> feature) {
            this.feature = feature;
        }

        public List<GiftBean> getGift() {
            return gift;
        }

        public void setGift(List<GiftBean> gift) {
            this.gift = gift;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public static class GiftBean {
            /**
             * money : 40
             * name : 爱心
             * photo : /Uploads/Picture/2017-11-09/5a0416da5db2d.png
             */

            private String money;
            private String name;
            private String photo;

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
}
