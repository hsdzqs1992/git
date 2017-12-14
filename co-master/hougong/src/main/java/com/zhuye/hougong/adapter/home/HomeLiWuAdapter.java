package com.zhuye.hougong.adapter.home;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.PersonJiaoBean;
import com.zhuye.hougong.contants.Contants;

/**
 * Created by zzzy on 2017/12/5.
 */

public class HomeLiWuAdapter extends BaseRecycleAdapter {


    public HomeLiWuAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.zhuye_liwu_tv;
    }
    @Override
    protected void conver(BaseHolder holder, int position) {
        ImageView iv = holder.getView(R.id.person_home_liwu);
        Glide.with(conn).load(Contants.BASE_URL+((PersonJiaoBean.DataBean.GiftBean)data.get(position)).
                getPhoto()).
                placeholder(R.mipmap.ic_launcher)
        .error(R.drawable.gift_1).into(iv);

    }

}
