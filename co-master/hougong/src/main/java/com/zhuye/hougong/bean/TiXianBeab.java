package com.zhuye.hougong.bean;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class TiXianBeab {
    /**
     * data : {"liaobi":"1100","money":77}
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
         * liaobi : 1100
         * money : 77
         */

        private String liaobi;
        private int money;

        public String getLiaobi() {
            return liaobi;
        }

        public void setLiaobi(String liaobi) {
            this.liaobi = liaobi;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
