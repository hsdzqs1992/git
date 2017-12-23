package com.zhuye.hougong.fragment.message;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zhuye.hougong.adapter.message.TongHuaListAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.TongListBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.PersonHomePageActivity;

import butterknife.BindView;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * Created by zzzy on 2017/12/5.
 */

public class TongHuaListFragment extends BaseFragment {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;

    TongHuaListAdapter adapter;
    @Override
    protected void initView() {

        adapter = new TongHuaListAdapter(getActivity());
        commotRecycle.setAdapter(adapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonMaterial.setLoadMore(true);
    }

    @Override
    protected int getResId() {
        return R.layout.common_recycle;
    }


    TongListBean dongTaiBean;
    @Override
    protected void initData() {
        super.initData();

getData(1);
    }
    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public int page = 1;
    @Override
    protected void initListener() {
        super.initListener();


        commonMaterial.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                state =refresh;
                getData(1);


            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                page++;
                state= loadmore;
                getData(page);
            }
        });

        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent in = new Intent(getActivity(),PersonHomePageActivity.class);
                in.putExtra("uid",dongTaiBean.getData().get(position).getUid());
                in.putExtra("guanzhu",dongTaiBean.getData().get(position).getUsertype());//bug
                getActivity().startActivity(in);
            }
        });
    }

    private void getData(int page) {

        OkGo.<String>post(Contants.call_record)
                .params("token", Sputils.getString(getActivity(),"token",""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean= gson.fromJson(response.body(),TongListBean.class);
                                if(adapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&adapter!=null){
                                                adapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            adapter.clear();
                                            adapter.addData(dongTaiBean.getData());
                                            commotRecycle.scrollToPosition(0);
                                            commonMaterial.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null|| dongTaiBean.getData().size()==0){
                                                CommentUtils.toast(getActivity(),"没有更多数据");
                                                TongHuaListFragment.this.page--;
                                            }else{
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        adapter.addData(dongTaiBean.getData());
                                                        commotRecycle.scrollToPosition(adapter.getSize());
                                                    }
                                                });

                                            }
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    commonMaterial.finishRefreshLoadMore();
                                                }
                                            });
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("201")){
                            commonMaterial.finishRefresh();
                            CommentUtils.toast(getActivity(),"没有更多数据");
                            TongHuaListFragment.this.page--;
                            commonMaterial.finishRefreshLoadMore();
                        }

                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        int i = 0;
                        Log.i("llllll",response.body());
                    }
                });
    }
}
