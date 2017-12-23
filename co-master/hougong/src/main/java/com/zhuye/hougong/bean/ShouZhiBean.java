package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class ShouZhiBean {
    /**
     * code : 200
     * data : [{"balance":130,"content":"接听收益","create_time":"2017-12-14 17:12:15","money":0},{"balance":140,"content":"接听收益","create_time":"2017-12-14 17:12:37","money":10},{"balance":195,"content":"接听收益","create_time":"2017-12-14 17:12:58","money":65}]
     * message :
     */

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
        /**
         * balance : 130
         * content : 接听收益
         * create_time : 2017-12-14 17:12:15
         * money : 0
         */

        private int balance;
        private String content;
        private String create_time;
        private int money;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
