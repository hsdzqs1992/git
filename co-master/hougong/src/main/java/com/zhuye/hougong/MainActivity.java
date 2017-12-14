package com.zhuye.hougong;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gyf.barlibrary.ImmersionBar;
import com.zhuye.hougong.fragment.FindFragment;
import com.zhuye.hougong.fragment.HomeFragment;
import com.zhuye.hougong.fragment.MeFragment;
import com.zhuye.hougong.fragment.MessageFragment;
import com.zhuye.hougong.fragment.PaiHangFragment;
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

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            //String city = location.getCity();    //获取城市
            String city_code =  location.getCityCode();
            mLocationClient.stop();
            //请求网络数据
            Sputils.setString(MainActivity.this,"code",city_code);

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
            tv.setText(tabnames.get(i));
            if(i==2){
                tv.setVisibility(View.GONE);
            }



            TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec(tabnames.get(i)).setIndicator(v);
            homeFragmenttabhost.addTab(view, tabfragment.get(i),null);
        }

//        View v= View.inflate(this,R.layout.im,null);
//        TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec("首页").setIndicator(v);
//        homeFragmenttabhost.addTab(view, HomeFragment.class,null);
    }
}
