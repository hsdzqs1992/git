package com.zhuye.hougong.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class BlackNumberAdapter extends BaseRecycleAdapter {

    public BlackNumberAdapter(Context conn) {
        super(conn);
    }

    public BlackNumberAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.blacknumber_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ((TextView)holder.getView(R.id.blacknumber_name)).setText(((MyFriendsBean.DataBean)data.get(position)).getNickname());
        ImageView iv =  ((ImageView) holder.getView(R.id.blacknumber_iv));
        Glide.with(conn).load(Contants.BASE_URL+((MyFriendsBean.DataBean)data.get(position)).getFace()).into(iv);
    }

}
