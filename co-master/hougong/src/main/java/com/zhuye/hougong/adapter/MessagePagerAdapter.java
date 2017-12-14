package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.hougong.fragment.message.ChatFragment;
import com.zhuye.hougong.fragment.message.TongHuaListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class MessagePagerAdapter extends FragmentPagerAdapter {


    private List<String> titles = new ArrayList<>();
    private List<Fragment> fargments = new ArrayList<>();

    public MessagePagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("消息");
        titles.add("通话");

        fargments.add(new ChatFragment());
        fargments.add(new TongHuaListFragment());
    }





    @Override
    public Fragment getItem(int position) {
        return fargments.get(position);
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
