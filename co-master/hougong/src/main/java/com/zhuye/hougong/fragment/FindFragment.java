package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.FindPagerAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.view.FaBuActivity;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class FindFragment extends BaseFragment {
   // private MyToolbar myToolbar;
    private ViewPager mviewpager;

    private PagerSlidingTabStrip mTabStrip;
    private ImageView fabu;
    FindPagerAdapter finAdapter;
    @Override
    protected void initView() {
       //myToolbar = rootView.findViewById(R.id.find_toolbar);

        //initToolBar();
        mviewpager = rootView.findViewById(R.id.find_viewpager);

        mTabStrip=rootView.findViewById(R.id.tab_strip_find);
        fabu=rootView.findViewById(R.id.message);
        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h8));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);

        finAdapter = new FindPagerAdapter(getChildFragmentManager());
        mviewpager.setAdapter(finAdapter);
        mTabStrip.setViewPager(mviewpager);

    }

    @Override
    protected void initData() {
        super.initData();
        //初始化关注数据
    }
    @Override
    protected void initListener() {
        super.initListener();
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FaBuActivity.class));
            }
        });


    }

    @Override
    protected int getResId() {
        return R.layout.fragment_find;
    }
}
