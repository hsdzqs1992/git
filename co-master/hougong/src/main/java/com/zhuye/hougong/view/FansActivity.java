package com.zhuye.hougong.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.GuanZhuAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
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
    protected void initData() {
        super.initData();
        OkGo.<String>post(Contants.loveme)
                .params("token", Sputils.getString(FansActivity.this,"token",""))
                .params("page",1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        //// TODO: 2017/12/15 0015  
                        if(response.body().contains("201")){
                            
                        }
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String i = response.body();
                        Gson gson = new Gson();
                        MyFriendsBean myfriend = gson.fromJson(response.body(),MyFriendsBean.class);
                        guanZhuAdapter.addData(myfriend.getData());
                        Log.i("llllll",i);
                    }
                });

    }
}
