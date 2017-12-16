package com.zhuye.hougong.adapter.message;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.TongListBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

/**
 * Created by zzzy on 2017/12/5.
 */

public class TongHuaListAdapter extends BaseRecycleAdapter {

    public TongHuaListAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.message_tonhua_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        RoundedCornerImageView vi = holder.getView(R.id.message_item_touxiang);
        Glide.with(conn).load(Contants.BASE_URL+((TongListBean.DataBean)data.get(position)).getFace()).into(vi);
        if(TextUtils.isEmpty(((TongListBean.DataBean)data.get(position)).getNickname())){
            ((TextView)holder.getView(R.id.message_item_title)).setText("无");
        }
        ((TextView)holder.getView(R.id.message_item_title)).setText(((TongListBean.DataBean)data.get(position)).getNickname());
        ((TextView)holder.getView(R.id.find_zuixin_data)).setText(((TongListBean.DataBean)data.get(position)).getTime());
    }
}
