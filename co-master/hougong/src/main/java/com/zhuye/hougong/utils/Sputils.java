package com.zhuye.hougong.utils;

import android.content.Context;

/**
 * Created by zzzy on 2017/11/21.
 */

public class Sputils {

    public static String getString(Context context,String key,String def){
        return context.getSharedPreferences("con",Context.MODE_PRIVATE).getString(key,def);
    }

    public static void setString(Context context,String key,String def){
        context.getSharedPreferences("con",Context.MODE_PRIVATE).edit().putString(key,def).commit();
    }

    public static Boolean getBoolean(Context context,String key,Boolean def){
        return context.getSharedPreferences("con",Context.MODE_PRIVATE).getBoolean(key,def);
    }

    public static void setBoolean(Context context,String key,Boolean def){
        context.getSharedPreferences("con",Context.MODE_PRIVATE).edit().putBoolean(key,def).commit();
    }

    public static int getInt(Context context,String key,Integer def){
        return context.getSharedPreferences("con",Context.MODE_PRIVATE).getInt(key,def);
    }

    public static void setInt(Context context,String key,int def){
        context.getSharedPreferences("con",Context.MODE_PRIVATE).edit().putInt(key,def).commit();
    }
}
