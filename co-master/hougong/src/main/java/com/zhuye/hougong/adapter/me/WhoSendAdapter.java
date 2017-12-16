package com.zhuye.hougong.adapter.me;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.SongLiBean;
import com.zhuye.hougong.contants.Contants;

/**
 * Created by zzzy on 2017/11/22.
 */

public class WhoSendAdapter extends BaseRecycleAdapter {

    public WhoSendAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.me_who_send;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        ((TextView)holder.getView(R.id.whosend_name)).setText(((SongLiBean.DataBean)data.get(position)).getNickname());


       ((TextView)holder.getView(R.id.whosend_age)).setText(((SongLiBean.DataBean)data.get(position)).getAge());
        if(((SongLiBean.DataBean)data.get(position)).getSex().equals("1")){
            Drawable drawable = conn.getResources().getDrawable(R.drawable.miss);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView)holder.getView(R.id.whosend_age)).setCompoundDrawables(drawable, null, null, null);
        }else if(((SongLiBean.DataBean)data.get(position)).getSex().equals("2")){
            Drawable drawable = conn.getResources().getDrawable(R.drawable.boy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView)holder.getView(R.id.whosend_age)).setCompoundDrawables(drawable, null, null, null);
        }
        ImageView iv1 =  ((ImageView) holder.getView(R.id.whosend_touxiang));
        Glide.with(conn).load(Contants.BASE_URL+((SongLiBean.DataBean)data.get(position)).getFace()).into(iv1);


        RecyclerView rv = holder.getView(R.id.whosend_liwu);
        rv.setAdapter(new LieuLinapapter(conn,(((SongLiBean.DataBean) data.get(position)).getGift())));
        rv.setLayoutManager(new LinearLayoutManager(conn,LinearLayoutManager.HORIZONTAL,true));
    }

}
