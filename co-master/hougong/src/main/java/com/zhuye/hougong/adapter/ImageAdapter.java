package com.zhuye.hougong.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.lzy.imagepicker.bean.ImageItem;
import com.zhuye.hougong.R;

import java.io.File;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class ImageAdapter extends BaseRecycleAdapter {


    public ImageAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.image1;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ImageView iv = holder.getView(R.id.image);
        iv.setImageURI(Uri.fromFile(new File(((ImageItem) data.get(position)).path)));
    }
}
