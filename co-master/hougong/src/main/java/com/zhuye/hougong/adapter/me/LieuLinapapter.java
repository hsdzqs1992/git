package com.zhuye.hougong.adapter.me;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.SongLiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class LieuLinapapter<T> extends BaseRecycleAdapter {

    public LieuLinapapter(Context conn, List<T> data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.tv2;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

       TextView tv =  holder.getView(R.id.name);
        tv.setText(((SongLiBean.DataBean.GiftBean)data.get(position)).getName() +"x" +((SongLiBean.DataBean.GiftBean)data.get(position)).getNum());
    }
}
