package com.zhuye.hougong.view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.YaoqingBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YanQingJiangLiActivity extends AppCompatActivity {


    @BindView(R.id.yaoqing_bg)
    ImageView yaoqingBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan_qing_jiang_li2);
        ButterKnife.bind(this);




        initData();
    }
    ShareAction acion;
    YaoqingBean bean;

    private void initData() {
        OkGo.<String>post(Contants.inv_code)
                .params("token", Sputils.getString(YanQingJiangLiActivity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            bean = gson.fromJson(response.body(), YaoqingBean.class);

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    @OnClick(R.id.yaoqing_bg)
    public void onViewClicked() {

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        if(TextUtils.isEmpty(Contants.BASE_URL+bean.getData())){
            return;
        }
        UMWeb web = new UMWeb(Contants.BASE_URL+bean.getData());
        web.setTitle("来看看吧");//标题
        //web.setThumb(thumb);  //缩略图


//        })
        //web.setDescription("my description");//描述 .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)

        acion = new ShareAction(YanQingJiangLiActivity.this)
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE)
                .withText("sadf")
                .withMedia(web)
                .setCallback(umShareListener);
//                .setShareboardclickCallback(new ShareBoardlistener() {
//                    @Override
//                 public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                    acion.setPlatform(snsPlatform.mPlatform).share();
//                }
//                });
        //        .setShareboardclickCallback(new ShareBoardlistener() {
//            @Override
//            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                new ShareAction(YanQingJiangLiActivity.this)
//                        .setPlatform(snsPlatform.mPlatform)
//                        .share();
//            }

       // UMShareAPI.get(this).getPlatformInfo(this,share_media, authListener);
        acion.open();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

     UMShareListener umShareListener = new UMShareListener(){

         @Override
         public void onStart(SHARE_MEDIA share_media) {

         }

         @Override
         public void onResult(SHARE_MEDIA share_media) {
             Toast.makeText(YanQingJiangLiActivity.this,"成功了",Toast.LENGTH_LONG).show();
         }

         @Override
         public void onError(SHARE_MEDIA share_media, Throwable throwable) {
             Toast.makeText(YanQingJiangLiActivity.this,"失败"+throwable.getMessage(),Toast.LENGTH_LONG).show();
         }

         @Override
         public void onCancel(SHARE_MEDIA share_media) {

         }
     };
}
