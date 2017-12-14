package com.zhuye.hougong.adapter.paihang;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.PaiHangBean;
import com.zhuye.hougong.contants.Contants;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class PaiHangAdapter extends BaseRecycleAdapter {

    public PaiHangAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.paihang_shengyu_bangdan;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        ((TextView)holder.getView(R.id.paihang_shengyu_zimu)).setText(position+4+"");

        ImageView iv = ((ImageView)holder.getView(R.id.paihang_sy_touxiang));
        Glide.with(conn).load(Contants.BASE_URL + ((PaiHangBean.DataBean)data.get(position)).getFace()).into(iv);

        ((TextView)holder.getView(R.id.paihang_sy_ageandsex)).setText(((PaiHangBean.DataBean)data.get(position)).getNickname());

        TextView age = (TextView)holder.getView(R.id.find_zuixin_age);

        age.setText(((PaiHangBean.DataBean)data.get(position)).getAge());
        if (((PaiHangBean.DataBean)data.get(position)).getSex().equals("1")) {
            Drawable drawable = conn.getResources().getDrawable(R.drawable.miss);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            age.setCompoundDrawables(drawable, null, null, null);
        } else if (((PaiHangBean.DataBean)data.get(position)).getSex().equals("0")) {
            Drawable drawable = conn.getResources().getDrawable(R.drawable.boy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            age.setCompoundDrawables(drawable, null, null, null);
        }

        ((TextView)holder.getView(R.id.paihang_sy_money)).setText(((PaiHangBean.DataBean)data.get(position)).getCount());

    }
}
