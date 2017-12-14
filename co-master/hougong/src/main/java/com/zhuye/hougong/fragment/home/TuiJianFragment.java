package com.zhuye.hougong.fragment.home;

import com.zhuye.hougong.adapter.home.HomeTuiJianAdapter;
import com.zhuye.hougong.weidgt.MyGridLayoutManager;

/**
 * Created by zzzy on 2017/11/21.
 */

public class TuiJianFragment extends BaseHomeFragment {


    @Override
    protected void initView() {
        super.initView();

        homeTuiJianAdapter = new HomeTuiJianAdapter(getActivity());
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.setLayoutManager(new MyGridLayoutManager(getActivity(),2));

    }

    protected int page = 1;
    @Override
    protected void initData() {
        super.initData();

        getReData(1,page);
    }

    @Override
    protected void loadmoreownData(int type, int page) {
        getReData(1,page);
    }

    @Override
    protected void refreshownData(int type, int i) {
        getReData(1,1);
    }


}
