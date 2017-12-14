package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/11/28.
 */

public class MyFriendsBean {

    /**
     * data : [{"love_id":"37","uid":"8","nickname":"13460300870","face":""},{"love_id":"36","uid":"7","nickname":"陈昆磊","face":"/Uploads/Picture/2017-11-23/20171123114138.png"},{"love_id":"35","uid":"7","nickname":"陈昆磊","face":"/Uploads/Picture/2017-11-23/20171123114138.png"}]
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
         * love_id : 37
         * uid : 8
         * nickname : 13460300870
         * face :
         */

        private String love_id;
        private String uid;
        private String nickname;
        private String face;

        public String getLove_id() {
            return love_id;
        }

        public void setLove_id(String love_id) {
            this.love_id = love_id;
        }

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
    }
}
