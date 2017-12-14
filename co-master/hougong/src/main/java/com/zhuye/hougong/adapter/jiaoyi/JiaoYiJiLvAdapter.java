package com.zhuye.hougong.adapter.jiaoyi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.fragment.jiaoyi.ChongZhiJlFragment;
import com.zhuye.hougong.fragment.jiaoyi.TiXianJlFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class JiaoYiJiLvAdapter extends FragmentStatePagerAdapter {

    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fargments = new ArrayList<>();
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public JiaoYiJiLvAdapter(FragmentManager fm) {
        super(fm);
        titles.add("充值记录");
        titles.add("提现记录");

        fargments.add(new ChongZhiJlFragment(1));
        fargments.add(new TiXianJlFragment(2));
    }

    @Override
    public Fragment getItem(int position) {
        return fargments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}
