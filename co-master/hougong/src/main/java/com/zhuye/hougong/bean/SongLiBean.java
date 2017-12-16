package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class SongLiBean {

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        private String age;
        private String face;
        private String nickname;
        private String sex;
        private String uid;
        private List<GiftBean> gift;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public List<GiftBean> getGift() {
            return gift;
        }

        public void setGift(List<GiftBean> gift) {
            this.gift = gift;
        }

        public static class GiftBean {
            /**
             * money : 40
             * name : 爱心
             * num : 3
             * photo : /Uploads/Picture/2017-11-09/5a0416da5db2d.png
             */

            private String money;
            private String name;
            private String num;
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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
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
