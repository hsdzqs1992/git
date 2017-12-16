package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.fragment.paihang.CaiFuFragment;
import com.zhuye.hougong.fragment.paihang.MeiLiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/20.
 */

public class PaiHangAdapter2 extends FragmentPagerAdapter {

    List<BaseFragment>  fragments = new ArrayList<>(2);
    public PaiHangAdapter2(FragmentManager fm,List<String> titles) {
        super(fm);
        this.titles = titles;
        fragments.add(new MeiLiFragment());
        fragments.add(new CaiFuFragment());
    }

    List<String> titles;
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
