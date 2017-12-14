package com.zhuye.hougong.bean;

import java.util.List;

/**
 * Created by zzzy on 2017/11/29.
 */

public class HistoryBean {

    /**
     * data : ["美女美女","meinv","hah"]
     * message :
     * code : 200
     */

    private String message;
    private String code;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
