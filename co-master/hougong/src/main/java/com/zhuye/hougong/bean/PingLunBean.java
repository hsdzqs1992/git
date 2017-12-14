package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/12/1.
 */

public class PingLunBean {
    /**
     * data : [{"ping_id":"17","content":"发发汗","create_time":"1511769263","zan":"0","ping_face":"/Uploads/Picture/2017-11-29/20171129122541.png","ping_nickname":"大點哥","hui_nickname":"","zan_type":0}]
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
         * ping_id : 17
         * content : 发发汗
         * create_time : 1511769263
         * zan : 0
         * ping_face : /Uploads/Picture/2017-11-29/20171129122541.png
         * ping_nickname : 大點哥
         * hui_nickname :
         * zan_type : 0
         */

        private String ping_id;
        private String content;
        private String create_time;
        private String zan;
        private String ping_face;
        private String ping_nickname;
        private String hui_nickname;
        private int zan_type;

        public String getPing_id() {
            return ping_id;
        }

        public void setPing_id(String ping_id) {
            this.ping_id = ping_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getZan() {
            return zan;
        }

        public void setZan(String zan) {
            this.zan = zan;
        }

        public String getPing_face() {
            return ping_face;
        }

        public void setPing_face(String ping_face) {
            this.ping_face = ping_face;
        }

        public String getPing_nickname() {
            return ping_nickname;
        }

        public void setPing_nickname(String ping_nickname) {
            this.ping_nickname = ping_nickname;
        }

        public String getHui_nickname() {
            return hui_nickname;
        }

        public void setHui_nickname(String hui_nickname) {
            this.hui_nickname = hui_nickname;
        }

        public int getZan_type() {
            return zan_type;
        }

        public void setZan_type(int zan_type) {
            this.zan_type = zan_type;
        }
    }
}
