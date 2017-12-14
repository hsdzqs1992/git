package com.zhuye.hougong.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhuye.hougong.model.Contants.AccountTable;

/**
 * Created by admin on 2017/11/26.
 */

public class AccountDB extends SQLiteOpenHelper {


    public AccountDB(Context context) {
        super(context, "account.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AccountTable.Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
