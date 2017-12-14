package com.zhuye.hougong.adapter.me;

import android.content.Context;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class LookMeAdapter extends BaseRecycleAdapter {

    public LookMeAdapter(Context conn) {
        super(conn);
    }

    public LookMeAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.look_me_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }

}
