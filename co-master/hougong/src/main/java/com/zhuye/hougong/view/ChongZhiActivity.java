package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.me.ChongZhiAdapter;
import com.zhuye.hougong.bean.LiaoBiLIstBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;
import com.zhuye.hougong.wxapi.WxPayBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongZhiActivity extends AppCompatActivity {

    @BindView(R.id.person_home_back)
    ImageView personHomeBack;
    @BindView(R.id.chongzhi_titile_name)
    TextView chongzhiTitileName;
    @BindView(R.id.chongzhi_touxiang)
    RoundedCornerImageView chongzhiTouxiang;
    @BindView(R.id.chongzhi_name)
    TextView chongzhiName;
    @BindView(R.id.find_zuixin_dizhi)
    TextView findZuixinDizhi;
    @BindView(R.id.chongzhi_data)
    TextView chongzhiData;
    @BindView(R.id.zhifu)
    RecyclerView zhifu;
    @BindView(R.id.zhontzhi_alipai_iv)
    ImageView zhontzhiAlipaiIv;
    @BindView(R.id.chongzhi_alipay)
    RelativeLayout chongzhiAlipay;
    @BindView(R.id.zhontzhi_weixin_iv)
    ImageView zhontzhiWeixinIv;
    @BindView(R.id.chongzhi_weixin)
    RelativeLayout chongzhiWeixin;
    @BindView(R.id.activity_chong_zhi)
    LinearLayout activityChongZhi;
String jine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chong_zhi);
        ButterKnife.bind(this);

        jine=getIntent().getStringExtra("jne");
        initView();

        initData();

        initListener();

    }



    private void initListener() {
        adaper.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                pos=position;
                //view.findViewById(R.id.imag)
               // Log.i("as","cishi"+position);
                for(int i = 0 ;i< bean.getData().size();i++){
                    if(i == position){
                        bean.getData().get(i).setIssected(true);
                    }else  {
                        bean.getData().get(i).setIssected(false);
                    }
                }
                adaper.addData2(bean.getData(),0);

            }
        });
    }

    private Integer pos;
    LiaoBiLIstBean bean;
    ChongZhiAdapter adaper;
    private void initView() {
        adaper = new ChongZhiAdapter(ChongZhiActivity.this);
        zhifu.setAdapter(adaper);
        zhifu.setLayoutManager(new GridLayoutManager(ChongZhiActivity.this,3));
        if (isImmersionBarEnabled())
            initImmersionBar();
    }
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
    protected ImmersionBar mImmersionBar;
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private void initData() {
        Glide.with(ChongZhiActivity.this).load(Sputils.getString(ChongZhiActivity.this,"face","")).into(chongzhiTouxiang);
        chongzhiName.setText(Sputils.getString(ChongZhiActivity.this,"name",""));
        findZuixinDizhi.setText(Sputils.getString(ChongZhiActivity.this,"age",""));
        chongzhiData.setText(jine);
        OkGo.<String>post(Contants.liaobi_list)
                .params("token", Sputils.getString(ChongZhiActivity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                             bean = gson.fromJson(response.body(),LiaoBiLIstBean.class);
                            for(int i = 0;i<bean.getData().size();i++){
                                bean.getData().get(i).setIssected(false);
                            }
                           // adaper.setData(bean.getData());
                            adaper.addData(bean.getData());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(ChongZhiActivity.this,response.body());
                    }
                });


    }
    private static IWXAPI WXapi;
    private String WX_APP_ID = "wxddf17683ec437cfa";
    PayReq req;
    @OnClick({R.id.person_home_back, R.id.chongzhi_alipay, R.id.chongzhi_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_home_back:
                finish();
                break;
            case R.id.chongzhi_alipay:

                break;
            case R.id.chongzhi_weixin:
                if(pos==null){
                    CommentUtils.toast(ChongZhiActivity.this,"请选择金额");
                    return;
                }

                //调用支付

                payWeiXin();

                break;
        }
    }

    private void payWeiXin() {
        WXapi = WXAPIFactory.createWXAPI(ChongZhiActivity.this, WX_APP_ID, false);
        WXapi.registerApp(WX_APP_ID);
        OkGo.<String>post(Contants.purchase)
                .params("token", Sputils.getString(ChongZhiActivity.this, "token", ""))
                .params("type_id", pos+1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            WxPayBean bean = gson.fromJson(response.body(),WxPayBean.class);
                            req = new PayReq();
                            req.appId = bean.getData().getAppid();
                            req.partnerId = bean.getData().getPartnerid();
                            req.prepayId = bean.getData().getPrepayid();
                            req.nonceStr = bean.getData().getNoncestr();
                            req.timeStamp = bean.getData().getTimestamp()+"";
                            req.packageValue = bean.getData().getPackageX();
                            req.sign = bean.getData().getSign();
                            WXapi.sendReq(req);
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(ChongZhiActivity.this, "支付失败");
                    }
                });
    }
}
