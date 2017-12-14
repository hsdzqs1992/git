package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mikepenz.materialdrawer.Drawer;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.HomePagerAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeRecycleBean;
import com.zhuye.hougong.city.ChooseAddressActivity;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.SearchActivity;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_sex_buxian)
    TextView homeSexBuxian;
    @BindView(R.id.home_sex_nv)
    TextView homeSexNv;
    @BindView(R.id.home_sex_nan)
    TextView homeSexNan;
    @BindView(R.id.home_tongcheng_buxian)
    TextView homeTongchengBuxian;
    @BindView(R.id.home_tongcheng_tongcheng)
    TextView homeTongchengTongcheng;
    @BindView(R.id.home_tongcheng_qite)
    TextView homeTongchengQite;
    @BindView(R.id.home_tonghua_buxian)
    TextView homeTonghuaBuxian;
    @BindView(R.id.home_tonghua_shipin)
    TextView homeTonghuaShipin;
    @BindView(R.id.home_tonghua_yuyin)
    TextView homeTonghuaYuyin;
    @BindView(R.id.home_queren)
    Button homeQueren;
    Unbinder unbinder;
    Unbinder unbinder1;
    @BindView(R.id.city)
    TextView city;
    Unbinder unbinder2;
    //private MyToolbar myToolbar;
    private ViewPager mviewpager;
    private PagerSlidingTabStrip mTabStrip;
    HomePagerAdapter homePagerAdapter;
    ImageView search;
    Drawer drawer;
    DrawerLayout mDrawerLayout;

    ImageView message;
    private int selectsex = 0;
    private int selecttongcheng = 0;
    private int selecttonghua = 0;
    private static final int sexbuxian = 0;
    private static final int sexne = 1;
    private static final int sexnan = 2;

    @Override
    protected void initView() {

        //drawer = new DrawerBuilder().withActivity(getActivity()).build();

        //drawer.setFullscreen(false);
        //Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"iconfont.ttf");
        mviewpager = rootView.findViewById(R.id.home_viewpager);
        mTabStrip = rootView.findViewById(R.id.tab_strip);
        search = rootView.findViewById(R.id.search);
        message = rootView.findViewById(R.id.message);
        mDrawerLayout = rootView.findViewById(R.id.home_drawer);

        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h8));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);

        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        mviewpager.setAdapter(homePagerAdapter);
        mviewpager.setOffscreenPageLimit(0);

        mTabStrip.setViewPager(mviewpager);
        cc =Sputils.getString(getActivity(),"city","郑州市");
        city.setText(cc);
    }
    String cc;
    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();
//        OkGo.<String>post(Contants.lunbo)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//
//                        Gson gson = new Gson();
//                        HomeBanner homeBanner = gson.fromJson(response.body(), HomeBanner.class);
//                        Log.d("------", homeBanner.toString());
//                        // TODO: 2017/11/27 第一次进入是没显示
//                        if (homePagerAdapter != null) {
////                            homePagerAdapter.setData(homeBanner);
////                            homePagerAdapter.initData();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        //Log.d("------",response.body());
//                    }
//                });

       /// String token = Sputils.getString(getActivity(), "token", "");
      // initHomeData(token, 1);
