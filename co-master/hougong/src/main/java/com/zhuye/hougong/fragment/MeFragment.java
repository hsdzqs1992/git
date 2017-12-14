package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.shawnlin.numberpicker.NumberPicker;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.me.MeBottomAdapter;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.bean.PersonInfoBean;
import com.zhuye.hougong.city.ChooseAddressActivity;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.tonghua.CallManager;
import com.zhuye.hougong.tonghua.VoiceCallActivity;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.DongTaiActivity;
import com.zhuye.hougong.view.FansActivity;
import com.zhuye.hougong.view.GuanZhuActivity;
import com.zhuye.hougong.view.LoginActivity;
import com.zhuye.hougong.view.MyWaletActivity;
import com.zhuye.hougong.view.PersonDetailActivity;
import com.zhuye.hougong.view.SelectPictureActivity;
import com.zhuye.hougong.view.SettingsActivity;
import com.zhuye.hougong.view.ShenQing2Activity;
import com.zhuye.hougong.view.ShengVIP1Activity;
import com.zhuye.hougong.view.WhoSendLiWuActivity;
import com.zhuye.hougong.view.YanQingJiangLiActivity;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;
import com.zhuye.hougong.wxapi.WxPayBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.zhuye.hougong.R.id.me_yinpin_tv2;


/**
 * Created by zzzy on 2017/11/20.
 */public class MeFragment extends Fragment {


    //private MyToolbar myToolbar;

    RoundedCornerImageView cornerImageView;
    ImageView setiv;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_friends)
    TextView tvFriends;
    Unbinder unbinder;
    //    @BindView(R.id.me_recycleview)
//    RecyclerView meRecycleview;
    MeBottomAdapter meBottomAdapter;
    @BindView(R.id.avatar)
    RoundedCornerImageView avatar;
    @BindView(R.id.me_name)
    TextView meName;
    @BindView(R.id.me_id)
    TextView meId;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.zuopinji)
    RelativeLayout zuopinji;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.btn_sign)
    TextView btnSign;
    @BindView(R.id.tag_vip)
    TextView tagVip;
    @BindView(R.id.me_yinpin_iv)
    ImageView meYinpinIv;
    @BindView(R.id.me_yinpin_tv)
    TextView meYinpinTv;
    @BindView(R.id.me_yinpin_iv2)
    ImageView meYinpinIv2;
    @BindView(R.id.me_yinpin_go)
    ImageView meYinpinGo;
    @BindView(me_yinpin_tv2)
    TextView meYinpinTv2;
    @BindView(R.id.me_shipin_iv)
    ImageView meShipinIv;
    @BindView(R.id.me_shipin_tv)
    TextView meShipinTv;
    @BindView(R.id.me_shipin_iv2)
    ImageView meShipinIv2;
    @BindView(R.id.me_shipin_go)
    ImageView meShipinGo;
    @BindView(R.id.me_shipin_tv2)
    TextView meShipinTv2;
    @BindView(R.id.me_qianbao)
    LinearLayout meQianbao;
    @BindView(R.id.me_photos)
    LinearLayout mePhotos;
    @BindView(R.id.me_yaoqing)
    LinearLayout meYaoqing;
    @BindView(R.id.me_lookme)
    LinearLayout meLookme;
    @BindView(R.id.me_sengliwu)
    LinearLayout meSengliwu;
    @BindView(R.id.me_shengvip)
    LinearLayout meShengvip;
    @BindView(R.id.fragment_nvshen)
    TextView fragmentNvshen;

    private Boolean isShouYuYin;
    private Boolean isShouShiPin;
    private static final String SHOWYINPIN = "shouyinpin";
    private static final String SHOWSHIPIN = "shoushipin";


    @Override
    public void onDestroy() {

        super.onDestroy();

    }


    //即时处理个人中心的界面
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */
        if (TextUtils.isEmpty(Sputils.getString(getActivity(), "token", ""))) {
            //退出登录了  用户名至为空
            meName.setText("请登录");
        } else {
            //重新加载数据
            initData();
        }
        //CommentUtils.toast(getActivity(),"hhhhhhhhhhh");
    }

    ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_mee, null);
        //myToolbar = view.findViewById(R.id.mee_toolbar);
        // myToolbar.hideView(myToolbar.homeLeftIcon);
        cornerImageView = view.findViewById(R.id.avatar);
        setiv = view.findViewById(R.id.iv_set);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);


        List<String> data = new ArrayList<>();

        data.add("我的钱包");
        data.add("自拍认证");
        data.add("邀请奖励");
        data.add("谁看过我");
        data.add("谁送过礼物");
        data.add("升级VIP");

