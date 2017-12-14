package com.zhuye.hougong.adapter.me;

import android.content.Context;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class WhoSendAdapter extends BaseRecycleAdapter {

    public WhoSendAdapter(Context conn) {
        super(conn);
    }

    public WhoSendAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.me_who_send;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }

}
