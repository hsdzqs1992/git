package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/11/20.
 */

public class HomeBanner {

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
         * img : /Uploads/Picture/2017-11-15/5a0bacffa6e49.png
         * url : http://www.baidu.com
         */

        private String img;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