//        initHomeData(token, 2);
//        initHomeData(token, 3);
//        initHomeData(token, 4);


    }



    private void initHomeData(String token, final Integer type) {
        OkGo.<String>post(Contants.hall)
                .tag(getActivity())
                .params("type", type)
                .params("page", 1)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        // TODO: 2017/11/22  数据有问题
                        try {
                            Gson gson = new Gson();
                            HomeRecycleBean homeRecycleBean = gson.fromJson(response.body(), HomeRecycleBean.class);
                            //Log.d("------",homeRecycleBean.toString());
                            //homePagerAdapter.setData(homeBanner);
                            switch (type) {
                                case 1:
                                    //homePagerAdapter.sethomeTuijianData(homeRecycleBean);
                                    break;
                                case 2:
                                    //homePagerAdapter.sethomeHuoYueData(homeRecycleBean);
                                    break;
                                case 3:
                                    //homePagerAdapter.sethomeXinRenData(homeRecycleBean);
                                    break;
                                case 4:
                                   // homePagerAdapter.sethomeMianFeiData(homeRecycleBean);
                                    break;
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        //// TODO: 2017/12/8 0008
                        // Log.d("------",response.body());
                    }
                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                //drawer.openDrawer();
                //mDrawerLayout.openDrawer(GravityCompat.END);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }


    @OnClick({R.id.home_sex_buxian, R.id.home_sex_nv, R.id.home_sex_nan, R.id.home_tongcheng_buxian, R.id.home_tongcheng_tongcheng, R.id.home_tongcheng_qite, R.id.home_tonghua_buxian, R.id.home_tonghua_shipin, R.id.home_tonghua_yuyin, R.id.home_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_sex_buxian:
                selectsex = 0;
                changeSexBackGround();
                break;
            case R.id.home_sex_nv:
                selectsex = 1;
                changeSexBackGround();
                break;
            case R.id.home_sex_nan:
                selectsex = 2;
                changeSexBackGround();
                break;
            case R.id.home_tongcheng_buxian:
                selecttongcheng = 0;
                changeTongChengGround();
                break;
            case R.id.home_tongcheng_tongcheng:
                selecttongcheng = 1;
                changeTongChengGround();
                break;
            case R.id.home_tongcheng_qite:
                selecttongcheng = 2;
                changeTongChengGround();
                //选择城市
                Intent in = new Intent(getActivity(), ChooseAddressActivity.class);
                in.putExtra("now",cc);
                startActivityForResult(in,10);
                break;
            case R.id.home_tonghua_buxian:
                selecttonghua = 0;
                changeTongHuaGround();
                break;
            case R.id.home_tonghua_shipin:
                selecttonghua = 1;
                changeTongHuaGround();
                break;
            case R.id.home_tonghua_yuyin:
                selecttonghua = 2;
                changeTongHuaGround();
                break;
            case R.id.home_queren:
                mDrawerLayout.closeDrawer(GravityCompat.END);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cit = data.getStringExtra("city");
        String code = data.getStringExtra("citycode");

        city.setText(cit);
    }

    private void changeTongHuaGround() {
        switch (selecttonghua) {
            case sexbuxian:
                setTvBackGround(homeTonghuaBuxian, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeTonghuaShipin, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTonghuaYuyin, R.drawable.tv_slidenor_bg, Color.BLACK);
                break;
            case sexne:
                setTvBackGround(homeTonghuaBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTonghuaShipin, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeTonghuaYuyin, R.drawable.tv_slidenor_bg, Color.BLACK);
                //homeSexBuxian.setBackground(getResources().getDrawable(R.drawable.tv_slide_bg));
                //homeSexNv.setBackgroundResource(R.drawable.tv_slide_bg);
                break;
            case sexnan:
                setTvBackGround(homeTonghuaBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTonghuaShipin, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTonghuaYuyin, R.drawable.tv_slide_bg, Color.RED);
                break;
        }
    }

    private void changeTongChengGround() {
        switch (selecttongcheng) {
            case sexbuxian:
                setTvBackGround(homeTongchengBuxian, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeTongchengTongcheng, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTongchengQite, R.drawable.tv_slidenor_bg, Color.BLACK);
                break;
            case sexne:
                setTvBackGround(homeTongchengBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTongchengTongcheng, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeTongchengQite, R.drawable.tv_slidenor_bg, Color.BLACK);
                //homeSexBuxian.setBackground(getResources().getDrawable(R.drawable.tv_slide_bg));
                //homeSexNv.setBackgroundResource(R.drawable.tv_slide_bg);
                break;
            case sexnan:
                setTvBackGround(homeTongchengBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTongchengTongcheng, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeTongchengQite, R.drawable.tv_slide_bg, Color.RED);
                break;
        }
    }

    private void changeSexBackGround() {
        switch (selectsex) {
            case sexbuxian:
                setTvBackGround(homeSexBuxian, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeSexNv, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeSexNan, R.drawable.tv_slidenor_bg, Color.BLACK);
                break;
            case sexne:
                setTvBackGround(homeSexBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeSexNv, R.drawable.tv_slide_bg, Color.RED);
                setTvBackGround(homeSexNan, R.drawable.tv_slidenor_bg, Color.BLACK);
                //homeSexBuxian.setBackground(getResources().getDrawable(R.drawable.tv_slide_bg));
                //homeSexNv.setBackgroundResource(R.drawable.tv_slide_bg);
                break;
            case sexnan:
                setTvBackGround(homeSexBuxian, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeSexNv, R.drawable.tv_slidenor_bg, Color.BLACK);
                setTvBackGround(homeSexNan, R.drawable.tv_slide_bg, Color.RED);
                break;
        }

    }


    private void setTvBackGround(TextView view, int resId, int color) {
        view.setTextColor(color);
        view.setBackgroundResource(resId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder2.unbind();
    }
}
