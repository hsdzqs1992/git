package com.zhuye.hougong.adapter.me;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.PhotoBean;
import com.zhuye.hougong.contants.Contants;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class PictureListAdapter extends BaseRecycleAdapter {
    public PictureListAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.photoimage;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        if(position==0){
            ((ImageView) holder.getView(R.id.photoimage)).setImageResource(R.drawable.photo1);
        }else {
            ImageView iv = holder.getView(R.id.photoimage);
            Glide.with(conn).load(Contants.BASE_URL+((PhotoBean.DataBean)data.get(position))
                    .getPhoto()).error(R.id.photoimage).placeholder(R.mipmap.ic_launcher).into(iv);
        }

    }
}
