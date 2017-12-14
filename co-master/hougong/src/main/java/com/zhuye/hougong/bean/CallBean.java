package com.zhuye.hougong.bean;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class CallBean {
    /**
     * code : 200
     * data : {"money":200}
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
        /**
         * money : 200
         */

        private float money;

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }
    }
}
