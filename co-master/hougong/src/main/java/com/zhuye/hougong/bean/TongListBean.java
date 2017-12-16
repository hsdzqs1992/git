package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class TongListBean {
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
         * uid : 60
         * nickname : 哈哈
         * face :
         * usertype : 0
         * hx_username : 601367698171660
         * stealth : 0
         * voice_open : 1
         * voice_money : 5
         * video_open : 1
         * video_money : 5
         * time : 0
         */

        private String uid;
        private String nickname;
        private String face;
        private String usertype;
        private String hx_username;
        private String stealth;
        private String voice_open;
        private String voice_money;
        private String video_open;
        private String video_money;
        private String time;

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

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public String getStealth() {
            return stealth;
        }

        public void setStealth(String stealth) {
            this.stealth = stealth;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
