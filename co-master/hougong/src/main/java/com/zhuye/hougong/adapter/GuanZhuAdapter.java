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
 * Created by zzzy on 2017/11/21.
 */

public class GuanZhuAdapter extends BaseRecycleAdapter {

    public GuanZhuAdapter(Context conn ){
        super(conn);
    }

    public GuanZhuAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.guanzhu_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ((TextView)holder.getView(R.id.guanzhu_item_tv)).setText(((MyFriendsBean.DataBean)data.get(position)).getNickname());
        ImageView iv= ((ImageView)holder.getView(R.id.guanzhu_item_iv));
        Glide.with(conn).load(Contants.BASE_URL+((MyFriendsBean.DataBean)data.get(position)).getFace()).into(iv);


    }




}
