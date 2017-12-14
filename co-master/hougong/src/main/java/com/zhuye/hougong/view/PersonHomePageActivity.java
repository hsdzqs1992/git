package com.zhuye.hougong.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hyphenate.easeui.EaseConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.find.LiWuAdapter;
import com.zhuye.hougong.adapter.home.HomeLiWuAdapter;
import com.zhuye.hougong.adapter.home.JinengAdapter;
import com.zhuye.hougong.bean.CallBean;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.bean.PersonJiaoBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.tonghua.CallManager;
import com.zhuye.hougong.tonghua.VideoCallActivity;
import com.zhuye.hougong.tonghua.VoiceCallActivity;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.DividerItemDecoration;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import java.security.InvalidParameterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhuye.hougong.R.id.tousu;

public class PersonHomePageActivity extends AppCompatActivity {


    @BindView(R.id.person_home_back)
    ImageView personHomeBack;
    @BindView(R.id.person_home_touxiang)
    RoundedCornerImageView personHomeTouxiang;
    @BindView(R.id.person_home_name)
    TextView personHomeName;
    @BindView(R.id.person_home_id)
    TextView personHomeId;
    @BindView(R.id.person_home_vip)
    TextView personHomeVip;
    @BindView(R.id.person_home_persondetail)
    ImageView personHomePersondetail;
    @BindView(R.id.person_home_slider)
    SliderLayout personHomeSlider;
    @BindView(R.id.person_home_age)
    TextView personHomeAge;
    @BindView(R.id.person_home_dizhi)
    TextView personHomeDizhi;
    @BindView(R.id.person_home_jietong)
    TextView personHomeJietong;
    @BindView(R.id.person_home_jiage_liaotia)
    TextView personHomeJiageLiaotia;
    @BindView(R.id.person_home_jiage_video)
    TextView personHomeJiageVideo;
    @BindView(R.id.person_home_dongtai_shumu)
    TextView personHomeDongtaiShumu;
    @BindView(R.id.person_home_dongtai_tou)
    ImageView personHomeDongtaiTou;
    @BindView(R.id.person_home_dongtai_title)
    TextView personHomeDongtaiTitle;
    @BindView(R.id.person_home_gengxin_time)
    TextView personHomeGengxinTime;
    @BindView(R.id.person_home_dontai_detail)
    LinearLayout personHomeDontaiDetail;
    @BindView(R.id.person_home_liwu_shu)
    TextView personHomeLiwuShu;

