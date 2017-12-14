package com.zhuye.hougong.fragment.find;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.find.FindZuiXinAdapter;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.PingLunActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class ZuiXinFragment extends BaseFindFragment {

    FindZuiXinAdapter adapter;
    @Override
    protected void initView() {
        super.initView();
        adapter = new FindZuiXinAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
        //初始化关注数据
        initZhuiXinData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                view.findViewById(R.id.find_zuixin_pinglun).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), PingLunActivity.class);
                        intent.putExtra("dynamic_id",dongTaiBean.getData().get(position).getDynamic_id());
                        getActivity().startActivity(intent);
                    }
                });
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //有bug
                 page = 1;
                initZhuiXinData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                page++;
                initZhuiXinData();
            }
        });
    }

    DongTaiBean dongTaiBean;
    int page = 1;
    int pagesize = 10;
    List<DongTaiBean.DataBean> datas = new ArrayList<>();
    private void initZhuiXinData() {
        OkGo.<String>post(Contants.dynamiclists)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type", "1")
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---", response.body());
                        try {
                            Gson gson = new Gson();
                            dongTaiBean = gson.fromJson(response.body(), DongTaiBean.class);
                            //最后一页时为空
                            if (dongTaiBean != null && adapter != null) {
                                // TODO: 2017/12/6 0006
                                datas.clear();
                                datas= dongTaiBean.getData();
                                adapter.addData(datas);
                                //if(materialRefreshLayout.)
                                if(page!=1){
                                    materialRefreshLayout.finishRefresh();
                                }

                                //finAdapter.setFindZhuiXinData(dongTaiBean);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }finally {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CommentUtils.toast(getActivity(),"没有更多数据了");
                                    materialRefreshLayout.finishRefresh();
                                    materialRefreshLayout.finishRefreshLoadMore();
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());
                    }
                });

    }
}
