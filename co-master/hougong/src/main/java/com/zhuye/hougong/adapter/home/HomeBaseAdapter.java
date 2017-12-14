package com.zhuye.hougong.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.HomeRecycleBean;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public  class HomeBaseAdapter extends BaseRecycleAdapter {

    public HomeBaseAdapter(Context conn) {
        super(conn);
    }

    public HomeBaseAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.home_tuijian_recycle_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        if(data != null && data.size() > 0 ){

            if(data.get(position) instanceof HomeRecycleBean.DataBean){
                ((TextView)holder.getView(R.id.home_tuijian_item_zhuboname)).setText(((HomeRecycleBean.DataBean)data.get(position)).getNickname()+"");
                ((TextView)holder.getView(R.id.home_tuijian_item_zhuboage)).setText(((HomeRecycleBean.DataBean)data.get(position)).getAge()+"");

                // 设置关注
                int love = ((HomeRecycleBean.DataBean)data.get(position)).getLove();
                if(love  == 0){
                    ((ImageView)holder.getView(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_off);
                }else if(love == 1) {
                    ((ImageView)holder.getView(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);


                }
                // 设置头像
                ImageView ivew = ((ImageView)holder.getView(R.id.home_tuijian_item_touxiang));
                Glide.with(conn).load(Contants.BASE_URL + ((HomeRecycleBean.DataBean)data.get(position)).getFace()).
                        placeholder(R.mipmap.ic_launcher).error(R.drawable.bg_vip).into(ivew);

                //是否在线
                String status = ((HomeRecycleBean.DataBean)data.get(position)).getStealth();
                if(status.equals("0")){
                    ((ImageView)holder.getView(R.id.home_tuijian_item_status)).setImageResource(R.drawable.on_line);
                }else if(status.equals("1")){
                    ((ImageView)holder.getView(R.id.home_tuijian_item_status)).setImageResource(R.drawable.off_line);
                }
            }

        }
    }


}
