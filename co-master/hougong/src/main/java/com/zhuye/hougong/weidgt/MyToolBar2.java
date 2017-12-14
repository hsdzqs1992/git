package com.zhuye.hougong.weidgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.hougong.R;

/**
 * Created by zzzy on 2017/11/21.
 */

public class MyToolBar2 extends Toolbar {

    public ImageView homeLeftIcon;

    public ImageView homeRightIcon;
    public TextView titles;

    public MyToolBar2(Context context) {
        this(context, null);
    }

    public MyToolBar2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.toolbar2, null);
        homeLeftIcon = view.findViewById(R.id.home_left_icon2);
        homeRightIcon = view.findViewById(R.id.home_right_icon2);
        titles = view.findViewById(R.id.tabs2);
        addView(view);
    }

    public void hideView(View view){
        view.setVisibility(View.INVISIBLE);
    }
    public void showView(View view){
        view.setVisibility(View.VISIBLE);
    }
}
