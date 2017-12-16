package com.zhuye.hougong;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMCallManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.vmloft.develop.library.tools.utils.VMLog;
import com.zhuye.hougong.model.Modle;
import com.zhuye.hougong.tonghua.CallManager;
import com.zhuye.hougong.tonghua.CallReceiver;

import org.litepal.LitePal;

/**
 * Created by zzzy on 2017/11/23.
 */

public class HouGongApplition extends Application {


    static Gson gson;
    private CallReceiver callReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        gson = new Gson();
        MultiDex.install(this);

        EMOptions options =new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setAutoAcceptGroupInvitation(false);

        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);

      // EMClient.getInstance().setDebugMode(true);
        //EMClient.getInstance().init(this,options);
        //EMClient.getInstance().callManager().getIncomingCallBroadcastAction()

        if(EaseUI.getInstance().init(this, options)){
            CallManager.getInstance().init(this);


            EMCallManager  manager= EMClient.getInstance().callManager();
            String na= manager.getIncomingCallBroadcastAction();
            IntentFilter callFilter = new IntentFilter(na);
            if (callReceiver == null) {
                callReceiver = new CallReceiver();
            }

            registerReceiver(callReceiver, callFilter);
            setConnectionListener();
            Modle.getInstance().init(this);
        }

       // EMClient.getInstance().init(this,options);
        // 通话管理类的初始化



        LitePal.initialize(this);
        NineGridView.setImageLoader(new PicassoImageLoader());
        conn = this;

        PlatformConfig.setWeixin("wxddf17683ec437cfa","1ab4d84b245a38badaa79f9f69ee979b");
        PlatformConfig.setQQZone("1106501527","P5FViAE5NJahAybm");
        UMShareAPI.get(this);

    }

    /**
     * 设置连接监听
     */
    private void setConnectionListener() {
        EMConnectionListener connectionListener = new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int i) {
                String str = "" + i;
                switch (i) {
                    case EMError.USER_REMOVED:
                        str = "账户被移除";
                        break;
                    case EMError.USER_LOGIN_ANOTHER_DEVICE:
                        str = "其他设备登录";
                        break;
                    case EMError.USER_KICKED_BY_OTHER_DEVICE:
                        str = "其他设备强制下线";
                        break;
                    case EMError.USER_KICKED_BY_CHANGE_PASSWORD:
                        str = "密码修改";
                        break;
                    case EMError.SERVER_SERVICE_RESTRICTED:
                        str = "被后台限制";
                        break;
                }
                VMLog.i("onDisconnected %s", str);
            }
        };
        EMClient.getInstance().addConnectionListener(connectionListener);
    }

    private static Context conn;

    public static Context getContext() {
        return conn;
    }

    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    public static Gson getGson(){
        return gson;
    }
}