    @BindView(R.id.person_home_video)
    Button personHomeVideo;
    @BindView(R.id.person_home_audio)
    Button personHomeAudio;
    @BindView(R.id.person_guanzhu)
    ImageView personGuanzhu;
    @BindView(R.id.person_dasang)
    ImageView personDasang;
    @BindView(R.id.person_liaotian)
    ImageView personLiaotian;
    @BindView(R.id.person_jineng)
    RecyclerView personJineng;
    @BindView(R.id.person_liwu)
    RecyclerView personLiwu;
    @BindView(R.id.person_home_tousu)
    TextView personHomeTousu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_home_page);
        ButterKnife.bind(this);

        //传入uid
        uid = getIntent().getStringExtra("uid");
        guanzhu = getIntent().getStringExtra("guanzhu");
        initView();
        initData();
    }

    private void initView() {
        if(guanzhu.equals("0")){
            personGuanzhu.setImageResource(R.drawable.gaungzhu_on);
        }else if(guanzhu.equals("1")){
            personGuanzhu.setImageResource(R.drawable.gaungzhu_off);
        }
    }

    String uid;
    String guanzhu;

    PersonJiaoBean person;

    private void initData() {
        //// TODO: 2017/12/5 得不到face


        OkGo.<String>post(Contants.communication)
                .params("uid", uid)
                .params("token", Sputils.getString(PersonHomePageActivity.this, "token", ""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {

                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        String i = response.body();
                        Gson gson = new Gson();
                        person = gson.fromJson(response.body(), PersonJiaoBean.class);
                        Log.i("-----", i);

                        if (person.getData().getLevel() == 0) {
                            personHomeVip.setVisibility(View.INVISIBLE);
                        } else {
                            personHomeVip.setVisibility(View.VISIBLE);
                        }

                        Glide.with(PersonHomePageActivity.this).load(Contants.BASE_URL + person.getData().getFace()).
                                placeholder(R.drawable.bg_vip).error(R.mipmap.ic_launcher).into(personHomeTouxiang);

                        personHomeName.setText(person.getData().getNickname());
                        personHomeId.setText(person.getData().getUid());
                        personHomeAge.setText(person.getData().getAge());
                        personHomeDizhi.setText(person.getData().getCity());
                        personHomeJietong.setText("接通率" + person.getData().getJtlv() + "%");

                        personHomeJiageLiaotia.setText(person.getData().getVoice_money() + "金币/每分钟");
                        personHomeJiageVideo.setText(person.getData().getVideo_money() + "金币/每分钟");


                        for (int j = 0; j < person.getData().getImg().size(); j++) {
                            DefaultSliderView defaultSliderView = new DefaultSliderView(PersonHomePageActivity.this);
                            defaultSliderView.image(Contants.BASE_URL + person.getData().getImg().get(j));
                            if (personHomeSlider != null)
                                personHomeSlider.addSlider(defaultSliderView);
                        }
                        personHomeDongtaiShumu.setText("动态(" + person.getData().getDynamic_count() + ")");
                        personHomeDongtaiTitle.setText(person.getData().getDynamic_content() + "wuneirong");

                        if (TextUtils.isEmpty(person.getData().getGift_count())) {
                            personHomeLiwuShu.setText("礼物(" + person.getData().getGift_count() + ")");
                        }


                        JinengAdapter ad = new JinengAdapter(PersonHomePageActivity.this);
                        personJineng.setAdapter(ad);
                        ad.addData(person.getData().getFeature());
                        personJineng.setLayoutManager(new LinearLayoutManager(PersonHomePageActivity.this));


                        HomeLiWuAdapter liwu = new HomeLiWuAdapter(PersonHomePageActivity.this);
                        personLiwu.setAdapter(liwu);
                        liwu.addData(person.getData().getGift());
                        //bug
                        personLiwu.setLayoutManager(new LinearLayoutManager(PersonHomePageActivity.this, LinearLayoutManager.HORIZONTAL, false));

                        DividerItemDecoration divider = new DividerItemDecoration(PersonHomePageActivity.this, LinearLayoutManager.HORIZONTAL);
                        personLiwu.addItemDecoration(divider);

                    }
                });
//        OkGo.<String>post(Contants.personcenter)
//                .params("uid",uid)
//                .params("token", Sputils.getString(PersonHomePageActivity.this,"token",""))
//                .execute(new MyCallback() {
//                    @Override
//                    protected void doFailue() {
//
//                    }
//
//                    @Override
//                    protected void excuess(Response<String> response) {
//                        String i = response.body();
//                        Gson gson = new Gson();
//                        person= gson.fromJson(response.body(),PersonDetailBean.class);
//                        Log.i("-----",i);
//                        Glide.with(PersonHomePageActivity.this).load(Contants.BASE_URL+person.getData().getFace()).into(personHomeTouxiang);
//                        personHomeName.setText(person.getData().getNickname());
//                        personHomeId.setText(person.getData().getUid());
//                        personHomeAge.setText(person.getData().getAge());
//                        personHomeDizhi.setText(person.getData().getCity());
//
//                    }
//                });
    }


    @OnClick({R.id.person_home_tousu,R.id.person_home_back, R.id.person_home_persondetail, R.id.person_home_dontai_detail, R.id.person_home_video, R.id.person_home_audio, R.id.person_guanzhu, R.id.person_dasang, R.id.person_liaotian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            
            case R.id.person_home_tousu:
                
                tousu();
                
                break;
            case R.id.person_home_back:
                finish();
                break;
            case R.id.person_home_persondetail:
                if (person != null) {
                    Intent intent = new Intent(PersonHomePageActivity.this, OtherPersonDetailActivity.class);
                    intent.putExtra("personinfo", person.getData().getUid());
                    startActivity(intent);
                }

                break;
            case R.id.person_home_dontai_detail:
                Intent intent = new Intent(PersonHomePageActivity.this, DongTaiActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("token", person.getData().getUid());
                startActivity(intent);
                break;
            case R.id.person_home_video:
                callVideo(getHuanXinid());
                break;
            case R.id.person_home_audio:
                callVoice(getHuanXinid());

                break;
            case R.id.person_guanzhu:

                guanzhuLOgo();
                break;
            case R.id.person_dasang:

                alertLiwu();
                break;
            case R.id.person_liaotian:
                Intent in = new Intent(PersonHomePageActivity.this, ChatAvtiviyt.class);
                in.putExtra(EaseConstant.EXTRA_USER_ID, getHuanXinid());
                startActivity(in);
                break;
        }
    }

    private void guanzhuLOgo() {

        if(guanzhu.equals("0")){
            OkGo.<String>post(Contants.love)
                    .params("token", Sputils.getString(PersonHomePageActivity.this,"token",""))
                    .params("uid",uid)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(PersonHomePageActivity.this,"关注失败");
                                 }

                                 @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(PersonHomePageActivity.this,"关注成功");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     //((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position)).setLove(1);
                                     personGuanzhu.setImageResource(R.drawable.gaungzhu_off);
                                     guanzhu ="1";
                                 }
                             }
                    );

        }else if(guanzhu.equals("1")){
            OkGo.<String>post(Contants.del_mylove)
                    .params("token", Sputils.getString(PersonHomePageActivity.this,"token",""))
                    .params("uid",uid)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(PersonHomePageActivity.this,"取消关注失败");
                                 }

                                 @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(PersonHomePageActivity.this,"取消关注");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     //((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position)).setLove(1);
                                     personGuanzhu.setImageResource(R.drawable.gaungzhu_on);
                                     guanzhu ="0";
                                 }
                             }
                    );
            personGuanzhu.setImageResource(R.drawable.gaungzhu_on);
        }


    }

    //// TODO: 2017/12/13 0013
    private void tousu() {

        final AlertDialog dialog = new  AlertDialog.Builder(PersonHomePageActivity.this).create();
        View view = View.inflate(PersonHomePageActivity.this,R.layout.aliet_tousu,null);
        dialog.setView(view);

        final EditText et = view.findViewById(tousu);


        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tousu = et.getText().toString().trim();
                if(TextUtils.isEmpty(tousu)){
                    CommentUtils.toast(PersonHomePageActivity.this,"请输入内容");
                    return;
                }

                OkGo.<String>post(Contants.report)
                        .params("token", Sputils.getString(PersonHomePageActivity.this,"token",""))
                        .params("uid",uid)
                        .params("content",tousu)
                        .execute(new MyCallback() {
                                     @Override
                                     protected void doFailue(Response<String> response) {
                                         CommentUtils.toast(PersonHomePageActivity.this,"投诉失败");
                                     }

                                     @Override
                                     protected void excuess(Response<String> response) {
                                         CommentUtils.toast(PersonHomePageActivity.this,"投诉成功了");
                                         if (dialog!=null&&dialog.isShowing()){
                                             dialog.dismiss();
                                         }
                                     }
                                 }
                        );

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

    private void alertLiwu() {

        OkGo.<String>post(Contants.giftlist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll", response.body());
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            try {
                                LiWu liwu = gson.fromJson(response.body(), LiWu.class);
                                View vie = View.inflate(PersonHomePageActivity.this, R.layout.bottom_window, null);
                                final PopupWindow popupWindow = new PopupWindow(PersonHomePageActivity.this);
                                popupWindow.setContentView(vie);
                                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                                popupWindow.setHeight(DensityUtil.dip2px(PersonHomePageActivity.this, 349f));
                                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                                popupWindow.setOutsideTouchable(true);
                                popupWindow.setFocusable(true);
                                initLiwuListener(vie);
                                ViewPager vp = vie.findViewById(R.id.viewpager);
                                LiWuAdapter ada = new LiWuAdapter(PersonHomePageActivity.this, 1, liwu.getData(), person.getData().getUid(), "");
                                vp.setAdapter(ada);
                                //移动点 // TODO: 2017/12/2
                                vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
                                        CommentUtils.toast(PersonHomePageActivity.this, "songba");
                                    }
                                });


                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll", response.body());
                    }
                });
    }

    private void initLiwuListener(View view) {


        view.findViewById(R.id.find_liwu_chongzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonHomePageActivity.this, ChongZhiActivity.class));
            }
        });
    }

    public String getHuanXinid() {
        String id = person.getData().getHx_username();
        if (TextUtils.isEmpty(id)) {
            throw new InvalidParameterException();
        }
        return id;
    }

    /**
     * 视频呼叫
     *
     * @param huanXinid
     */
    private void callVideo(String huanXinid) {
        //checkContacts();
        //han.sendEmptyMessageDelayed(0,2000);
        initmoneydata("video",huanXinid);

    }

    /**
     * 语音呼叫
     *
     * @param huanXinid
     */
    private void callVoice( String huanXinid) {
        initmoneydata("voice",huanXinid);
        //checkContacts();
    }

    private Handler han = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 0:
                    //
                    CallManager.getInstance().endCall();
                    break;
            }
        }
    };

