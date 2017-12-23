package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hyphenate.easeui.EaseConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.home.OtherPersonAdapter;
import com.zhuye.hougong.bean.PersonDetailBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.DividerItemDecoration;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherPersonDetailActivity extends AppCompatActivity {

    @BindView(R.id.other_person_back)
    ImageView mOtherPersonBack;
    @BindView(R.id.other_person_detail_touxiang)
    RoundedCornerImageView mOtherPersonDetailTouxiang;
    @BindView(R.id.other_person_detail_name)
    TextView mOtherPersonDetailName;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.other_person_detail_xiangce)
    TextView mOtherPersonDetailXiangce;
    @BindView(R.id.other_person_detail_xiangcego)
    TextView mOtherPersonDetailXiangcego;
    @BindView(R.id.other_person_detail_recycleview)
    RecyclerView mOtherPersonDetailRecycleview;
    @BindView(R.id.other_person_detail_ziliao)
    TextView mOtherPersonDetailZiliao;
    @BindView(R.id.other_person_detail_age)
    TextView mOtherPersonDetailAge;
    @BindView(R.id.other_person_detail_xingzuo)
    TextView mOtherPersonDetailXingzuo;
    @BindView(R.id.other_person_detail_sex)
    TextView mOtherPersonDetailSex;
    @BindView(R.id.other_person_detail_city)
    TextView mOtherPersonDetailCity;
    @BindView(R.id.other_person_detail_shifouguan)
    Button mOtherPersonDetailShifouguan;
    @BindView(R.id.person_jiahei)
    TextView mPersonJiahei;
    String p;
    @BindView(R.id.switch1)
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_detail);
        ButterKnife.bind(this);
        p = getIntent().getStringExtra("personinfo");
        huid = getIntent().getStringExtra("hxid");
        initData();
        initListener();
    }

    private void initListener() {
        //switch1.on
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                   //打开 加入黑名单
                    mPersonJiahei.setText("已加黑");
                }else {
                    mPersonJiahei.setText("加黑");
                }
                jiaheimingdan();
            }
        });
    }

    PersonDetailBean personDetailBean;

    private void initData() {
        OkGo.<String>post(Contants.personcenter)
                .params("uid", p)
                .params("token", Sputils.getString(OtherPersonDetailActivity.this, "token", ""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this, "成功");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this, "成功");
                        Gson gson = new Gson();
                        personDetailBean = gson.fromJson(response.body(), PersonDetailBean.class);
                        Glide.with(OtherPersonDetailActivity.this).load(Contants.BASE_URL + personDetailBean.getData().getFace()).into(mOtherPersonDetailTouxiang);

                        mOtherPersonDetailName.setText(personDetailBean.getData().getNickname());
                        mTvFollow.setText(personDetailBean.getData().getLove() + "\n关注");
                        mTvFans.setText(personDetailBean.getData().getFans() + "\n粉丝");
                        mOtherPersonDetailName.setText(personDetailBean.getData().getNickname());
                        mOtherPersonDetailAge.setText(personDetailBean.getData().getAge());
                        mOtherPersonDetailXingzuo.setText(personDetailBean.getData().getCon());
                        mOtherPersonDetailXingzuo.setText(personDetailBean.getData().getCon());
                        if (personDetailBean.getData().getSex().equals("1")) {
                            mOtherPersonDetailSex.setText("女");
                        } else {
                            mOtherPersonDetailSex.setText("男");
                        }
                        mOtherPersonDetailCity.setText(personDetailBean.getData().getCity());

                        if (personDetailBean.getData().getImg().size() > 0) {
                            List data = new ArrayList();

                            for (int i = 0; i < personDetailBean.getData().getImg().size(); i++) {
                                data.add(Contants.BASE_URL + personDetailBean.getData().getImg().get(i));
                            }
                            OtherPersonAdapter oadapter = new OtherPersonAdapter(OtherPersonDetailActivity.this, data);
                            mOtherPersonDetailRecycleview.setAdapter(oadapter);
                            mOtherPersonDetailRecycleview.setLayoutManager(new LinearLayoutManager(OtherPersonDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));


                            DividerItemDecoration divider = new DividerItemDecoration(OtherPersonDetailActivity.this, LinearLayoutManager.HORIZONTAL);
                            mOtherPersonDetailRecycleview.addItemDecoration(divider);
                        }

                        if (personDetailBean.getData().getBlack() == 1) {
                            mPersonJiahei.setText("已加黑");
                        } else if (personDetailBean.getData().getBlack() == 0) {
                            mPersonJiahei.setText("加黑");
                        }

//                        if (personDetailBean.getData().getGuanzhu() == 1) {
//                            mOtherPersonDetailShifouguan.setText("已关注");
//                        } else if (personDetailBean.getData().getGuanzhu() == 0) {
//                            mOtherPersonDetailShifouguan.setText("未关注");
//                        }

                    }
                });

    }

    @OnClick({R.id.other_person_back, R.id.person_jiahei, R.id.other_person_detail_touxiang, R.id.other_person_detail_name, R.id.tv_follow, R.id.tv_fans, R.id.other_person_detail_xiangce, R.id.other_person_detail_xiangcego, R.id.other_person_detail_recycleview, R.id.other_person_detail_ziliao, R.id.other_person_detail_age, R.id.other_person_detail_xingzuo, R.id.other_person_detail_sex, R.id.other_person_detail_city, R.id.other_person_detail_shifouguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.other_person_back:
                finish();
                break;
            case R.id.other_person_detail_touxiang:
                break;
            case R.id.other_person_detail_name:
                break;
            case R.id.tv_follow:
                break;
            case R.id.tv_fans:
                break;

            case R.id.other_person_detail_xiangce:
                break;
            case R.id.other_person_detail_xiangcego:
                break;
            case R.id.other_person_detail_recycleview:
                break;
            case R.id.other_person_detail_ziliao:
                break;
            case R.id.other_person_detail_age:
                break;
            case R.id.other_person_detail_xingzuo:
                break;
            case R.id.other_person_detail_sex:
                break;
            case R.id.other_person_detail_city:
                break;
            case R.id.other_person_detail_shifouguan:
                //jiaGaunzhu();
                lianTian();
                break;
            case R.id.person_jiahei:

                break;
        }
    }

    String huid;
    private void lianTian() {
        Intent in = new Intent(OtherPersonDetailActivity.this, ChatAvtiviyt.class);
        in.putExtra(EaseConstant.EXTRA_USER_ID, huid);
        startActivity(in);

    }

    private void jiaGaunzhu() {
        if (personDetailBean.getData().getGuanzhu() == 1) {
            OkGo.<String>post(Contants.del_mylove)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this, "token", ""))
                    .params("uid", p)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this, "shibaile");
                                 }

                                 @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this, "chenggongle");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     mOtherPersonDetailShifouguan.setText("加关注");
                                     personDetailBean.getData().setGuanzhu(0);
                                 }
                             }
                    );


        } else {

            OkGo.<String>post(Contants.love)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this, "token", ""))
                    .params("uid", p)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this, "shibaile");
                                 }

                                 @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this, "chenggongle");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     mOtherPersonDetailShifouguan.setText("已关注");
                                     personDetailBean.getData().setGuanzhu(1);
                                 }
                             }
                    );

        }

    }

    private void jiaheimingdan() {
        if (personDetailBean.getData().getBlack() == 1) {
            OkGo.<String>post(Contants.del_black)
                    .params("uid", p)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this, "token", ""))
                    .execute(new MyCallback() {
                        @Override
                        protected void doFailue(Response<String> response) {
                            CommentUtils.toast(OtherPersonDetailActivity.this, "网络出错");
                        }

                        @Override
                        protected void excuess(Response<String> response) {
                            // CommentUtils.toast(OtherPersonDetailActivity.this,"移除黑名单");
                            mPersonJiahei.setText("加入黑名单");
                            personDetailBean.getData().setBlack(0);
                        }
                    });
        } else if (personDetailBean.getData().getBlack() == 0) {
            OkGo.<String>post(Contants.black)
                    .params("uid", p)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this, "token", ""))
                    .execute(new MyCallback() {
                        @Override
                        protected void doFailue(Response<String> response) {
                            CommentUtils.toast(OtherPersonDetailActivity.this, "网络出错");
                        }

                        @Override
                        protected void excuess(Response<String> response) {
                            mPersonJiahei.setText("移除黑名单");
                            personDetailBean.getData().setBlack(1);
                        }
                    });

        }

    }
}
