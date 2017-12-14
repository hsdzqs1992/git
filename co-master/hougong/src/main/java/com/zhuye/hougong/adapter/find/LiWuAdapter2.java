package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by admin on 2017/12/2.
 */

public class LiWuAdapter2  extends BaseRecycleAdapter{
    public LiWuAdapter2(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.liwu_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ImageView iv = holder.getView(R.id.iv);
        Glide.with(conn).load(Contants.BASE_URL+((LiWu.DataBean)data.get(position)).getPhoto()).into(iv);
        ((TextView) holder.getView(R.id.name)).setText(((LiWu.DataBean)data.get(position)).getName());
        ((TextView) holder.getView(R.id.price)).setText(((LiWu.DataBean)data.get(position)).getMoney());
    }

}
