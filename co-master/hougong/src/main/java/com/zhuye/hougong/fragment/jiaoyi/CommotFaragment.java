package com.zhuye.hougong.fragment.jiaoyi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.CommontAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

/**
 * Created by zzzy on 2017/11/22.
 * 通用fragment
 */

public class CommotFaragment extends BaseFragment {


    MaterialRefreshLayout materialRefreshLayout;
    RecyclerView recyclerView;
    CommontAdapter commontAdapter;

    @Override
    protected void initView() {
        materialRefreshLayout = rootView.findViewById(R.id.common_material);
        recyclerView = rootView.findViewById(R.id.commot_recycle);
        commontAdapter = new CommontAdapter(getActivity());
        recyclerView.setAdapter(commontAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    protected void getData(int type){
        OkGo.<String>post(Contants.zhichujiaoji)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type", type)
                .params("page", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            //// TODO: 2017/12/9 0009 数据的处理
                            //WxPayBean bean = gson.fromJson(response.body(),WxPayBean.class);

                        }else if(response.body().contains("201")){
                            CommentUtils.toast(getActivity(),"没有更多数据");
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(getActivity(), "支付失败");
                    }
                });
    }



    @Override
    protected int getResId() {
        return R.layout.common_recycle;
    }
}
