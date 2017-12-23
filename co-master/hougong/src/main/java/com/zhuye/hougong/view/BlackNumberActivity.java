package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BlackNumberAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlackNumberActivity extends AppCompatActivity {


    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.songliwu_tixian)
    TextView songliwuTixian;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle2);
        ButterKnife.bind(this);
        mywalotQianbao.setText("黑名单");
        songliwuTixian.setVisibility(View.GONE);

        blackNumberAdapter = new BlackNumberAdapter(this);

        initData();
        commotRecycle.setAdapter(blackNumberAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
        commonMaterial.setLoadMore(true);


        iniListener();
    }
    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public int page = 1;

    private void iniListener() {

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

        blackNumberAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                //// TODO: 2017/12/1 反应慢
              //  CommentUtils.toast(BlackNumberActivity.this, "token");
                Log.i("as","aa");
                TextView tv = view.findViewById(R.id.blacknumber_yichu);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OkGo.<String>post(Contants.del_black)
                                .params("token", Sputils.getString(BlackNumberActivity.this, "token", ""))
                                .params("uid", Integer.parseInt(dongTaiBean.getData().get(position).getUid()))
                                .execute(new MyCallback() {
                                    @Override
                                    protected void doFailue(Response<String> response) {
                                        CommentUtils.toast(BlackNumberActivity.this, "sdf");
                                    }

                                    @Override
                                    protected void excuess(Response<String> response) {
                                        if (response.body().contains("200")) {
                                            CommentUtils.toast(BlackNumberActivity.this, "已取消");
                                            blackNumberAdapter.removeData(dongTaiBean.getData().get(position),position);
                                        }
                                        //String s = response.body();
                                        // Gson gson = new Gson();
                                        //mybean = gson.fromJson(response.body(),MyFriendsBean.class);
                                        //CommentUtils.toast(BlackNumberActivity.this,s);
                                        //blackNumberAdapter.addData(mybean.getData());
                                    }
                                });
                    }
                });
            }
        });
    }

    BlackNumberAdapter blackNumberAdapter;
    MyFriendsBean dongTaiBean;

    private void initData() {
        getData(1);
    }

    private void getData(int page) {
        OkGo.<String>post(Contants.blacklist)
                .params("token", Sputils.getString(BlackNumberActivity.this, "token", ""))
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean= gson.fromJson(response.body(),MyFriendsBean.class);
                                if(blackNumberAdapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&blackNumberAdapter!=null){
                                                blackNumberAdapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            blackNumberAdapter.clear();
                                            blackNumberAdapter.addData(dongTaiBean.getData());
                                            commotRecycle.scrollToPosition(0);
                                            commonMaterial.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null|| dongTaiBean.getData().size()==0){
                                                CommentUtils.toast(BlackNumberActivity.this,"没有更多数据");
                                                BlackNumberActivity.this.page--;
                                            }else{
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        blackNumberAdapter.addData(dongTaiBean.getData());
                                                        commotRecycle.scrollToPosition(blackNumberAdapter.getSize());
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
                            CommentUtils.toast(BlackNumberActivity.this,"没有更多数据");
                            BlackNumberActivity.this.page--;
                            commonMaterial.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    @OnClick({R.id.person_detail_back, R.id.songliwu_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
            case R.id.songliwu_tixian:
                break;
        }
    }
}
