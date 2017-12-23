package com.zhuye.hougong;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.bean.PersonInfoBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.fragment.FindFragment;
import com.zhuye.hougong.fragment.HomeFragment;
import com.zhuye.hougong.fragment.MeFragment;
import com.zhuye.hougong.fragment.MessageFragment;
import com.zhuye.hougong.fragment.PaiHangFragment;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.utils.Sputils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    @BindView(android.R.id.content)
    FrameLayout content;
    @BindView(R.id.home_fragmenttabhost)
    FragmentTabHost homeFragmenttabhost;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private List<String> tabnames = new ArrayList<>();
    private  List<Class> tabfragment = new ArrayList<>();
    private  List<Integer> tabimage = new ArrayList<>();

    //private String[]  tabnames = new String("");
    public Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabnames.add("首页");
        tabnames.add("排行榜");
        tabnames.add("发现");
        tabnames.add("消息");
        tabnames.add("我的");

        tabfragment.add(HomeFragment.class);
        tabfragment.add( PaiHangFragment.class);
        tabfragment.add( FindFragment.class);
        tabfragment.add( MessageFragment.class);
        tabfragment.add( MeFragment.class);


        tabimage.add(R.drawable.home_mettingpalce);
        tabimage.add(R.drawable.home_mrankinglist);
        tabimage.add(R.drawable.home_find);
        tabimage.add(R.drawable.home_message);
        tabimage.add(R.drawable.home_me);

        if (isImmersionBarEnabled())
            initImmersionBar();
        //初始化view
        initView();

        initData();
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
        initListener();

    }

    /**
     *
     */
    private void initData() {
        //获取个人头像

        //获取个人信息
        OkGo.<String>post(Contants.information)
                .params("token", Sputils.getString(MainActivity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Gson gson = new Gson();
                            PersonInfoBean personInfoBean = gson.fromJson(response.body(), PersonInfoBean.class);
                            if (response.body().contains("200")) {
                                Sputils.setString(MainActivity.this,"face",Contants.BASE_URL+personInfoBean.getData().getFace());
                                Sputils.setString(MainActivity.this,"name",personInfoBean.getData().getNickname());
                                Sputils.setString(MainActivity.this,"age",personInfoBean.getData().getAge());
                                //Sputils.setString(MainActivity.this,"uid",personInfoBean.getData().get);
//                                FutureTarget<File> fea = Glide.with(MainActivity.this).load("").downloadOnly(200,200);
//                                File file fea.get();
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

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String city = location.getCity();    //获取城市
            String city_code =  location.getCityCode();
            mLocationClient.stop();
            //请求网络数据
            Sputils.setString(MainActivity.this,"code",city_code);
            Sputils.setString(MainActivity.this,"city",city);
        }
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private void initListener() {
        homeFragmenttabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                String p = s;
                if(s.equals("首页")){
                    mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.white).init();

                }else if(s.equals("排行榜")){
                    mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.black).init();
                   // mImmersionBar.statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.white).init();
                }else if(s.equals("发现")){
                    mImmersionBar.statusBarDarkFont(true).navigationBarColor(R.color.colorPrimary).init();

                }else if(s.equals("消息")){
                    mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.white).init();

                }else if(s.equals("我的")){
                    mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.white).init();
                }
            }
        });
    }

    protected ImmersionBar mImmersionBar;
    private void initView() {
        //初始化tabhost
        initTab();
    }

    private void initTab() {


        //homeFragmenttabhost.set
        homeFragmenttabhost.setup(this,getSupportFragmentManager(),R.id.home_framelayout);
        // Typeface typeface = Typeface.createFromAsset(getAssets(),"iconfont.ttf");
        for(int i = 0; i<5;i++){
            View v= View.inflate(this,R.layout.tabview,null);
            ImageView tabiv = v.findViewById(R.id.tab_view);
            tabiv.setBackgroundResource(tabimage.get(i));
            //tabiv.setImageResource(R.mipmap.ic_launcher);
            //tabiv.setText(tabimage.get(i));
            //tabiv.setTextSize();
           // tabiv.setTypeface(typeface);
            TextView tv = v.findViewById(R.id.tab_tv);
            ImageView iv = v.findViewById(R.id.tab_view);
            tv.setText(tabnames.get(i));
            ViewGroup.LayoutParams  params=  iv.getLayoutParams();

            if(i==2){
                params.height= DensityUtil.dip2px(MainActivity.this,40f);
                params.width=  DensityUtil.dip2px(MainActivity.this,40f);
                tv.setVisibility(View.GONE);
                iv.setLayoutParams(params);
            }else {
                params.height= DensityUtil.dip2px(MainActivity.this,21f);
                params.width=  DensityUtil.dip2px(MainActivity.this,21f);
                iv.setLayoutParams(params);
            }



            TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec(tabnames.get(i)).setIndicator(v);
            homeFragmenttabhost.addTab(view, tabfragment.get(i),null);
        }

//        View v= View.inflate(this,R.layout.im,null);
//        TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec("首页").setIndicator(v);
//        homeFragmenttabhost.addTab(view, HomeFragment.class,null);
    }
}
