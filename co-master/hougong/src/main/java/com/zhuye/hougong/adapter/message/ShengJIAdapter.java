package com.zhuye.hougong.adapter.message;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.VipListbean;
import com.zhuye.hougong.contants.Contants;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class ShengJIAdapter extends BaseRecycleAdapter {

    public ShengJIAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.image;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ImageView iv = holder.getView(R.id.image);
        Glide.with(conn).load(Contants.BASE_URL+((VipListbean.DataBean)data.get(position)).getPhoto()).into(iv);
    }
}
