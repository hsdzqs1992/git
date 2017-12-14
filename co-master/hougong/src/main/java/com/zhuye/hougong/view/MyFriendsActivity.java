package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.MyFriendsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriendsActivity extends AppCompatActivity {


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
        mywalotQianbao.setText("我的好友");

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("sdfasdf" + i);
        }

        MyFriendsAdapter guanZhuAdapter = new MyFriendsAdapter(this, data);
        commotRecycle.setAdapter(guanZhuAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }

}
