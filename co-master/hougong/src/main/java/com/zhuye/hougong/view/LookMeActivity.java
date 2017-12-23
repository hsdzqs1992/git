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
import com.zhuye.hougong.adapter.me.LookMeAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.LookBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.OnClick;

public class LookMeActivity extends BaseActivity {


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
    }

    @Override
    protected void initData() {
        getData(page);
    }
    LookBean dongTaiBean;
    private void getData(int page ) {
        OkGo.<String>post(Contants.wholookme)
                .params("token", Sputils.getString(LookMeActivity.this,"token",""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                                    Gson gson = new Gson();
                                    try {
                                        dongTaiBean = gson.fromJson(response.body(),LookBean.class);

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
                                                        CommentUtils.toast(LookMeActivity.this,"没有更多数据");
                                                        LookMeActivity.this.page--;
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
                                    CommentUtils.toast(LookMeActivity.this,"没有更多数据");
                                    LookMeActivity.this.page--;
                                    commonMaterial.finishRefreshLoadMore();
                                }
                            }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    LookMeAdapter blackNumberAdapter;
    @Override
    protected void initview() {
        super.initview();
        mywalotQianbao.setText("谁看过我");
        songliwuTixian.setVisibility(View.INVISIBLE);
        blackNumberAdapter = new LookMeAdapter(this);
        commotRecycle.setAdapter(blackNumberAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
        commonMaterial.setLoadMore(true);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }
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
    protected int getResId() {
        return R.layout.common_recycle2;
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }

}
