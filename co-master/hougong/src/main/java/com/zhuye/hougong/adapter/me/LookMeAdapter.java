package com.zhuye.hougong.adapter.me;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.LookBean;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class LookMeAdapter extends BaseRecycleAdapter {

    public LookMeAdapter(Context conn) {
        super(conn);
    }

    public LookMeAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.look_me_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ((TextView)holder.getView(R.id.lookme_name)).setText(((LookBean.DataBean)data.get(position)).getNickname());
        ((TextView)holder.getView(R.id.lookme_age)).setText(((LookBean.DataBean)data.get(position)).getAge());
        ((TextView)holder.getView(R.id.lookme_time)).setText(((LookBean.DataBean)data.get(position)).getCreate_time());
        ImageView iv =  ((ImageView) holder.getView(R.id.sex));
        if(((LookBean.DataBean)data.get(position)).getSex().equals("1")){
            iv.setImageResource(R.drawable.miss);
        }else if(((LookBean.DataBean)data.get(position)).getSex().equals("2")){
            iv.setImageResource(R.drawable.boy);
        }

        ImageView iv1 =  ((ImageView) holder.getView(R.id.lookme_touxiang));
        Glide.with(conn).load(Contants.BASE_URL+((LookBean.DataBean)data.get(position)).getFace()).into(iv1);


    }

}
