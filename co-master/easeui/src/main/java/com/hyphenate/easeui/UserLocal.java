package com.hyphenate.easeui;


import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class UserLocal extends DataSupport {

    public String getHxname() {
        return hxname;
    }

    public void setHxname(String hxname) {
        this.hxname = hxname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacepath() {
        return facepath;
    }

    public void setFacepath(String facepath) {
        this.facepath = facepath;
    }

    @Column(unique = true,nullable = false)
    private String hxname;
    private String name;
    private String facepath;

}
