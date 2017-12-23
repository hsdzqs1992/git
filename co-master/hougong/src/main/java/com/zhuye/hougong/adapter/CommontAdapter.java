package com.zhuye.hougong.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.ShouZhiBean;

/**
 * Created by zzzy on 2017/11/22.
 */

public class CommontAdapter extends BaseRecycleAdapter {
    public CommontAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.common_recycle_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

       TextView tv =  holder.getView(R.id.common_recycle_title);
        tv.setText(((ShouZhiBean.DataBean)data.get(position)).getContent());

        TextView dat =  holder.getView(R.id.common_recycle_data);
        dat.setText(((ShouZhiBean.DataBean)data.get(position)).getCreate_time());

        TextView tv3 =  holder.getView(R.id.common_recycle_shuliang);

        tv3.setText("余额"+((ShouZhiBean.DataBean)data.get(position)).getBalance());
        TextView tv4 =  holder.getView(R.id.common_recycle_jine);
        tv4.setText(((ShouZhiBean.DataBean)data.get(position)).getMoney()+"");

    }


}
