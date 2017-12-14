package com.zhuye.hougong.utils;

import android.content.Context;

/**
 * Created by zzzy on 2017/11/25.
 */

public class DensityUtil {


    public static int dip2px(Context context,float dipValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
}
