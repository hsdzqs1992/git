package com.zhuye.hougong.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.Code;
import com.zhuye.hougong.bean.TiXianBeab;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {

    @BindView(R.id.mywalot_back)
    ImageView mywalotBack;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.tixian_yinhang)
    TextView tixianYinhang;
    @BindView(R.id.jine)
    EditText jine;
    @BindView(R.id.quanbu)
    TextView quanbu;
    @BindView(R.id.tixian)
    Button tixian;


    @Override
    protected void initData() {
        super.initData();

        OkGo.<String>post(Contants.tx_money)
                .params("token", Sputils.getString(TiXianActivity.this,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("sdf",response.body());
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            TiXianBeab tiXianBeab= gson.fromJson(response.body(),TiXianBeab.class);
                            jine.setText(tiXianBeab.getData().getMoney()+"");
                        }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("sdf",response.body());
                    }
                });
    }

    @Override
    protected int getResId() {
        return R.layout.activity_ti_xian;
    }

    @OnClick({R.id.mywalot_back, R.id.tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywalot_back:
                finish();
                break;
            case R.id.tixian:
                tixian();
                break;
        }
    }

    private void tixian() {

        if(TextUtils.isEmpty(jine.getText().toString().trim())){
            CommentUtils.toast(TiXianActivity.this,"请输入金额");
            return;
        }

        OkGo.<String>post(Contants.cash)
                .params("token", Sputils.getString(TiXianActivity.this,"token",""))
                .params("money", jine.getText().toString().trim())
                .params("cash", "6227002504321123715")
                .params("cash_name", "中国建设银行")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("sdf",response.body());

                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            Code tiXianBeab= gson.fromJson(response.body(),Code.class);
                            //jine.setText(tiXianBeab.getData().getMoney()+"");
                            CommentUtils.toast(TiXianActivity.this,tiXianBeab.getMessage());
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(TiXianActivity.this,"提现失败");
                        }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("sdf",response.body());
                    }
                });

    }
}
