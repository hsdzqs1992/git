package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.message.ShengJIAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.VipListbean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.wxapi.WxPayBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhuye.hougong.R.drawable.location;

public class ShengJiVIP2Activity extends BaseActivity {


    @BindView(R.id.mywalot_back)
    ImageView mywalotBack;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.shengji2_tou)
    ImageView shengji2Tou;
    @BindView(R.id.shengji2_name)
    TextView shengji2Name;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.shengji2_jinetitle)
    TextView shengji2Jinetitle;
    @BindView(R.id.shengji2_jinee)
    TextView shengji2Jinee;
    @BindView(R.id.shengji2_kaitong)
    Button shengji2Kaitong;

    ShengJIAdapter adapter;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.fabu_location_iv)
    ImageView fabuLocationIv;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.activity_sheng_ji_vip2)
    LinearLayout activityShengJiVip2;

    @Override
    protected void initview() {
        super.initview();
        adapter = new ShengJIAdapter(this);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new GridLayoutManager(this, 3));

    }

    VipListbean tiXianBeab;

    @Override
    protected void initData() {
        super.initData();

        Glide.with(ShengJiVIP2Activity.this).load(Sputils.getString(ShengJiVIP2Activity.this, "face", "")).into(shengji2Tou);
        shengji2Name.setText(Sputils.getString(ShengJiVIP2Activity.this, "name", ""));
        OkGo.<String>post(Contants.viplist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("sdf", response.body());
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            tiXianBeab = gson.fromJson(response.body(), VipListbean.class);
                            adapter.addData(tiXianBeab.getData());
                            //初始化
                            shengji2Jinee.setText(tiXianBeab.getData().get(1).getMoney());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("sdf", response.body());
                    }
                });
    }

    private Integer pos;

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                pos = position;
                for (int i = 0; i < tiXianBeab.getData().size(); i++) {
                    if (i == position) {
                        tiXianBeab.getData().get(i).setSelect(true);
                    } else {
                        tiXianBeab.getData().get(i).setSelect(false);
                    }
                }
                adapter.addData2(tiXianBeab.getData(), 0);
                shengji2Jinee.setText(tiXianBeab.getData().get(position).getMoney());
                month.setText(tiXianBeab.getData().get(position).getMonth());
            }
        });



        fabuLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isslectedloat();
            }
        });
    }

    private Boolean isshowlocation = true;
    public void isslectedloat() {
        if(isshowlocation){
            fabuLocationIv.setImageResource(R.drawable.check_off);
        }else {
            fabuLocationIv.setImageResource(R.drawable.check_on);
        }
        isshowlocation = !isshowlocation;
    }

    @Override
    protected int getResId() {
        return R.layout.activity_sheng_ji_vip2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mywalot_back, R.id.shengji2_kaitong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywalot_back:
                finish();
                break;
            case R.id.shengji2_kaitong:
                if (pos == null) {
                    CommentUtils.toast(ShengJiVIP2Activity.this, "请选择金额");
                    return;
                }
                //调用支付
                payWeiXin();
                break;
        }
    }

    private static IWXAPI WXapi;
    private String WX_APP_ID = "wxddf17683ec437cfa";
    PayReq req;

    private void payWeiXin() {
        WXapi = WXAPIFactory.createWXAPI(ShengJiVIP2Activity.this, WX_APP_ID, false);
        WXapi.registerApp(WX_APP_ID);
        OkGo.<String>post(Contants.wxpayvip)
                .params("token", Sputils.getString(ShengJiVIP2Activity.this, "token", ""))
                .params("type_id", pos + 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            WxPayBean bean = gson.fromJson(response.body(), WxPayBean.class);
                            req = new PayReq();
                            req.appId = bean.getData().getAppid();
                            req.partnerId = bean.getData().getPartnerid();
                            req.prepayId = bean.getData().getPrepayid();
                            req.nonceStr = bean.getData().getNoncestr();
                            req.timeStamp = bean.getData().getTimestamp() + "";
                            req.packageValue = bean.getData().getPackageX();
                            req.sign = bean.getData().getSign();
                            WXapi.sendReq(req);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(ShengJiVIP2Activity.this, "支付失败");
                    }
                });
    }
}
