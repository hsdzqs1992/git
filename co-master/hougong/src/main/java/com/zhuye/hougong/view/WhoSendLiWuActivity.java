package com.zhuye.hougong.view;

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
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.WhoSendAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.SongLiBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;

public class WhoSendLiWuActivity extends BaseActivity {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;

    ImageView back;
    TextView tixian;

    @Override
    protected void initview() {
        super.initview();
        whoSendAdapter = new WhoSendAdapter(this);
        commotRecycle.setAdapter(whoSendAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
        back = findViewById(R.id.person_detail_back);
        tixian = findViewById(R.id.songliwu_tixian);
        tixian.setVisibility(View.GONE);
        commonMaterial.setLoadMore(true);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }
    WhoSendAdapter whoSendAdapter;

    @Override
    protected void initListener() {
        super.initListener();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    }



    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public int page = 1;
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected ImmersionBar mImmersionBar;
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initData() {
        getData(1);
    }
    SongLiBean dongTaiBean;
    private void getData(int page ) {
        OkGo.<String>post(Contants.gift_log)
                .params("token", Sputils.getString(WhoSendLiWuActivity.this,"token",""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean = gson.fromJson(response.body(),SongLiBean.class);


                                if(whoSendAdapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&whoSendAdapter!=null){
                                                whoSendAdapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            whoSendAdapter.clear();
                                            whoSendAdapter.addData(dongTaiBean.getData());
                                            commotRecycle.scrollToPosition(0);
                                            commonMaterial.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null|| dongTaiBean.getData().size()==0){
                                                CommentUtils.toast(WhoSendLiWuActivity.this,"没有更多数据");
                                                WhoSendLiWuActivity.this.page--;
                                            }else{
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        whoSendAdapter.addData(dongTaiBean.getData());
                                                        commotRecycle.scrollToPosition(whoSendAdapter.getSize());
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
                            CommentUtils.toast(WhoSendLiWuActivity.this,"没有更多数据");
                            WhoSendLiWuActivity.this.page--;
                            commonMaterial.finishRefreshLoadMore();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---",response.body());

                    }
                });
    }

    @Override
    protected int getResId() {
        return R.layout.common_recycle2;
    }
}
