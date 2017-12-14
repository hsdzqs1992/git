package com.zhuye.hougong.bean;

/**
 * Created by admin on 2017/11/26.
 */

public class UserInfo {
    private String userName;
    private String hxid;
    private String nickname;
    private String photo;
    private Integer age;
    private String sex;
    private String city;

    public UserInfo() {

    }

    public UserInfo(String userName) {
        this.userName = userName;
        this.hxid = userName;
        this.nickname = userName;
        this.photo = userName;
    }

    public UserInfo(String userName, String hxid, String nickname, String photo) {
        this.userName = userName;
        this.hxid = hxid;
        this.nickname = nickname;
        this.photo = photo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
