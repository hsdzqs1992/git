package com.zhuye.hougong.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.zhuye.hougong.bean.UserInfo;
import com.zhuye.hougong.model.Contants.AccountTable;
import com.zhuye.hougong.model.db.AccountDB;

/**
 * Created by admin on 2017/11/26.
 */

public class AccoutDao  {

    Context conn;
    AccountDB accountDB;

    public AccoutDao(Context con) {
        conn = con;
        accountDB = new AccountDB(conn);
    }

    public void saveUser(UserInfo user){
        SQLiteDatabase readableDatabase = accountDB.getReadableDatabase();

        if (user==null){
            return;
        }
        ContentValues values =  new ContentValues();
        values.put(AccountTable.col_name,user.getUserName());
        values.put(AccountTable.col_hxid,user.getHxid());
        values.put(AccountTable.col_nick,user.getNickname());
        values.put(AccountTable.col_photo,user.getPhoto());
        readableDatabase.replace(AccountTable.AccountTable,null,values);
    }

    public UserInfo findUser(String username){

        if (username == null || TextUtils.isEmpty(username)){
            return null;
        }
        SQLiteDatabase readableDatabase = accountDB.getReadableDatabase();
        String sql = "select * from " + AccountTable.AccountTable + " where " + AccountTable.col_name+" =?";
        //readableDatabase.execSQL(sql,new String[]{username});
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{username});

        UserInfo userInfo=null;
        if (cursor.moveToNext()){

            userInfo = new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(AccountTable.col_hxid)));
            userInfo.setNickname(cursor.getString(cursor.getColumnIndex(AccountTable.col_nick)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(AccountTable.col_photo)));
            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(AccountTable.col_name)));
        }

        //关闭游标
        cursor.close();

        return userInfo;
    }
}
