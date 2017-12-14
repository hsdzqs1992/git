package com.zhuye.hougong.weidgt;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by zzzy on 2017/11/28.
 */

public class MyLineLayoutManager extends LinearLayoutManager {
    public MyLineLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyLineLayoutManager(Context context) {
        super(context);
    }

    public MyLineLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
