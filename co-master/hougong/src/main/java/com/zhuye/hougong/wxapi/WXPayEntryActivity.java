package com.zhuye.hougong.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.hougong.utils.CommentUtils;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明的主题
        //setContentView(R.layout.activity_wxpay_entry);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        api = WXAPIFactory.createWXAPI(this, "wxddf17683ec437cfa");
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
    @Override
    public void onResp(BaseResp baseResp) {
        //支付成功
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode){
                case BaseResp.ErrCode.ERR_OK :
                    CommentUtils.toast(WXPayEntryActivity.this,"支付成功");
                    break;
                case BaseResp.ErrCode.ERR_SENT_FAILED :
                    CommentUtils.toast(WXPayEntryActivity.this,"发送失败");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    CommentUtils.toast(WXPayEntryActivity.this,"取消支付");
                    break;
                case BaseResp.ErrCode.ERR_UNSUPPORT:
                    CommentUtils.toast(WXPayEntryActivity.this,"手机不支持");
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    CommentUtils.toast(WXPayEntryActivity.this,"认证失败");
                    break;
            }
            finish();
        }
    }
}
