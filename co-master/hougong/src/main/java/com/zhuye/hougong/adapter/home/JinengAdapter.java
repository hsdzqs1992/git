package com.zhuye.hougong.adapter.home;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

/**
 * Created by zzzy on 2017/12/5.
 */

public class JinengAdapter extends BaseRecycleAdapter {

    public JinengAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.zhuye_tv;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ((TextView)holder.getView(R.id.name)).setText(""+data.get(position));
    }

}
