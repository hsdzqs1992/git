package com.zhuye.hougong.adapter.me;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.LiaoBiLIstBean;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class ChongZhiAdapter extends BaseRecycleAdapter {


    public ChongZhiAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.zhongzhi_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        ((TextView)holder.getView(R.id.price)).setText(((LiaoBiLIstBean.DataBean)data.get(position)).getLiaobi()+"\n聊币");
        ((TextView)holder.getView(R.id.money)).setText(((LiaoBiLIstBean.DataBean)data.get(position)).getMoney()+"\n元");
    }
}
