package com.zhuye.hougong.adapter.message;

import android.content.Context;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

/**
 * Created by zzzy on 2017/12/5.
 */

public class TongHuaListAdapter extends BaseRecycleAdapter {

    public TongHuaListAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.message_tonhua_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }
}
