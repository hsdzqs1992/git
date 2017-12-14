package com.zhuye.hougong.fragment.paihang;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class CaiFuFragment extends BasePaiHangFragment {


    @Override
    protected void initData() {
        super.initData();
        type1 = 4;
        day = 1;
        getBangData(1,type1,day);

    }

    @Override
    protected void onClickMonth() {
        type1 = 4;
        day = 3;
        getBangData(1,type1,day);

    }

    @Override
    protected void onClickZhou() {
        type1 = 4;
        day = 2;
        getBangData(1,type1,day);
    }

    @Override
    protected void onClickRi() {
        type1 = 4;
        day = 1;
        getBangData(1,type1,day);
    }



}
