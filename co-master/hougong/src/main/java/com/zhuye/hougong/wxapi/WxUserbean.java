package com.zhuye.hougong.wxapi;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class WxUserbean {
    /**
     * city : Zhengzhou
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7mAt63VrbLaictyh1H6QWaUfI9PJicC6nKdH6JSE7TBJIp5Dg8EIw2npibhxFiaOEf6Ds1goBGmGibLw/0
     * language : zh_CN
     * nickname : 流水
     * openid : or_rR1ZasKkstEzUgTH1tuYN32VM
     * privilege : []
     * province : Henan
     * sex : 1
     * unionid : on1Qkw2J92FtIt43fmS1GLYXGvOM
     */

    private String city;
    private String country;
    private String headimgurl;
    private String language;
    private String nickname;
    private String openid;
    private String province;
    private int sex;
    private String unionid;
    private List<?> privilege;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
