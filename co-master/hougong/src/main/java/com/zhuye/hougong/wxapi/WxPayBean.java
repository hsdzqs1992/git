package com.zhuye.hougong.wxapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class WxPayBean {
    /**
     * data : {"appid":"wxddf17683ec437cfa","noncestr":"uJUVc2sS6IF1weDxjyWaNaH7Q1v7NBqn","package":"Sign=WXPay","partnerid":"1493625572","prepayid":"wx20171207183026f16234ffdf0846865325","timestamp":1512642628,"sign":"CED90501B987399B32DF88FC4A906D0F"}
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
         * appid : wxddf17683ec437cfa
         * noncestr : uJUVc2sS6IF1weDxjyWaNaH7Q1v7NBqn
         * package : Sign=WXPay
         * partnerid : 1493625572
         * prepayid : wx20171207183026f16234ffdf0846865325
         * timestamp : 1512642628
         * sign : CED90501B987399B32DF88FC4A906D0F
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
