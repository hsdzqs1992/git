package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.contants.Contants;

import java.util.List;

/**
 * Created by zzzy on 2017/11/30.
 */

public class Find3Adapter extends BaseRecycleAdapter {

    public Find3Adapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.find_image1;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
//        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.ivv);
//        draweeView.setImageURI(Contants.BASE_URL+data.get(position));
     // Glide.with(conn).load(Contants.BASE_URL+data.get(position)).into(im);
        ImageView im = holder.getView(R.id.ivv);
        Glide.with(conn).
                load(Contants.BASE_URL+data.get(position)).into(im);

    }


}
