package com.zhuye.hougong.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.LookMeAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.LookBean;
import com.zhuye.hougong.contants.Contants;
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



    @Override
    protected void initData() {
        OkGo.<String>post(Contants.wholookme)
                .params("token", Sputils.getString(LookMeActivity.this,"token",""))
                .params("page",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        if(response.body().contains("200")){
                            try {
                                Gson gson = new Gson();
                                LookBean bean = gson.fromJson(response.body(),LookBean.class);
                                if(bean.getData()!=null && bean.getData().size()>0){
                                    blackNumberAdapter.addData(bean.getData());
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
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
