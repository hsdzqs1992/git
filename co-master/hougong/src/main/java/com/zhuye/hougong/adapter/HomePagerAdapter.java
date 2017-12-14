package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.fragment.home.HuoYueFragment;
import com.zhuye.hougong.fragment.home.MianFeiFragment;
import com.zhuye.hougong.fragment.home.TuiJianFragment;
import com.zhuye.hougong.fragment.home.XinRenFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {


    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fargments = new ArrayList<>();
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("推荐");
        titles.add("活跃");
        titles.add("新人");
        titles.add("免费");
        fargments.add(new TuiJianFragment());
        fargments.add(new HuoYueFragment());
        fargments.add(new XinRenFragment());
        fargments.add(new MianFeiFragment());

    }
    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment =fargments.get(position);
        return baseFragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
