package com.zhuye.hougong.bean;

/**
 * Created by zzzy on 2017/11/21.
 */

public class LoginCode {
    /**
     * data : {"token":"fdf1e33f56818b0c93293cd2c9b1d9b7"}
     * message : 登陆成功
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
         * token : fdf1e33f56818b0c93293cd2c9b1d9b7
         */

        private String token;
        private String hx_password;

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public String getHx_password() {
            return hx_password;
        }

        public void setHx_password(String hx_password) {
            this.hx_password = hx_password;
        }

        private String hx_username;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
