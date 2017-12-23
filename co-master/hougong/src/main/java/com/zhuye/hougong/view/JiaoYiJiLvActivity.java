package com.zhuye.hougong.view;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.jiaoyi.JiaoYiJiLvAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.OnClick;

public class JiaoYiJiLvActivity extends BaseActivity {


    @BindView(R.id.jiiiaoyijilv_viewpager)
    ViewPager jiiiaoyijilvViewpager;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.activity_jiao_yi_ji_lv)
    LinearLayout activityJiaoYiJiLv;


    @Override
    protected void initview() {
        super.initview();
        tabStrip.setTextColorResource(R.color.liu);
        tabStrip.setIndicatorColorResource(R.color.tab_blue_bg);
        tabStrip.setDividerColor(Color.TRANSPARENT);
        tabStrip.setTextSelectedColorResource(R.color.tab_blue_bg);
        tabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h10));
        tabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        tabStrip.setUnderlineHeight(1);
        jiaoYiJiLvAdapter = new JiaoYiJiLvAdapter(getSupportFragmentManager());
        jiiiaoyijilvViewpager.setAdapter(jiaoYiJiLvAdapter);
        tabStrip.setViewPager(jiiiaoyijilvViewpager);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }
    JiaoYiJiLvAdapter jiaoYiJiLvAdapter;
    @Override
    protected int getResId() {
        return R.layout.activity_jiao_yi_ji_lv;
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected ImmersionBar mImmersionBar;
    protected boolean isImmersionBarEnabled() {
        return true;
    }
}
