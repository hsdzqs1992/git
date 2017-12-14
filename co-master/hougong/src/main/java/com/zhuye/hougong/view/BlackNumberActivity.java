package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.BlackNumberAdapter;
import com.zhuye.hougong.bean.MyFriendsBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import java.util.ArrayList;
import java.util.List;

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

        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add("sdfasdf" + i);
        }

        blackNumberAdapter = new BlackNumberAdapter(this);

        initData();
        commotRecycle.setAdapter(blackNumberAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
        iniListener();
    }

    private void iniListener() {
        blackNumberAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                //// TODO: 2017/12/1 反应慢
              //  CommentUtils.toast(BlackNumberActivity.this, "token");
                TextView tv = view.findViewById(R.id.blacknumber_yichu);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OkGo.<String>post(Contants.del_black)
                                .params("token", Sputils.getString(BlackNumberActivity.this, "token", ""))
                                .params("uid", Integer.parseInt(mybean.getData().get(position).getUid()))
                                .execute(new MyCallback() {
                                    @Override
                                    protected void doFailue(Response<String> response) {
                                        CommentUtils.toast(BlackNumberActivity.this, "sdf");
                                    }

                                    @Override
                                    protected void excuess(Response<String> response) {
                                        if (response.body().contains("200")) {
                                            CommentUtils.toast(BlackNumberActivity.this, "已取消");
                                            blackNumberAdapter.removeData(mybean.getData().get(position),position);
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
    MyFriendsBean mybean;

    private void initData() {
        OkGo.<String>post(Contants.blacklist)
                .params("token", Sputils.getString(BlackNumberActivity.this, "token", ""))
                .params("page", 1)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        CommentUtils.toast(BlackNumberActivity.this, "sdf");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String s = response.body();
                        Gson gson = new Gson();
                        mybean = gson.fromJson(response.body(), MyFriendsBean.class);
                        //CommentUtils.toast(BlackNumberActivity.this,s);
                        blackNumberAdapter.addData(mybean.getData());
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
