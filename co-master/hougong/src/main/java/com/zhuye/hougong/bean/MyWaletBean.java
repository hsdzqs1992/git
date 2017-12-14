package com.zhuye.hougong.bean;

/**
 * Created by zzzy on 2017/11/27.
 */

public class MyWaletBean {


    /**
     * data : {"money":"0","profile":0,"zprofile":""}
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
         * money : 0
         * profile : 0
         * zprofile :
         */

        private String money;
        private int profile;
        private String zprofile;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getProfile() {
            return profile;
        }

        public void setProfile(int profile) {
            this.profile = profile;
        }

        public String getZprofile() {
            return zprofile;
        }

        public void setZprofile(String zprofile) {
            this.zprofile = zprofile;
        }
    }
}