//    private String voicemoney = person.getData().getVoice_money();
//    private String videomoney = person.getData().getVideo_money();

    private void initmoneydata(final String type, final String huanXinid) {
        OkGo.<String>post(Contants.answer_set)
                .params("token", Sputils.getString(PersonHomePageActivity.this, "token", ""))
                .params("uid", uid)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            try {
                                Gson gosn = new Gson();
                                CallBean bean = gosn.fromJson(response.body(),CallBean.class);

                                if(bean.getData().getMoney()==0){
                                   if(person.getData().getMian_type()==1){
                                       CommentUtils.toast(PersonHomePageActivity.this,"请充值");
                                       return;
                                   }
                                }
                                if(type.equals("voice")){
                                    Intent intent = new Intent(PersonHomePageActivity.this, VoiceCallActivity.class);
                                    CallManager.getInstance().setChatId(huanXinid);
                                    CallManager.getInstance().setInComingCall(false);
                                    CallManager.getInstance().setCallType(CallManager.CallType.VOICE);
                                    //startActivity(intent);
//                                    intent.putExtra("money",bean.getData().getMoney()+"");
//                                    intent.putExtra("price",voicemoney);
                                    intent.putExtra("money","2000");
                                    intent.putExtra("price",person.getData().getVoice_money());
                                    intent.putExtra("type","fa");
                                    startActivityForResult(intent,10);
                                }else if(type.equals("video")){
                                    Intent intent = new Intent(PersonHomePageActivity.this, VideoCallActivity.class);
                                    CallManager.getInstance().setChatId(huanXinid);
                                    CallManager.getInstance().setInComingCall(false);
                                    CallManager.getInstance().setCallType(CallManager.CallType.VIDEO);
                                    intent.putExtra("money","2000");
                                    intent.putExtra("price",person.getData().getVideo_money());
                                    intent.putExtra("type","fa");
                                    startActivityForResult(intent,20);

                                }


                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            //Log.i("sdfas",response.body());
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(PersonHomePageActivity.this,"主播隐身了");
                        }else if(response.body().contains("202")){
                            CommentUtils.toast(PersonHomePageActivity.this,"关闭了接听");
                        }else if(response.body().contains("288")){
                            CommentUtils.toast(PersonHomePageActivity.this,"登录失效");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        //Log.i("sdfas",response.body());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int time = data.getIntExtra("time",0);
        switch (requestCode){
            case 10:
                //Log.i("as",time+"sdfasdf");
                OkGo.<String>post(Contants.avlog)
                        .params("token", Sputils.getString(PersonHomePageActivity.this, "token", ""))
                        .params("uid", uid)
                        .params("type", "voice")
                        .params("time", time)
                        //.addUrlParams("feature",dd)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                if (response.body().contains("200")) {
                                    //CommentUtils.toast(PersonHomePageActivity.this, "设置成功");

                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                CommentUtils.toast(PersonHomePageActivity.this, "设置失败");
                            }
                        });
                break;
            case 20:
                OkGo.<String>post(Contants.avlog)
                        .params("token", Sputils.getString(PersonHomePageActivity.this, "token", ""))
                        .params("uid", uid)
                        .params("type", "video")
                        .params("time", time)
                        //.addUrlParams("feature",dd)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                if (response.body().contains("200")) {
                                    //CommentUtils.toast(PersonHomePageActivity.this, "设置成功");
                                }
                            }
                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                               // CommentUtils.toast(PersonHomePageActivity.this, "设置失败");
                            }
                        });
                break;
        }
    }
}
