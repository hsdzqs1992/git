package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class VipListbean {
    /**
     * data : [{"vip_id":"1","money":"0.01","month":"1","photo":"/Uploads/Picture/2017-11-10/5a0525980501b.png"},{"vip_id":"2","money":"30.00","month":"3","photo":"/Uploads/Picture/2017-11-10/5a0528907997e.png"},{"vip_id":"3","money":"60.00","month":"6","photo":"/Uploads/Picture/2017-11-10/5a0528a239ef8.png"},{"vip_id":"4","money":"108.00","month":"12","photo":"/Uploads/Picture/2017-11-10/5a0528b130291.png"}]
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
         * vip_id : 1
         * money : 0.01
         * month : 1
         * photo : /Uploads/Picture/2017-11-10/5a0525980501b.png
         */

        private String vip_id;
        private String money;
        private String month;
        private String photo;

        public String getVip_id() {
            return vip_id;
        }

        public void setVip_id(String vip_id) {
            this.vip_id = vip_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
