package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.WhoSendAdapter;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WhoSendLiWuActivity extends AppCompatActivity {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle2);
        ButterKnife.bind(this);




        WhoSendAdapter whoSendAdapter = new WhoSendAdapter(this);

        commotRecycle.setAdapter(whoSendAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {

        OkGo.<String>post(Contants.gift_log)
                .params("token", Sputils.getString(WhoSendLiWuActivity.this,"token",""))
                .params("page",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---",response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---",response.body());

                    }
                });
    }
}
