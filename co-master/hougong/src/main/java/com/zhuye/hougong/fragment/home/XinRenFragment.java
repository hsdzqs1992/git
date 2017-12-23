package com.zhuye.hougong.fragment.home;

import android.support.v7.widget.GridLayoutManager;

import com.zhuye.hougong.adapter.home.HomeXinRenAdapter;
import com.zhuye.hougong.weidgt.DividerGridViewItemDecoration2;

/**
 * Created by zzzy on 2017/11/21.
 */

public class XinRenFragment extends BaseHomeFragment2 {


    @Override
    protected void initView() {
        super.initView();

        homeTuiJianAdapter = new HomeXinRenAdapter(getActivity());
        recyclerView.addHeaderView(slideview);
        GridLayoutManager grid =new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.addItemDecoration(new DividerGridViewItemDecoration2(getActivity(),true));

    }

    @Override
    protected void initData() {
        super.initData();
        getReData(3,page);
    }
    protected int page = 1;
    @Override
    protected void loadmoreownData(int type, int page) {
        getReData(3,page);
    }

    @Override
    protected void refreshownData(int type, int i) {
        getReData(3,1);
    }

}
