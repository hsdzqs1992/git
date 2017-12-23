package com.zhuye.hougong.fragment.home;

import android.support.v7.widget.GridLayoutManager;

import com.zhuye.hougong.adapter.home.HomeMianFeiAdapter;
import com.zhuye.hougong.weidgt.DividerGridViewItemDecoration2;

/**
 * Created by zzzy on 2017/11/21.
 */

public class MianFeiFragment extends BaseHomeFragment2 {

    @Override
    protected void initView() {
        super.initView();
        homeTuiJianAdapter = new HomeMianFeiAdapter(getActivity());
        recyclerView.addHeaderView(slideview);
        GridLayoutManager grid =new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.addItemDecoration(new DividerGridViewItemDecoration2(getActivity(),true));

    }

    @Override
    protected void initData() {
        super.initData();
        getReData(4,page);
    }

    protected int page = 1;
    @Override
    protected void loadmoreownData(int type, int page) {
        getReData(4,page);
    }

    @Override
    protected void refreshownData(int type, int i) {
        getReData(4,1);
    }


}
