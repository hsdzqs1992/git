package com.zhuye.hougong.fragment.paihang;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.paihang.PaiHangAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.PaiHangBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.PersonHomePageActivity;
import com.zhuye.hougong.weidgt.MyLineLayoutManager;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;
import com.zhuye.hougong.weidgt.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public abstract class BasePaiHangFragment extends BaseFragment {


    @BindView(R.id.paihang_bangdan_ri)
    TextView paihangBangdanRi;
    @BindView(R.id.paihang_touxiang)
    ImageView paihangTouxiang;
    @BindView(R.id.paihang_xiaotouxiang)
    ImageView paihangXiaotouxiang;
    @BindView(R.id.paihang_name)
    TextView paihangName;
    @BindView(R.id.paihang_money)
    TextView paihangMoney;
    @BindView(R.id.paihang_two)
    LinearLayout paihangTwo;
    @BindView(R.id.paihang_bangdan_zhou)
    TextView paihangBangdanZhou;
    @BindView(R.id.paihang_touxiang_1)
    RoundedCornerImageView paihangTouxiang1;
    @BindView(R.id.paihang_xiaotouxiang_1)
    ImageView paihangXiaotouxiang1;
    @BindView(R.id.paihang_name1)
    TextView paihangName1;
    @BindView(R.id.find_zuixin_age_1)
    TextView findZuixinAge1;
    @BindView(R.id.paihang_money_1)
    TextView paihangMoney1;
    @BindView(R.id.paihang_one)
    LinearLayout paihangOne;
    @BindView(R.id.paihang_bangdan_3)
    TextView paihangBangdan3;
    @BindView(R.id.paihang_touxiang_3)
    ImageView paihangTouxiang3;
    @BindView(R.id.paihang_xiaotouxiang_3)
    ImageView paihangXiaotouxiang3;
    @BindView(R.id.paihang_name_3)
    TextView paihangName3;
    @BindView(R.id.find_zuixin_age_3)
    TextView findZuixinAge3;
    @BindView(R.id.paihang_money_3)
    TextView paihangMoney3;
    @BindView(R.id.paihang_three)
    LinearLayout paihangThree;
    @BindView(R.id.paihang_bangdan_shengyu)
    TextView paihangBangdanShengyu;


    @BindView(R.id.find_zuixin_age)
    TextView findZuixinAge;

    @BindView(R.id.paihangqi)
    WrapRecyclerView paihangqi;

    PaiHangAdapter ada;
    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;
    protected LinearLayout header;

    @Override
    protected void initView() {
        ada = new PaiHangAdapter(getActivity());

        paihangqi.setAdapter(ada);
        paihangqi.setLayoutManager(new MyLineLayoutManager(getActivity()));
        materialRefreshLayout.setLoadMore(true);
        header = rootView.findViewById(R.id.header);
    }

    private int kinds = 0;

    @Override
    protected int getResId() {
        return R.layout.paihang_vp_item;
    }

    @OnClick({R.id.paihang_touxiang_3,R.id.paihang_touxiang,R.id.paihang_touxiang_1,R.id.paihang_bangdan_ri, R.id.paihang_bangdan_zhou, R.id.paihang_bangdan_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.paihang_touxiang_3:
                int a =0;
                enterHome(bean.getData().get(2).getUid(),bean.getData().get(2).getLove()+"");
                break;
            case R.id.paihang_touxiang:

                int o =0;
                enterHome(bean.getData().get(1).getUid(),bean.getData().get(1).getLove()+"");
                break;
            case R.id.paihang_touxiang_1:

                int k =0;
                enterHome(bean.getData().get(0).getUid(),bean.getData().get(0).getLove()+"");
                break;
            case R.id.paihang_bangdan_ri:
                CommentUtils.toast(getActivity(), "ri");
                kinds = 0;
                changeSexBackGround();
                onClickRi();
                break;
            case R.id.paihang_bangdan_zhou:
                CommentUtils.toast(getActivity(), "1");
                kinds = 1;
                changeSexBackGround();
                onClickZhou();
                break;
            case R.id.paihang_bangdan_3:
                CommentUtils.toast(getActivity(), "3");
                kinds = 2;
                changeSexBackGround();
                onClickMonth();
                break;

        }
    }

    private void enterHome(String id,String love) {
        Intent in = new Intent(getActivity(),PersonHomePageActivity.class);
        in.putExtra("uid",id);
        in.putExtra("guanzhu",love);//bug
        getActivity().startActivity(in);
    }
//
//    public void emterHomePage(int ji) {
//        Intent intent = new Intent(getActivity(), PersonHomePageActivity.class);
//        intent.putExtra("uid", bean.getData().get(ji).getUid());
//        getActivity().startActivity(intent);
//    }

    protected abstract void onClickMonth();

    protected abstract void onClickZhou();

    protected abstract void onClickRi();


    private void changeSexBackGround() {
        switch (kinds) {
            case 0:
                setTvBackGround(paihangBangdanRi, R.drawable.bangdan_tv_shape, getActivity().getResources().getColor(R.color.colorAccent));
                setTvBackGround(paihangBangdanZhou, Color.WHITE);
                setTvBackGround(paihangBangdan3, Color.WHITE);
                break;
            case 1:
                setTvBackGround(paihangBangdanRi, Color.WHITE);
                setTvBackGround(paihangBangdanZhou, R.drawable.bangdan_tv_shape, getActivity().getResources().getColor(R.color.colorAccent));
                setTvBackGround(paihangBangdan3, Color.WHITE);
                //homeSexBuxian.setBackground(getResources().getDrawable(R.drawable.tv_slide_bg));
                //homeSexNv.setBackgroundResource(R.drawable.tv_slide_bg);
                break;
            case 2:
                setTvBackGround(paihangBangdanRi, Color.WHITE);
                setTvBackGround(paihangBangdanZhou, Color.WHITE);
                setTvBackGround(paihangBangdan3, R.drawable.bangdan_tv_shape, getActivity().getResources().getColor(R.color.colorAccent));
                break;
        }

    }

    private void setTvBackGround(TextView view, int color) {
        view.setTextColor(color);
        view.setBackgroundResource(R.color.touming);
    }

    private void setTvBackGround(TextView view, int resId, int color) {
        view.setTextColor(color);
        view.setBackgroundResource(resId);

    }

    protected PaiHangBean bean;

    protected void getBangData(int page, int type, int day) {
        OkGo.<String>post(Contants.billboard)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("page", page)
                .params("type", type)
                .params("day", day)
                .execute(new MyCallback() {
                    @Override
                    protected void doFailue(Response<String> response) {
                        if(response.body().contains("201")){
                            CommentUtils.toast(getActivity(),"没有更多数据");
                            BasePaiHangFragment.this.page--;
                            materialRefreshLayout.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    protected void excuess(Response<String> response) {
                        try {
                            Gson gosn = new Gson();
                            bean = gosn.fromJson(response.body(), PaiHangBean.class);
                            if(!isrefresh){
                                handleData();
                            }else{
                                switch (state){
                                    case refresh:
                                           handleData();
//                                        ada.clear();
//                                        converData();
//                                        ada.addData(newdata);
//                                        paihangqi.scrollToPosition(0);
//                                        materialRefreshLayout.finishRefresh();
                                        break;
                                    case loadmore:
                                        if(bean.getData()==null){
                                            CommentUtils.toast(getActivity(),"没有更多数据");
                                            BasePaiHangFragment.this.page--;
                                        }else{
                                            ada.addData(bean.getData());
                                            paihangqi.scrollToPosition(ada.getSize());

                                        }
                                        materialRefreshLayout.finishRefreshLoadMore();
                                        break;
                                }
                            }

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    protected void handleData() {
        if (bean.getData().size() == 1) {
            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
        } else if (bean.getData().size() == 2) {
            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
            handletoudata(paihangName,findZuixinAge,paihangMoney,paihangTouxiang,1);
        } else if (bean.getData().size() >= 3) {
            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
            handletoudata(paihangName,findZuixinAge,paihangMoney,paihangTouxiang,1);
            handletoudata(paihangName3,findZuixinAge3,paihangMoney3,paihangTouxiang3,2);
            converData();
            //bug
            ada.clear();
            ada.addData(newdata);
            if(isrefresh){
                materialRefreshLayout.finishRefresh();
            }
        }

    }

    private void handletoudata(TextView name , TextView age , TextView money , ImageView face , int po) {
        //设置数据  需要处理 // TODO: 2017/12/6 0006   uid
        Glide.with(getActivity()).load(Contants.BASE_URL + bean.getData().get(po).getFace()).into(face);
        name.setText(bean.getData().get(po).getNickname());
        age.setText(bean.getData().get(po).getAge());
        if (bean.getData().get(po).getSex().equals("1")) {
            Drawable drawable = getResources().getDrawable(R.drawable.miss);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            age.setCompoundDrawables(drawable, null, null, null);
        } else if (bean.getData().get(po).getSex().equals("0")) {
            Drawable drawable = getResources().getDrawable(R.drawable.boy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            age.setCompoundDrawables(drawable, null, null, null);
        }
        money.setText(bean.getData().get(po).getCount());
    }

    protected List<PaiHangBean.DataBean> newdata = new ArrayList();


    protected void converData() {

        newdata.clear();
        for (int i = 3; i < bean.getData().size(); i++) {
            newdata.add(bean.getData().get(i));
        }
    }

    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public static int page = 1;


    protected int type1 = 3;
    protected  int day = 1;

    private Boolean isrefresh = false;


    @Override
    protected void initListener() {
        super.initListener();

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                state = refresh;
                page = 1;
                isrefresh = true;
                getBangData(page,type1,day);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                state = loadmore;
                page++;
                isrefresh =true;
                getBangData(page,type1,day);

            }
        });

        ada.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                enterHome(newdata.get(position).getUid(),newdata.get(position).getLove());
            }
        });
    }
}
