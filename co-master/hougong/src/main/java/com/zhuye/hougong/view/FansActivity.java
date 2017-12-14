package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.GuanZhuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzzy on 2017/11/22.
 */

public class FansActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);
        mywalotQianbao.setText("我的粉丝");

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("sdfasdf" + i);
        }

        final GuanZhuAdapter guanZhuAdapter = new GuanZhuAdapter(this, data);
        guanzhuRecycleview.setAdapter(guanZhuAdapter);
        guanzhuRecycleview.setLayoutManager(new LinearLayoutManager(this));

        guanZhuAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //Toast.makeText(GuanZhuActivity.this,"haha",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
    }
}
