package com.zhuye.hougong.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.GuanZhuAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzzy on 2017/11/22.
 */

public class FansActivity extends BaseActivity {


    @BindView(R.id.guanzhu_recycleview)
    RecyclerView guanzhuRecycleview;
    @BindView(R.id.guanzhu_refesh)
    MaterialRefreshLayout guanzhuRefesh;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_zhuanqian)
    TextView mywalotZhuanqian;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    GuanZhuAdapter guanZhuAdapter;


    @Override
    protected void initview() {
        super.initview();
        mywalotQianbao.setText("我的粉丝");
        guanZhuAdapter  = new GuanZhuAdapter(this);
        guanzhuRecycleview.setAdapter(guanZhuAdapter);
        guanzhuRecycleview.setLayoutManager(new LinearLayoutManager(this));
        guanzhuRefesh.setLoadMore(true);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_guan_zhu;
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initListener() {
        super.initListener();

        guanzhuRefesh.setMaterialRefreshListener(new MaterialRefreshListener() {
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
    }

    MyFriendsBean dongTaiBean ;
    private void getData(int page) {
        OkGo.<String>post(Contants.loveme)
                .params("token", Sputils.getString(FansActivity.this,"token",""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean= gson.fromJson(response.body(),MyFriendsBean.class);
                                if(guanZhuAdapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&guanZhuAdapter!=null){
                                                guanZhuAdapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            guanZhuAdapter.clear();
                                            guanZhuAdapter.addData(dongTaiBean.getData());
                                            guanzhuRecycleview.scrollToPosition(0);
                                            guanzhuRefesh.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null|| dongTaiBean.getData().size()==0){
                                                CommentUtils.toast(FansActivity.this,"没有更多数据");
                                                FansActivity.this.page--;
                                            }else{
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        guanZhuAdapter.addData(dongTaiBean.getData());
                                                        guanzhuRecycleview.scrollToPosition(guanZhuAdapter.getSize());
                                                    }
                                                });

                                            }
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    guanzhuRefesh.finishRefreshLoadMore();
                                                }
                                            });
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("201")){
                            guanzhuRefesh.finishRefresh();
                            CommentUtils.toast(FansActivity.this,"没有更多数据");
                            FansActivity.this.page--;
                            guanzhuRefesh.finishRefreshLoadMore();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }

    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public int page = 1;

    @Override
    protected void initData() {
        super.initData();
        getData(1);
    }
}
