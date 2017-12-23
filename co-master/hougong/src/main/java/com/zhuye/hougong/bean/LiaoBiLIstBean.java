package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class LiaoBiLIstBean {
    /**
     * data : [{"id":"1","money":"0.01","liaobi":"100"},{"id":"2","money":"30.00","liaobi":"303"},{"id":"3","money":"50.00","liaobi":"501"},{"id":"4","money":"100.00","liaobi":"1030"},{"id":"5","money":"200.00","liaobi":"2080"},{"id":"6","money":"500.00","liaobi":"5250"},{"id":"7","money":"1000.00","liaobi":"10600"},{"id":"8","money":"2000.00","liaobi":"21400"},{"id":"9","money":"5000.00","liaobi":"54000"}]
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
        public Boolean getIssected() {
            return issected;
        }

        public void setIssected(Boolean issected) {
            this.issected = issected;
        }

        /**
         * id : 1
         * money : 0.01
         * liaobi : 100
         */

        private Boolean issected;
        private String id;
        private String money;
        private String liaobi;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getLiaobi() {
            return liaobi;
        }

        public void setLiaobi(String liaobi) {
            this.liaobi = liaobi;
        }
    }
}
