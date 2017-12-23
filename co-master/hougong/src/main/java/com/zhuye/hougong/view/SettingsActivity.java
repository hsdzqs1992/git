package com.zhuye.hougong.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {



    @BindView(R.id.stting_bangding)
    ImageView sttingBangding;
    @BindView(R.id.stting_heimingdan)
    ImageView sttingHeimingdan;
    @BindView(R.id.stting_yinsehn)
    ImageView sttingYinsehn;
    @BindView(R.id.stting_feedback)
    ImageView sttingFeedback;
    @BindView(R.id.setting_qingchu)
    TextView settingQingchu;
    @BindView(R.id.setting_qingchuhuancun)
    TextView settingQingchuhuancun;
    @BindView(R.id.stting_guanyu)
    ImageView sttingGuanyu;
    @BindView(R.id.setting_logout)
    RelativeLayout settingLogout;

    private int yinshen = 1;
    ImageView back;
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
    @Override
    protected void initview() {
        super.initview();
        if (isImmersionBarEnabled())
            initImmersionBar();
        back = findViewById(R.id.person_detail_back);
        if(Sputils.getBoolean(this,"yinshen",false)) {
                sttingYinsehn.setImageResource(R.drawable.open);
        }else {
            sttingYinsehn.setImageResource(R.drawable.close);

        }
    }

    protected void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getResId() {
        return R.layout.activity_settings;
    }

    @OnClick({R.id.stting_bangding, R.id.stting_heimingdan, R.id.stting_yinsehn, R.id.stting_feedback, R.id.setting_qingchu, R.id.setting_qingchuhuancun, R.id.stting_guanyu, R.id.setting_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stting_bangding:
                break;
            case R.id.stting_heimingdan:
                startActivity(new Intent(SettingsActivity.this,BlackNumberActivity.class));
                break;
            case R.id.stting_yinsehn:
                if (Sputils.getBoolean(this,"yinshen",false)) {

                    buyinshen();
                 }else {
                    yinShen();

                  }

                break;
            case R.id.stting_feedback:
                startActivity(new Intent(SettingsActivity.this,FeedBackActivity.class));
                break;
            case R.id.setting_qingchu:
                break;
            case R.id.setting_qingchuhuancun:
                break;
            case R.id.stting_guanyu:
                break;
            case R.id.setting_logout:
                logout();
                break;
        }
   }

    private void buyinshen() {
        onYinShen(1);
    }

    private void yinShen() {
        final AlertDialog dialog1 = new  AlertDialog.Builder(SettingsActivity.this).create();
        View view = View.inflate(SettingsActivity.this,R.layout.yinshen,null);
        dialog1.setView(view);
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog1!=null&&dialog1.isShowing()){
                    dialog1.dismiss();
                }
                onYinShen(2);
            }
        });

        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog1!=null&&dialog1.isShowing()){
                    dialog1.dismiss();
                }
            }
        });
        dialog1.show();

    }

    private void logout(){
        OkGo.<String>post(Contants.logout)
                .params("token", Sputils.getString(SettingsActivity.this,"token",""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        CommentUtils.toast(SettingsActivity.this,"退出失败");
                    }
                    @Override
                    protected void excuess(Response<String> response) {
                        Sputils.setString(SettingsActivity.this,"token","");

                        //  退出三方登录
                        EMClient.getInstance().logout(true, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        CommentUtils.toast(SettingsActivity.this,"退出成功");
                                        EventBus.getDefault().post(new MessageEvent());
                                        startActivity(new Intent(SettingsActivity.this,LoginActivity.class));
                                        finish();
                                    }
                                });


                            }

                            @Override
                            public void onError(int i, String s) {
                                finish();
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
    }

    //处理隐身的逻辑
    private void onYinShen(final int type) {
        OkGo.<String>post(Contants.stealth)
                .params("token", Sputils.getString(SettingsActivity.this,"token",""))
                .params("type",type + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")){
                            CommentUtils.toast(SettingsActivity.this,"设置成功");
                        }
                        if(type == 2){
                            Sputils.setBoolean(SettingsActivity.this,"yinshen",true);
                            sttingYinsehn.setImageResource(R.drawable.open);
                        }  else if(type == 1){
                            Sputils.setBoolean(SettingsActivity.this,"yinshen",false);
                            sttingYinsehn.setImageResource(R.drawable.close);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(SettingsActivity.this,"设置失败");
                    }
                });

    }
}
