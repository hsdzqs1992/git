package com.zhuye.hougong.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zzzy on 2017/11/24.
 */

public class CommentUtils {
    public static void toast(Context context, String values){
        Toast.makeText(context,values,Toast.LENGTH_SHORT).show();
    }
}
