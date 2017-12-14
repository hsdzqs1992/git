package com.zhuye.hougong.fragment.home;

import com.zhuye.hougong.adapter.home.HomeMianFeiAdapter;
import com.zhuye.hougong.weidgt.MyGridLayoutManager;

/**
 * Created by zzzy on 2017/11/21.
 */

public class MianFeiFragment extends BaseHomeFragment {

    @Override
    protected void initView() {
        super.initView();
        homeTuiJianAdapter = new HomeMianFeiAdapter(getActivity());
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.setLayoutManager(new MyGridLayoutManager(getActivity(),2));

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
