package com.zhuye.hougong.adapter;

import android.content.Context;

import com.zhuye.hougong.R;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class CommontAdapter extends BaseRecycleAdapter {
    public CommontAdapter(Context conn) {
        super(conn);
    }

    public CommontAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.common_recycle_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }


}
