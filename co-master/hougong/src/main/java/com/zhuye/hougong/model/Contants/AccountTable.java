package com.zhuye.hougong.model.Contants;

/**
 * Created by admin on 2017/11/26.
 */

public class AccountTable {

    public final static String AccountTable = "accout";
    public final static String col_name = "name";
    public final static String col_hxid = "hxid";
    public final static String col_nick = "nick";
    public final static String col_photo = "photo";

    public final static String col_sex = "sex";
    public final static String col_age = "age";
    public final static String col_city = "city";


    public final static String Create_table = "create table " +AccountTable + " ("
            + col_name + " text primary key ,"
            + col_hxid + " text ,"
            + col_nick + " text ,"
            + col_photo + " text ,"
            + col_sex + " text ,"
            + col_age + " text ,"
            + col_city + " text);";
}
