package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.PingLunBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class PingLunAdapter extends BaseRecycleAdapter {

    public PingLunAdapter(Context conn) {
        super(conn);
    }

    public PingLunAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.find_pinglun_rv_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        //int i = 1;
        RoundedCornerImageView iv =  holder.getView(R.id.piniglun_item_touxiang);
        Glide.with(conn).load(Contants.BASE_URL+((PingLunBean.DataBean)data.get(position)).getPing_face()).
                placeholder(R.mipmap.ic_launcher).error(R.drawable.bg_vip).into(iv);

        ((TextView)holder.getView(R.id.piniglun_item_name)).setText((((PingLunBean.DataBean) data.get(position)).getPing_nickname()));
        ((TextView)holder.getView(R.id.piniglun_item_content)).setText((((PingLunBean.DataBean) data.get(position)).getContent()));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dat = sdf.format(Long.parseLong(((PingLunBean.DataBean)data.get(position)).getCreate_time()));
        ((TextView)holder.getView(R.id.piniglun_item_time)).setText((dat));

        //无回复字段
        ((TextView)holder.getView(R.id.piniglun_item_huifu)).setText((((PingLunBean.DataBean) data.get(position)).getZan()+"回复"));


        ((TextView)holder.getView(R.id.piniglun_item_zanshu)).setText((((PingLunBean.DataBean) data.get(position)).getZan()+""));


        if(((PingLunBean.DataBean) data.get(position)).getZan_type()==0){
            ((ImageView)holder.getView(R.id.piniglun_item_dianzantubiao)).setImageResource(R.drawable.praise_of);
        }else{
            ((ImageView)holder.getView(R.id.piniglun_item_dianzantubiao)).setImageResource(R.drawable.praise_on);
        }


    }
}
