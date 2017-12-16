package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuanZhuActivity extends AppCompatActivity {


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);
        mywalotQianbao.setText("我的关注");



        guanZhuAdapter = new GuanZhuAdapter(this);
        guanzhuRecycleview.setAdapter(guanZhuAdapter);
        guanzhuRecycleview.setLayoutManager(new LinearLayoutManager(this));

        initData();
    }

    private void initData() {
        OkGo.<String>post(Contants.mylove)
                .params("token", Sputils.getString(GuanZhuActivity.this,"token",""))
                .params("page",1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {

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

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }
}
