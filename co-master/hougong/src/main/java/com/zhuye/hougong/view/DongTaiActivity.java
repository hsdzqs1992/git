package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.DongTaiAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.weidgt.MyLineLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DongTaiActivity extends BaseActivity {

    int type;
    @BindView(R.id.fragment_home_recycle)
    RecyclerView fragmentHomeRecycle;
    @BindView(R.id.fragment_home_materrial)
    MaterialRefreshLayout fragmentHomeMaterrial;
    DongTaiAdapter adapter;
    @BindView(R.id.fabu)
    ImageView fabu;
    @BindView(R.id.fabu1)
    TextView fabu1;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    String token;
    @BindView(R.id.header)
    LinearLayout header;

    @Override
    protected void initview() {
        super.initview();
        type = getIntent().getIntExtra("type", 1);
        token = getIntent().getStringExtra("token");
        adapter = new DongTaiAdapter(this);
        fragmentHomeMaterrial.setLoadMore(true);
        fragmentHomeRecycle.setAdapter(adapter);
        fragmentHomeRecycle.setLayoutManager(new MyLineLayoutManager(this));
        if (type == 1) {
            mywalotQianbao.setText("我的动态");
        } else if (type == 2) {
            mywalotQianbao.setText("好友动态");
            header.setVisibility(View.GONE);
        }

    }

    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public static int page = 1;
    @Override
    protected void initListener() {
        super.initListener();

        fragmentHomeMaterrial.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                state = refresh;
              //  initFindData(1,"201");
                getData(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                state = loadmore;
                page++;
                // initFindData(page,"201");
                getData(page);
            }
        });


    }

    @Override
    protected void initData() {
        super.initData();
        getData(1);

    }


    DongTaiBean dongTaiBean;

    private void getData(int page) {
        OkGo.<String>post(Contants.dynamiclists)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      //  Log.i("---", response.body());
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean = gson.fromJson(response.body(),DongTaiBean.class);
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
                                            fragmentHomeRecycle.scrollToPosition(0);
                                            fragmentHomeMaterrial.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null){
                                                CommentUtils.toast(DongTaiActivity.this,"没有更多数据");
                                                DongTaiActivity.this.page--;
                                            }else{
                                                adapter.addData(dongTaiBean.getData());
                                                fragmentHomeRecycle.scrollToPosition(adapter.getSize());

                                            }
                                            fragmentHomeMaterrial.finishRefreshLoadMore();
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(DongTaiActivity.this,"没有更多数据");
                            DongTaiActivity.this.page--;
                            fragmentHomeMaterrial.finishRefreshLoadMore();
                        }else if(response.body().equals("null")){
                            CommentUtils.toast(DongTaiActivity.this,"没有更多数据");
                            DongTaiActivity.this.page--;
                            fragmentHomeMaterrial.finishRefreshLoadMore();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());
                    }
                });
    }

    @Override
    protected int getResId() {
        return R.layout.activity_dongtai2;
    }


    @OnClick({R.id.fabu, R.id.fabu1, R.id.person_detail_back, R.id.mywalot_qianbao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabu:
            case R.id.fabu1:
                //// TODO: 2017/12/11 0011 回来是type处理  
                startActivity(new Intent(this, FaBuActivity.class));
                break;
            case R.id.mywalot_qianbao:

                break;
            case R.id.person_detail_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
