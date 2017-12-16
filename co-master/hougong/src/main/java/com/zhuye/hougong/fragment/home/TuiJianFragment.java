package com.zhuye.hougong.fragment.home;

import android.support.v7.widget.GridLayoutManager;

import com.zhuye.hougong.adapter.home.HomeTuiJianAdapter;
import com.zhuye.hougong.weidgt.DividerGridViewItemDecoration2;

/**
 * Created by zzzy on 2017/11/21.
 */

public class TuiJianFragment extends BaseHomeFragment2 {


    @Override
    protected void initView() {
        super.initView();

        homeTuiJianAdapter = new HomeTuiJianAdapter(getActivity());
        recyclerView.addHeaderView(slideview);

        GridLayoutManager grid =new GridLayoutManager(getActivity(),2);
//        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return getItemViewType(position) == RecyclerView.INVALID_TYPE
//                        ? gridManager.getSpanCount() : 1;
//            }
//        });
        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(homeTuiJianAdapter);
        recyclerView.addItemDecoration(new DividerGridViewItemDecoration2(getActivity(),true));

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
