package com.zhuye.hougong.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.PaiHangAdapter2;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public class PaiHangFragment extends BaseFragment {

    @BindView(R.id.paihang_viewpager)
    ViewPager paihangViewpager;
    Unbinder unbinder;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip paihangTablayout;
    @BindView(R.id.self_create_titlebar)
    RelativeLayout mSelfCreateTitlebar;

    private List<String> titles = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.fragment_paihang;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        titles.add("魅力榜");
        titles.add("财富榜");
        return rootView;
    }

    @Override
    protected void initView() {

        paihangTablayout.setTextColorResource(R.color.white);
        paihangTablayout.setIndicatorColorResource(R.color.tab_blue_bg);
        paihangTablayout.setDividerColor(Color.TRANSPARENT);
        paihangTablayout.setTextSelectedColorResource(R.color.white);
        paihangTablayout.setTextSize(getResources().getDimensionPixelSize(R.dimen.h10));
        paihangTablayout.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        paihangTablayout.setUnderlineHeight(1);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initData() {

        try {
            Class<?> tablayout = paihangTablayout.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(paihangTablayout);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.setMarginStart(DensityUtil.dip2px(getActivity(), 20f));
                params.setMarginEnd(DensityUtil.dip2px(getActivity(), 20f));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        paihangViewpager.setAdapter(new PaiHangAdapter2(getChildFragmentManager(), titles));
        paihangTablayout.setViewPager(paihangViewpager);

        getDatafromFerver();
    }

    private void getDatafromFerver() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        paihangViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==0){
                    mSelfCreateTitlebar.setBackgroundResource(R.drawable.paihang_bg);
                }else if(position==1){
                    mSelfCreateTitlebar.setBackgroundResource(R.drawable.paihang_bg2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
