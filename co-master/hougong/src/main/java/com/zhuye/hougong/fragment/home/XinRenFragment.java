package com.zhuye.hougong.fragment.home;

import com.zhuye.hougong.adapter.home.HomeXinRenAdapter;
import com.zhuye.hougong.weidgt.MyGridLayoutManager;

/**
 * Created by zzzy on 2017/11/21.
 */

public class XinRenFragment extends BaseHomeFragment {


    @Override
    protected void initView() {
        super.initView();

        homeTuiJianAdapter = new HomeXinRenAdapter(getActivity());
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.setLayoutManager(new MyGridLayoutManager(getActivity(),2));

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
