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

public class Find4Adapter extends BaseRecycleAdapter {

    public Find4Adapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.find_image3;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ImageView im = holder.getView(R.id.ivv);
      Glide.with(conn).
             load(Contants.BASE_URL+data.get(position)).into(im);
//        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.ivv);
//        draweeView.setImageURI(Contants.BASE_URL+data.get(position));

    }


}
