package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class PhotoBean {

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
         * l_id : 64
         * photo : /Uploads/Picture/2017-12-11/video.png
         */

        private String l_id;
        private String photo;

        public String getL_id() {
            return l_id;
        }

        public void setL_id(String l_id) {
            this.l_id = l_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
