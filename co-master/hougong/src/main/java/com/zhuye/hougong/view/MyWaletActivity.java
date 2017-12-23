package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.MyWaletBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWaletActivity extends AppCompatActivity {


    @BindView(R.id.mywalot_money)
    TextView mywalotMoney;
    @BindView(R.id.mywalot_leiji)
    TextView mywalotLeiji;
    @BindView(R.id.mywalot_zhichu)
    TextView mywalotZhichu;
    @BindView(R.id.mywalot_chongzhi)
    LinearLayout mywalotChongzhi;
    @BindView(R.id.mywalot_tixian)
    LinearLayout mywalotTixian;
    @BindView(R.id.mywalot_jiaoyi)
    LinearLayout mywalotJiaoyi;
    @BindView(R.id.mywalot_shouzhijilv)
    LinearLayout mywalotShouzhijilv;
    @BindView(R.id.mywalot_back)
    ImageView mywalotBack;
    @BindView(R.id.activity_my_walet)
    LinearLayout activityMyWalet;
    @BindView(R.id.mywalot_zhuanqian)
    TextView mywalotZhuanqian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_walet);

        if (isImmersionBarEnabled())
            initImmersionBar();
        initData();
        ButterKnife.bind(this);
    }
    MyWaletBean mbean;
    protected ImmersionBar mImmersionBar;
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
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private void initData() {
        OkGo.<String>post(Contants.money)
                .params("token", Sputils.getString(MyWaletActivity.this,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        mbean = gson.fromJson(response.body(),MyWaletBean.class);
                        if(response.body().contains("200")){
                            mywalotMoney.setText(mbean.getData().getMoney());
                            mywalotLeiji.setText(mbean.getData().getProfile()+"");
                            mywalotZhichu.setText(mbean.getData().getZprofile()+"100");
                        }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }

    @OnClick({R.id.mywalot_chongzhi, R.id.mywalot_tixian, R.id.mywalot_jiaoyi, R.id.mywalot_shouzhijilv, R.id.mywalot_back,R.id.mywalot_zhuanqian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywalot_chongzhi:
                Intent in = new Intent(MyWaletActivity.this, ChongZhiActivity.class);
                in.putExtra("jne",mbean.getData().getMoney());
                startActivity(in);
                break;
            case R.id.mywalot_tixian:
                startActivity(new Intent(MyWaletActivity.this, TiXianActivity.class));
                break;
            case R.id.mywalot_jiaoyi:
                startActivity(new Intent(MyWaletActivity.this, JiaoYiJiLvActivity.class));
                break;
            case R.id.mywalot_shouzhijilv:
                startActivity(new Intent(MyWaletActivity.this, ShouZhiJiLvActivity.class));
                break;
            case R.id.mywalot_back:
                finish();
                break;
            case R.id.mywalot_zhuanqian:
                startActivity(new Intent(MyWaletActivity.this, ZhuanQianGongActivity.class));
                break;
        }
    }
}
