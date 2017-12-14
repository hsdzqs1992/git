package com.zhuye.hougong.adapter.me;

import android.content.Context;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by zzzy on 2017/11/22.
 */

public class MyFriendsAdapter extends BaseRecycleAdapter {

    public MyFriendsAdapter(Context conn) {
        super(conn);
    }

    public MyFriendsAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.guanzhu_item;


    }


    @Override
    protected void conver(BaseHolder holder, int position) {
        //((TextView)holder.getView(R.id.fragment_me_item_tv)).setText(data.get(position)+"");
    }

}
