package com.zhuye.hougong.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.zhuye.hougong.MainActivity;
import com.zhuye.hougong.bean.LoginCode;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.model.Modle;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import org.greenrobot.eventbus.EventBus;

import java.net.URLEncoder;

public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    private String WX_APP_ID = "wxddf17683ec437cfa";
    private BaseResp resp = null;
    // 获取第一步的code后，请求以下链接获取access_token
    private String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    // 获取用户个人信息
    private String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    private String WX_APP_SECRET = "1ab4d84b245a38badaa79f9f69ee979b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wxentry);
        api = WXAPIFactory.createWXAPI(this, WX_APP_ID, false);
        api.handleIntent(getIntent(), this);

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = "";
        if (resp == null) {
            resp = baseResp;
        }

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                String code = ((SendAuth.Resp) resp).code;

            /*
             * 将你前面得到的AppID、AppSecret、code，拼接成URL 获取access_token等等的信息(微信)
             */
                String get_access_token = getCodeRequest(code);

                OkGo.<String>post(get_access_token)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.i("sdfasd",response.body());
                                if(response.body().contains("access_token")){
                                    Gson gson = new Gson();
                                    WxLoginBean bean  = gson.fromJson(response.body(),WxLoginBean.class);
                                    String access_token = bean.getAccess_token();
                                    String openid  =  bean.getOpenid();
                                    String get_user_info_url = getUserInfo(access_token, openid);
                                    getUserInfo(get_user_info_url);
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                Log.i("sdfasd",response.body());
                            }
                        });

//                AsyncHttpClient client = new AsyncHttpClient(this);
//                client.post(get_access_token, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, JSONObject response) {
//                        // TODO Auto-generated method stub
//                        super.onSuccess(statusCode, response);
//                        try {
//
//
//                            if (!response.equals("")) {
//                                String access_token = response
//                                        .getString("access_token");
//                                String openid = response.getString("openid");
//                                String get_user_info_url = getUserInfo(
//                                        access_token, openid);
//                                getUserInfo(get_user_info_url);
//                            }
//
//                        } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                });

             //   finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "发送返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    /**
     * 获取access_token的URL（微信）
     *
     * @param code
     *            授权时，微信回调给的
     * @return URL
     */
    private String getCodeRequest(String code) {
        String result = null;
        GetCodeRequest = GetCodeRequest.replace("APPID",
                urlEnodeUTF8(WX_APP_ID));
        GetCodeRequest = GetCodeRequest.replace("SECRET",
                urlEnodeUTF8(WX_APP_SECRET));
        GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
        result = GetCodeRequest;
        return result;
    }

    private String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取用户个人信息的URL（微信）
     *
     * @param access_token
     *            获取access_token时给的
     * @param openid
     *            获取access_token时给的
     * @return URL
     */
    private String getUserInfo(String access_token, String openid) {
        String result = null;
        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
                urlEnodeUTF8(access_token));
        GetUserInfo = GetUserInfo.replace("OPENID", urlEnodeUTF8(openid));
        result = GetUserInfo;
        return result;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    /**
     * 通过拼接的用户信息url获取用户信息
     *
     * @param user_info_url
     */
    private void getUserInfo(String user_info_url) {
       // api.unregisterApp();
        OkGo.<String>post(user_info_url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("sdfasd",response.body());
                        if(response.body().contains("nickname")){
                            Gson gson = new Gson();
                            WxUserbean user = gson.fromJson(response.body(),WxUserbean.class);
                            OkGo.<String>post(Contants.sf_login)
                                    .params("login_type","weixin")
                                    .params("openid",user.getOpenid())
                                    .params("nickname",user.getNickname())
                                    .params("face",user.getHeadimgurl())
                                    .params("sex",user.getSex()+1)
                                    .execute(new MyCallback() {
                                        @Override
                                        protected void doFailue(Response<String> response) {
                                        }
                                        @Override
                                        protected void excuess(Response<String> response) {
                                            Gson gson = new Gson();
                                            //// TODO: 2017/11/28  解析错误
                                            LoginCode code = gson.fromJson(response.body(), LoginCode.class);
                                            Sputils.setString(WXEntryActivity.this,"token",code.getData().getToken());

                                            Modle.getInstance().loginSuccess(code.getData().getHx_username());
                                            //将用户保存到数据库
                                            Modle.getInstance().getAccountDao().saveUser(new com.zhuye.hougong.bean.UserInfo(code.getData().getHx_username()));
                                            //跳转
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    EventBus.getDefault().post(new MessageEvent());
                                                    CommentUtils.toast(WXEntryActivity.this,"登录成功");
                                                    startActivity(new Intent(WXEntryActivity.this,MainActivity.class));
                                                    finish();
                                                }
                                            });
                                        }
                                    });

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("sdfasd",response.body());
                    }
                });
//        AsyncHttpClient client = new AsyncHttpClient(this);
//        client.get(user_info_url, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, JSONObject response) {
//                // TODO Auto-generated method stub
//                super.onSuccess(statusCode, response);
//                try {
//
//                    System.out.println("获取用户信息:" + response);
//
//                    if (!response.equals("")) {
//                        String openid = response.getString("openid");
//                        String nickname = response.getString("nickname");
//                        String headimgurl = response.getString("headimgurl");
//
//                    }
//
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
    }



    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {





        finish();
    }
}