//        meBottomAdapter= new MeBottomAdapter(getActivity(),data);
//        meRecycleview.setAdapter(meBottomAdapter);
//        meRecycleview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        isShouShiPin = Sputils.getBoolean(getActivity(), SHOWSHIPIN, false);
        isShouYuYin = Sputils.getBoolean(getActivity(), SHOWYINPIN, false);
        meShipinIv2.setImageResource(isShouShiPin ? R.drawable.open : R.drawable.close);
        meYinpinIv2.setImageResource(isShouYuYin ? R.drawable.open : R.drawable.close);
        //使用eventbus 即时完成数据更新
        initData();
        initListener();

        //shiPinToggle();
        //yuYinToggle();
        return view;
    }


    private void initData() {

        String token = Sputils.getString(getActivity(), "token", "");
        if (TextUtils.isEmpty(token)) {
            return;
        }
        //获取个人信息
        OkGo.<String>post(Contants.information)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CommentUtils.toast(getActivity(), response.body());
                        Log.i("---------", response.body());
                        try {
                            Gson gson = new Gson();
                            PersonInfoBean personInfoBean = gson.fromJson(response.body(), PersonInfoBean.class);
                            if (response.body().contains("200")) {

                                Glide.with(getActivity()).load(Contants.BASE_URL+personInfoBean.getData().getFace())
                                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(avatar);
                                //头部
                                meName.setText(personInfoBean.getData().getNickname());
                                //无id字段
                                //meId.setText(personInfoBean.getData().g);
                                meId.setVisibility(View.GONE);

                                if(personInfoBean.getData().getLevel().equals("0")){
                                    tagVip.setText("平民");
                                }else if(personInfoBean.getData().getUsertype().equals("2")){
                                    tagVip.setText("VIP");
                                }

                                tvFollow.setText(personInfoBean.getData().getInterest() + "\n关注");
                                tvFans.setText(personInfoBean.getData().getLove()+"\n粉丝");
                                tvFriends.setText(personInfoBean.getData().getTrends()+"\n动态");

                                if(personInfoBean.getData().getUsertype().equals("1")){
                                    fragmentNvshen.setText("主播");
                                }else if(personInfoBean.getData().getUsertype().equals("2")){
                                    fragmentNvshen.setText("申请中");
                                }else if(personInfoBean.getData().getUsertype().equals("0")){
                                    fragmentNvshen.setText("成为主播");
                                }

                                //语音视频初始化
                                if(personInfoBean.getData().getVoice_open().equals("1")){
                                    meYinpinIv2.setImageResource(R.drawable.open);
                                }else if(personInfoBean.getData().getVoice_open().equals("0")){
                                    meYinpinIv2.setImageResource(R.drawable.close);
                                }


                                meYinpinTv2.setText(personInfoBean.getData().getVoice_money()+"金币/分钟");
                                meShipinTv2.setText(personInfoBean.getData().getVideo_money()+"金币/分钟");
                                // me_yinpin_tv2
                                if(personInfoBean.getData().getVideo_open().equals("1")){
                                    meShipinIv2.setImageResource(R.drawable.open);
                                }else if(personInfoBean.getData().getVideo_open().equals("0")){
                                    meShipinIv2.setImageResource(R.drawable.close);
                                }


    //                            personName.setText(personInfoBean.getData().getNickname());
    //                            personAge.setText(personInfoBean.getData().getCity() + "sdfa");
    //                            personXingzuo.setText(personInfoBean.getData().getCon()+"老虎");
    //                            personZone.setText(personInfoBean.getData().getCity()+"老虎");
    //                            personJibie.setText(personInfoBean.getData().getLevel()+"平");
    //                            if(personInfoBean.getData().getSex().contains("0")){
    //                                personBtnNan.setChecked(true);
    //                                personBtnNv.setChecked(false);
    //                            }else{
    //                                personBtnNan.setChecked(false);
    //                                personBtnNv.setChecked(true);
    //                            }
                            } else {

                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void initListener() {
        cornerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = Sputils.getString(getActivity(), "token", "");
                //startActivity(new Intent(getActivity(), LoginActivity.class));
                // startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                if (TextUtils.isEmpty(token)) {
                    //没登录
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    //登录
                    startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                }
            }
        });
        setiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({ me_yinpin_tv2,R.id.me_shipin_tv2,R.id.tv_follow,R.id.tag_vip,R.id.fragment_nvshen, R.id.tv_fans, R.id.tv_friends, R.id.me_yinpin_iv2, R.id.me_yinpin_go, R.id.me_shipin_iv2, R.id.me_shipin_go, R.id.me_qianbao, R.id.me_photos, R.id.me_yaoqing, R.id.me_lookme, R.id.me_sengliwu, R.id.me_shengvip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case me_yinpin_tv2:
                settingShcarge(1);
                break;
            case R.id.me_shipin_tv2:
                settingShcarge(2);
                break;
            case R.id.tv_follow:
                startActivity(new Intent(getActivity(), GuanZhuActivity.class));
                break;
            case R.id.tv_fans:
                startActivity(new Intent(getActivity(), FansActivity.class));
                break;
            case R.id.tv_friends:
               // startActivity(new Intent(getActivity(), MyFriendsActivity.class));
                Intent intent = new Intent(getActivity(), DongTaiActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("token",Sputils.getString(getActivity(), "token", ""));
                startActivity(intent);

                break;
            case R.id.me_yinpin_iv2:
                yuYinToggle();
                //meYinpinIv2.setImageResource(R.drawable.close);
                break;
            case R.id.me_yinpin_go:
                settingShcarge(1);
                break;
            case R.id.me_shipin_iv2:
                shiPinToggle();
                break;
            case R.id.me_shipin_go:
                settingShcarge(2);
                break;
            case R.id.me_qianbao:
                startActivity(new Intent(getActivity(), MyWaletActivity.class));
                break;
            case R.id.me_photos:
                //startActivity(new Intent(getActivity(), MyFriendsActivity.class));
                startActivity(new Intent(getActivity(), SelectPictureActivity.class));
                break;
            case R.id.me_yaoqing:
                startActivity(new Intent(getActivity(), YanQingJiangLiActivity.class));
                break;
            case R.id.me_lookme:
               // callVoice();
               // startActivity(new Intent(getActivity(), LookMeActivity.class));
                Intent in = new Intent(getActivity(), ChooseAddressActivity.class);
                in.putExtra("now","sdf");
                startActivity(in);
                break;
            case R.id.me_sengliwu:
                startActivity(new Intent(getActivity(), WhoSendLiWuActivity.class));
                break;
            case R.id.me_shengvip:
               startActivity(new Intent(getActivity(), ShengVIP1Activity.class));
               // test();
                break;
            case R.id.fragment_nvshen:
                startActivity(new Intent(getActivity(), ShenQing2Activity.class));
                break;

            case R.id.tag_vip:
                //编辑资料
               // startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                break;
        }
    }

    private static IWXAPI WXapi;
    private String WX_APP_ID = "wxddf17683ec437cfa";
    PayReq req;
    private void test() {
        WXapi = WXAPIFactory.createWXAPI(getActivity(), WX_APP_ID, false);
        WXapi.registerApp(WX_APP_ID);
        OkGo.<String>post(Contants.purchase)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type_id", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(getActivity(), "设置成功");
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
                        CommentUtils.toast(getActivity(), "设置失败");
                    }
                });
    }

    int pos = 0;
    private void settingShcarge(final int type) {
        final AlertDialog dialog = new  AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(),R.layout.aliet,null);
        dialog.setView(view);

       // final String[] city = {"免费","10","20","30","50","100"};
        final List<String> data = new ArrayList();
        data.add("免费");
        NumberPicker picker = view.findViewById(R.id.picker);
        for(int i= 5;i<201;i=i+5){
            data.add(i+"");
        }
        final String[] city = new String[data.size()];
        for(int i = 0;i<data.size();i++){
            city[i]=data.get(i);
        }


        picker.setDisplayedValues(city);
        picker.setMinValue(0);
        picker.setMaxValue(city.length - 1);
        picker.setValue(0);
        //picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
         //int i =  picker.getValue();
        //Log.i("acy",i+"");

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
               // Log.i("acy",i+"");
                Log.i("acy",i1+"asdfsd");
                pos= i1;

            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
                final int money ;
                if(pos==0){
                    money = 0;
                }else {
                    money = Integer.parseInt(city[pos]);
                }
                OkGo.<String>post(Contants.charge)
                        .params("token", Sputils.getString(getActivity(), "token", ""))
                        .params("type", type)
                        .params("money", money)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                if (response.body().contains("200")) {
                                    CommentUtils.toast(getActivity(), "设置成功");
                                    if(type==1){
                                        meYinpinTv2.setText(money+"金币/分钟");
                                    }else if(type==2){
                                        meShipinTv2.setText(money+"金币/分钟");
                                    }
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                CommentUtils.toast(getActivity(), "设置失败");
                            }


                        });

            }
        });

        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }

    /**
     * 语音呼叫
     */
    private void callVoice() {
        //checkContacts();
        Intent intent = new Intent(getActivity(), VoiceCallActivity.class);
        CallManager.getInstance().setChatId("14725836922");
        CallManager.getInstance().setInComingCall(false);
        CallManager.getInstance().setCallType(CallManager.CallType.VOICE);
        startActivity(intent);
    }

    //
    private void shiPinToggle() {
        if (isShouShiPin) {
            meShipinIv2.setImageResource(R.drawable.close);
            closeFuWu(2);
        } else {
            meShipinIv2.setImageResource(R.drawable.open);
            openFuWu(2);
        }
        isShouShiPin = !isShouShiPin;
        Sputils.setBoolean(getActivity(), SHOWSHIPIN, isShouShiPin);
    }

    private void yuYinToggle() {
        if (isShouYuYin) {
            meYinpinIv2.setImageResource(R.drawable.close);
            //CommentUtils.toast(getActivity(), Sputils.getString(getActivity(), "token", ""));
            closeFuWu(1);
        } else {
            meYinpinIv2.setImageResource(R.drawable.open);
            //CommentUtils.toast(getActivity(), Sputils.getString(getActivity(), "token", ""));
            openFuWu(1);
        }
        isShouYuYin = !isShouYuYin;
        Sputils.setBoolean(getActivity(), SHOWYINPIN, isShouYuYin);
    }

    private void closeFuWu(int i) {
        OkGo.<String>post(Contants.YUSHI_CLODED)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type", i + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(getActivity(), "关闭成功");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(getActivity(), "关闭失败");
                    }


                });
    }


    private void openFuWu(int type) {
        OkGo.<String>post(Contants.YUSHI_OPEN)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type", type + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(getActivity(), "开启成功");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(getActivity(), "开启失败");
                    }
                });
    }


}
