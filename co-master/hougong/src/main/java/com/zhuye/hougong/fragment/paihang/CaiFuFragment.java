package com.zhuye.hougong.fragment.paihang;

import android.view.View;

import com.zhuye.hougong.R;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class CaiFuFragment extends BasePaiHangFragment2 {


    @Override
    protected void initData() {
        super.initData();
        header.setBackgroundResource(R.drawable.paihang_bg2);
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


    @Override
    public void onClick(View view) {

    }
}
