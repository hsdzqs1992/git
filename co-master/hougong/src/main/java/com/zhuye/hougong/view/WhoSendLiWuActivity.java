package com.zhuye.hougong.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.WhoSendAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.SongLiBean;
import com.zhuye.hougong.contants.Contants;
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

    }

    @Override
    protected void initData() {
        OkGo.<String>post(Contants.gift_log)
                .params("token", Sputils.getString(WhoSendLiWuActivity.this,"token",""))
                .params("page",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            try {
                                Gson gson = new Gson();
                                SongLiBean bean = gson.fromJson(response.body(),SongLiBean.class);
                                whoSendAdapter.addData(bean.getData());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
