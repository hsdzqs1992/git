package com.zhuye.hougong.adapter.home;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by zzzy on 2017/12/1.
 */

public class OtherPersonAdapter extends BaseRecycleAdapter {

    public OtherPersonAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.other;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
       ImageView iv =  holder.getView(R.id.ivv);
        Glide.with(conn).load(data.get(position)).into(iv);

    }


}
