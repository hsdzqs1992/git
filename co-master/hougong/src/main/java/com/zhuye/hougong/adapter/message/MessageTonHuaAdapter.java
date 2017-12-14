package com.zhuye.hougong.adapter.message;

import android.content.Context;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class MessageTonHuaAdapter extends BaseRecycleAdapter {

    public MessageTonHuaAdapter(Context conn) {
        super(conn);
    }

    public MessageTonHuaAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.message_tonhua_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }


}
