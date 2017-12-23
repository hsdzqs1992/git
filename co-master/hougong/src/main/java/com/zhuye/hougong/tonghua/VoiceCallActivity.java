package com.zhuye.hougong.tonghua;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vmloft.develop.library.tools.utils.VMLog;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.find.LiWuAdapter;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.LoginActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzan13 on 2016/10/18.
 * <p>
 * 音频通话界面处理
 */
public class VoiceCallActivity extends CallActivity {

    // 使用 ButterKnife 注解的方式获取控件
    @BindView(R.id.layout_root)
    View rootView;
    @BindView(R.id.text_call_state)
    TextView callStateView;
    @BindView(R.id.text_call_time)
    TextView callTimeView;
    @BindView(R.id.img_call_avatar)
    ImageView avatarView;
    @BindView(R.id.text_call_username)
    TextView usernameView;
    @BindView(R.id.btn_exit_full_screen)
    ImageButton exitFullScreenBtn;
    @BindView(R.id.btn_mic_switch)
    ImageButton micSwitch;
    @BindView(R.id.btn_speaker_switch)
    ImageButton speakerSwitch;
    @BindView(R.id.btn_record_switch)
    ImageButton recordSwitch;
    @BindView(R.id.fab_reject_call)
    FloatingActionButton rejectCallFab;
    @BindView(R.id.fab_end_call)
    FloatingActionButton endCallFab;
    @BindView(R.id.fab_answer_call)
    FloatingActionButton answerCallFab;
    @BindView(R.id.img_call_background)
    ImageView imgCallBackground;
    @BindView(R.id.btn_record_songli)
    ImageButton btnRecordSongli;
    @BindView(R.id.layout_calling)
    LinearLayout layoutCalling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_call);

        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");


        if (type.equals("fa")) {
            money = getIntent().getStringExtra("money");
            price = getIntent().getStringExtra("price");
            uid = getIntent().getStringExtra("uid");
            face = getIntent().getStringExtra("face");
            nickname = getIntent().getStringExtra("nickname");
            toname = getIntent().getStringExtra("toname");
            //问题
            totaltime = Long.valueOf(Integer.parseInt(money) / Integer.parseInt(price));
        } else if (type.equals("shou")) {
            ext = getIntent().getStringExtra("ext");
            toname = getIntent().getStringExtra("fromname");
            if (ext.contains("uid")) {
                // todo

                // bug
                String[] data = ext.split(",");
                fromuid = data[0].substring(3);
                fromface = data[1].substring(4);
                fromname = data[2].substring(8);
            } else {
                String[] data = ext.split(",");
                fromuid = data[0];
                fromface = data[1];
               // fromname = data[2];
                fromname = "df";
            }

        }
        EMClient.getInstance().chatManager().addMessageListener(listener1);
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(listener1);
    }

    private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bundle ben = msg.getData();
        String name = ben.getString("name");
        String img = ben.getString("giftimg");
        CommentUtils.toast(VoiceCallActivity.this,name+img);
    }
};

    EMMessageListener listener1 = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            Log.i("as",list.toString());
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {
//收到透传消息
            Log.i("as",list.toString());
            if(list.size()>0){
                EMMessage message =  list.get(0);
                //Log.i("as",message.toString());
                try {

//                    Log.i("as",message.getStringAttribute("name"));
//
//                    Log.i("as",message.getStringAttribute("giftimg"));
                    Log.i("as",Thread.currentThread().getName());

                   Message mess =  handler.obtainMessage();
                    //mess.arg1 = message.getStringAttribute("name");
                    Bundle ban = new Bundle();
                    ban.putString("name",message.getStringAttribute("name"));
                    ban.putString("giftimg",message.getStringAttribute("giftimg"));

                    mess.setData(ban);
                    handler.sendMessage(mess);
                  //  Log.i("as",message.get);

                    //Log.i("as",((EMCmdMessageBody)message.getBody()).getParams().toString());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onMessageRead(List<EMMessage> list) {
            Log.i("as",list.toString());
        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {
            Log.i("as",list.toString());
        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {
            Log.i("as",o.toString());
        }
    };
String toname;
    String fromuid;
    String fromface;
    String fromname;

    String type;
    private Long totaltime;
    String money;
    String price;

    /**
     * 重载父类方法,实现一些当前通话的操作，
     */
    @Override
    protected void initView() {
        super.initView();
        if (CallManager.getInstance().isInComingCall()) {
            endCallFab.setVisibility(View.GONE);
            answerCallFab.setVisibility(View.VISIBLE);
            rejectCallFab.setVisibility(View.VISIBLE);
            callStateView.setText(R.string.call_connected_is_incoming);
        } else {
            endCallFab.setVisibility(View.VISIBLE);
            answerCallFab.setVisibility(View.GONE);
            rejectCallFab.setVisibility(View.GONE);
            callStateView.setText(R.string.call_connecting);
        }

        // usernameView.setText(CallManager.getInstance().getChatId());
        Glide.with(VoiceCallActivity.this).load(fromface).placeholder(R.drawable.alert_bg).into(avatarView);
        usernameView.setText(fromname);
        micSwitch.setActivated(!CallManager.getInstance().isOpenMic());
        speakerSwitch.setActivated(CallManager.getInstance().isOpenSpeaker());
        recordSwitch.setActivated(CallManager.getInstance().isOpenRecord());

        // 判断当前通话时刚开始，还是从后台恢复已经存在的通话
        if (CallManager.getInstance().getCallState() == CallManager.CallState.ACCEPTED) {
            endCallFab.setVisibility(View.VISIBLE);
            answerCallFab.setVisibility(View.GONE);
            rejectCallFab.setVisibility(View.GONE);
            callStateView.setText(R.string.call_accepted);
            refreshCallTime();
        }
    }

    /**
     * 界面控件点击监听器
     */
    @OnClick({
            R.id.btn_exit_full_screen, R.id.btn_mic_switch, R.id.btn_speaker_switch, R.id.btn_record_switch, R.id.fab_reject_call,
            R.id.fab_end_call, R.id.fab_answer_call,R.id.btn_record_songli
    })
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_record_songli:
                sendLiWu();
                break;

            case R.id.btn_exit_full_screen:
                // 最小化通话界面
                exitFullScreen();
                break;
            case R.id.btn_mic_switch:
                // 麦克风开关
                onMicrophone();
                break;
            case R.id.btn_speaker_switch:
                // 扬声器开关
                onSpeaker();
                break;
            case R.id.btn_record_switch:
                // 录制开关
                recordCall();
                break;
            case R.id.fab_end_call:
                // 结束通话
                endCall();
                break;
            case R.id.fab_reject_call:
                // 拒绝接听通话
                rejectCall();
                break;
            case R.id.fab_answer_call:
                // 接听通话
                answerCall();


                break;
        }
    }

    protected void requestLiwuData() {
        OkGo.<String>post(Contants.giftlist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                LiWu liwu= gson.fromJson(response.body(),LiWu.class);
                                View vie = View.inflate(VoiceCallActivity.this,R.layout.bottom_window,null);
                                final PopupWindow popupWindow = new PopupWindow(VoiceCallActivity.this);
                                popupWindow.setContentView(vie);
                                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                                popupWindow.setHeight(DensityUtil.dip2px(VoiceCallActivity.this,349f));
                                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                                popupWindow.setOutsideTouchable(true);
                                popupWindow.setFocusable(true);
                                ViewPager vp = vie.findViewById(R.id.viewpager);
                                LiWuAdapter aa = new LiWuAdapter(VoiceCallActivity.this,1,liwu.getData(),uid,"");
                                vp.setAdapter(aa);
                                aa.setliwueand(new LiWuAdapter.Liwuanswer() {
                                    @Override
                                    public void success(Response<String> response,String url) {
                                        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
                                        String action = "songli";//action可以自定义
                                        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
                                        String toUsername = toname;//发送给某个人
                                        cmdMsg.setTo(toUsername);
                                        //liwu 的地址
                                        cmdMsg.addBody(cmdBody);
                                        cmdMsg.setAttribute("name", Sputils.getString(VoiceCallActivity.this,"nickname","haha"));
                                        cmdMsg.setAttribute("giftimg",url);
                                        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
                                        CommentUtils.toast(VoiceCallActivity.this,"fasongchenggong");
                                    }
                                    @Override
                                    public void failed(Response<String> response) {

                                    }
                                });
                                //移动点 // TODO: 2017/12/2
                                vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                        // TODO: 2017/12/11 0011 红点的处理
                                    }

                                    @Override
                                    public void onPageSelected(int position) {

                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int state) {

                                    }
                                });
                                popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
                                vie.findViewById(R.id.songliwu).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        CommentUtils.toast(VoiceCallActivity.this,"songba");
                                    }
                                });




                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("208")){
                            startActivity(new Intent(VoiceCallActivity.this, LoginActivity.class));
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    private void sendLiWu() {

        requestLiwuData();




    }


    /**
     * 接听通话
     */
    @Override
    protected void answerCall() {
        super.answerCall();

        endCallFab.setVisibility(View.VISIBLE);
        rejectCallFab.setVisibility(View.GONE);
        answerCallFab.setVisibility(View.GONE);
    }

    /**
     * 退出全屏通话界面
     */
    private void exitFullScreen() {
        CallManager.getInstance().addFloatWindow();
        onFinish();
    }

    /**
     * 麦克风开关，主要调用环信语音数据传输方法
     */
    private void onMicrophone() {
        try {
            // 根据麦克风开关是否被激活来进行判断麦克风状态，然后进行下一步操作
            if (micSwitch.isActivated()) {
                // 设置按钮状态
                micSwitch.setActivated(false);
                // 暂停语音数据的传输
                EMClient.getInstance().callManager().resumeVoiceTransfer();
                CallManager.getInstance().setOpenMic(true);
            } else {
                // 设置按钮状态
                micSwitch.setActivated(true);
                // 恢复语音数据的传输
                EMClient.getInstance().callManager().pauseVoiceTransfer();
                CallManager.getInstance().setOpenMic(false);
            }
        } catch (HyphenateException e) {
            VMLog.e("exception code: %d, %s", e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 扬声器开关
     */
    private void onSpeaker() {
        // 根据按钮状态决定打开还是关闭扬声器
        if (speakerSwitch.isActivated()) {
            // 设置按钮状态
            speakerSwitch.setActivated(false);
            CallManager.getInstance().closeSpeaker();
            CallManager.getInstance().setOpenSpeaker(false);
        } else {
            // 设置按钮状态
            speakerSwitch.setActivated(true);
            CallManager.getInstance().openSpeaker();
            CallManager.getInstance().setOpenSpeaker(true);
        }
    }

    /**
     * 录制通话内容 TODO 后期实现
     */
    private void recordCall() {
        Snackbar.make(rootView, "暂未实现", Snackbar.LENGTH_LONG).show();
        // 根据开关状态决定是否开启录制
        if (recordSwitch.isActivated()) {
            // 设置按钮状态
            recordSwitch.setActivated(false);
            CallManager.getInstance().setOpenRecord(false);
        } else {
            // 设置按钮状态
            recordSwitch.setActivated(true);
            CallManager.getInstance().setOpenRecord(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(CallEvent event) {
        if (event.isState()) {
            refreshCallView(event);
        }
        if (event.isTime()) {
            // 不论什么情况都检查下当前时间
            refreshCallTime();
        }
    }

    /**
     * 刷新通话界面
     */
    private void refreshCallView(CallEvent event) {
        EMCallStateChangeListener.CallError callError = event.getCallError();
        EMCallStateChangeListener.CallState callState = event.getCallState();
        switch (callState) {
            case CONNECTING: // 正在呼叫对方，TODO 没见回调过
                VMLog.i("正在呼叫对方" + callError);
                break;
            case CONNECTED: // 正在等待对方接受呼叫申请（对方申请与你进行通话）
                VMLog.i("正在连接" + callError);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (CallManager.getInstance().isInComingCall()) {
                            callStateView.setText(R.string.call_connected_is_incoming);
                        } else {
                            callStateView.setText(R.string.call_connected);
                        }
                    }
                });
                break;
            case ACCEPTED: // 通话已接通
                VMLog.i("通话已接通");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callStateView.setText(R.string.call_accepted);
                    }
                });
                break;
            case DISCONNECTED: // 通话已中断
                VMLog.i("通话已结束" + callError);

                //在这 处理对方挂断记录时间


                onFinish();
                break;
            case NETWORK_DISCONNECTED:
                Toast.makeText(activity, "对方网络断开", Toast.LENGTH_SHORT).show();
                VMLog.i("对方网络断开");
                break;
            case NETWORK_NORMAL:
                VMLog.i("网络正常");
                break;
            case NETWORK_UNSTABLE:
                if (callError == EMCallStateChangeListener.CallError.ERROR_NO_DATA) {
                    VMLog.i("没有通话数据" + callError);
                } else {
                    VMLog.i("网络不稳定" + callError);
                }
                break;
            case VIDEO_PAUSE:
                Toast.makeText(activity, "对方已暂停视频传输", Toast.LENGTH_SHORT).show();
                VMLog.i("对方已暂停视频传输");
                break;
            case VIDEO_RESUME:
                Toast.makeText(activity, "对方已恢复视频传输", Toast.LENGTH_SHORT).show();
                VMLog.i("对方已恢复视频传输");
                break;
            case VOICE_PAUSE:
                Toast.makeText(activity, "对方已暂停语音传输", Toast.LENGTH_SHORT).show();
                VMLog.i("对方已暂停语音传输");
                break;
            case VOICE_RESUME:
                Toast.makeText(activity, "对方已恢复语音传输", Toast.LENGTH_SHORT).show();
                VMLog.i("对方已恢复语音传输");
                break;
            default:
                break;
        }
    }

    /**
     * 刷新通话时间显示
     */

    private void refreshCallTime() {
        t = CallManager.getInstance().getCallTime();

        //// TODO: 2017/12/13 0013 chuli 
        Log.i("as", t + "");

        if (type.equals("fa")) {
            if (t > 180) {
                if ((t - 180) == totaltime * 60) {
                    CommentUtils.toast(VoiceCallActivity.this, "余额不足，请充值！");
                    CallManager.getInstance().endCall();
                    Intent in = new Intent();
                    in.putExtra("time", t);
                    setResult(100, in);
                    finish();
                }
            }
        }


        int h = t / 60 / 60;
        int m = t / 60 % 60;
        int s = t % 60 % 60;
        String time = "";
        if (h > 9) {
            time = "" + h;
        } else {
            time = "0" + h;
        }
        if (m > 9) {
            time += ":" + m;
        } else {
            time += ":0" + m;
        }
        if (s > 9) {
            time += ":" + s;
        } else {
            time += ":0" + s;
        }
        if (!callTimeView.isShown()) {
            callTimeView.setVisibility(View.VISIBLE);
        }
        callTimeView.setText(time);
    }

    /**
     * 屏幕方向改变回调方法
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onUserLeaveHint() {
        //super.onUserLeaveHint();
        exitFullScreen();
    }

    /**
     * 通话界面拦截 Back 按键，不能返回
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exitFullScreen();
    }
}
