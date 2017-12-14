package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
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
    @BindView(R.id.tv_friends)
    TextView mTvFriends;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_detail);
        ButterKnife.bind(this);
        p= getIntent().getStringExtra("personinfo");
        initData();
    }

    PersonDetailBean personDetailBean;
    private void initData() {
        OkGo.<String>post(Contants.personcenter)
                .params("uid",p)
                .params("token", Sputils.getString(OtherPersonDetailActivity.this,"token",""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");
                        Gson gson = new Gson();
                        personDetailBean = gson.fromJson(response.body(),PersonDetailBean.class);
                        Glide.with(OtherPersonDetailActivity.this).load(Contants.BASE_URL+personDetailBean.getData().getFace()).into(mOtherPersonDetailTouxiang);

                        mOtherPersonDetailName.setText(personDetailBean.getData().getNickname());
                        mTvFollow.setText(personDetailBean.getData().getLove()+"\n关注");
                        mTvFans.setText(personDetailBean.getData().getFans()+"\n粉丝");
                        mOtherPersonDetailName.setText(personDetailBean.getData().getNickname());
                        mOtherPersonDetailAge.setText(personDetailBean.getData().getAge());
                        mOtherPersonDetailXingzuo.setText(personDetailBean.getData().getCon());
                        mOtherPersonDetailXingzuo.setText(personDetailBean.getData().getCon());
                        if(personDetailBean.getData().getSex().equals("1")){
                            mOtherPersonDetailSex.setText("女");
                        }else{
                            mOtherPersonDetailSex.setText("男");
                        }
                        mOtherPersonDetailCity.setText(personDetailBean.getData().getCity());

                        if(personDetailBean.getData().getImg().size()>0){
                            List data = new ArrayList();

                            for (int i = 0 ;i<personDetailBean.getData().getImg().size();i++){
                                data.add(Contants.BASE_URL+personDetailBean.getData().getImg().get(i));
                            }
                            OtherPersonAdapter oadapter = new OtherPersonAdapter(OtherPersonDetailActivity.this,data);
                            mOtherPersonDetailRecycleview.setAdapter(oadapter);
                            mOtherPersonDetailRecycleview.setLayoutManager(new LinearLayoutManager(OtherPersonDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));


                            DividerItemDecoration divider = new DividerItemDecoration(OtherPersonDetailActivity.this,LinearLayoutManager.HORIZONTAL);
                            mOtherPersonDetailRecycleview.addItemDecoration(divider);
                        }
                    }
                });

    }

    @OnClick({R.id.other_person_back,R.id.person_jiahei, R.id.other_person_detail_touxiang, R.id.other_person_detail_name, R.id.tv_follow, R.id.tv_fans, R.id.tv_friends, R.id.other_person_detail_xiangce, R.id.other_person_detail_xiangcego, R.id.other_person_detail_recycleview, R.id.other_person_detail_ziliao, R.id.other_person_detail_age, R.id.other_person_detail_xingzuo, R.id.other_person_detail_sex, R.id.other_person_detail_city, R.id.other_person_detail_shifouguan})
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
            case R.id.tv_friends:
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
                jiaGaunzhu();
                break;
            case R.id.person_jiahei:
                
                jiaheimingdan();
                break;
        }
    }

    private void jiaGaunzhu() {
        if(personDetailBean.getData().getGuanzhu()==1){
            OkGo.<String>post(Contants.del_mylove)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this,"token",""))
                    .params("uid",p)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this,"shibaile");
                                 }

                            @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this,"chenggongle");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     mOtherPersonDetailShifouguan.setText("加关注");
                                 }
                             }
                    );


        }else{

            OkGo.<String>post(Contants.love)
                    .params("token", Sputils.getString(OtherPersonDetailActivity.this,"token",""))
                    .params("uid",p)
                    .execute(new MyCallback() {
                                 @Override
                                 protected void doFailue(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this,"shibaile");
                                 }

                                 @Override
                                 protected void excuess(Response<String> response) {
                                     CommentUtils.toast(OtherPersonDetailActivity.this,"chenggongle");
                                     //((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                     mOtherPersonDetailShifouguan.setText("已关注");
                                 }
                             }
                    );

        }

    }

    private void jiaheimingdan() {
        OkGo.<String>post(Contants.black)
                .params("uid",p)
                .params("token", Sputils.getString(OtherPersonDetailActivity.this,"token",""))
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        CommentUtils.toast(OtherPersonDetailActivity.this,"成功");

                    }
                });

    }
}
