package com.zhuye.hougong.fragment.jiaoyi;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class ZhiChuJlFragment extends CommotFaragment {

    int type;
    public ZhiChuJlFragment(int type) {
        this.type =type;
    }

    @Override
    protected void initData() {
        super.initData();
        getData(4);
    }
}
