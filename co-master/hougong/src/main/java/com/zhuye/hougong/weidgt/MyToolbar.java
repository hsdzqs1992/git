package com.zhuye.hougong.weidgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.zhuye.hougong.R;

/**
 * Created by zzzy on 2017/11/20.
 */

public class MyToolbar extends Toolbar {

    public TextView homeLeftIcon;

    public TextView homeRightIcon;
    public PagerSlidingTabStrip tabs;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.toolbar, null);
        homeLeftIcon = view.findViewById(R.id.home_left_icon);
        homeRightIcon = view.findViewById(R.id.home_right_icon);
        tabs = view.findViewById(R.id.tabs);
        addView(view);
    }

    public void hideView(View view){
        view.setVisibility(View.INVISIBLE);
    }
    public void showView(View view){
        view.setVisibility(View.VISIBLE);
    }
}
