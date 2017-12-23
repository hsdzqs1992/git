package com.zhuye.hougong.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.jiaoyi.ShouZhiJiLvAdapter;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouZhiJiLvActivity extends AppCompatActivity {


    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.jiiiaoyijilv_viewpager)
    ViewPager jiiiaoyijilvViewpager;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_zhi_ji_lv);
        ButterKnife.bind(this);
        if (isImmersionBarEnabled())
            initImmersionBar();
        tabStrip.setTextColorResource(R.color.liu);
        tabStrip.setIndicatorColorResource(R.color.tab_blue_bg);
        tabStrip.setDividerColor(Color.TRANSPARENT);
        tabStrip.setTextSelectedColorResource(R.color.tab_blue_bg);
        tabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h10));
        tabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        tabStrip.setUnderlineHeight(1);

        ShouZhiJiLvAdapter jiaoYiJiLvAdapter = new ShouZhiJiLvAdapter(getSupportFragmentManager());
        jiiiaoyijilvViewpager.setAdapter(jiaoYiJiLvAdapter);
        tabStrip.setViewPager(jiiiaoyijilvViewpager);
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }
}
