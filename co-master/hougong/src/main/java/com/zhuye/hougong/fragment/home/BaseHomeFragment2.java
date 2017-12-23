package com.zhuye.hougong.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.home.HomeBaseAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.bean.HomeRecycleBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.PersonHomePageActivity;
import com.zhuye.hougong.weidgt.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

/**
 * Created by zzzy on 2017/11/23.
 */

public abstract class BaseHomeFragment2 extends BaseFragment {


    protected SliderLayout sliderShow;
    protected MaterialRefreshLayout materialRefreshLayout;

    protected WrapRecyclerView recyclerView;
    protected HomeBaseAdapter homeTuiJianAdapter;

    protected HomeBanner homeBanner;
    View slideview;
    @Override
    protected void initView() {
       // sliderShow= (SliderLayout) rootView.findViewById(R.id.slider);
        materialRefreshLayout = rootView.findViewById(R.id.fragment_home_materrial);
        recyclerView = rootView.findViewById(R.id.fragment_home_recycle);
        materialRefreshLayout.setLoadMore(true);
        //slideview = View.inflate(getActivity(),R.layout.slider,null);
        slideview = LayoutInflater.from(getActivity()).inflate(R.layout.slider,null);
        sliderShow= (SliderLayout) slideview.findViewById(R.id.slider);

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_home_vp2;
    }

    public void setData(HomeBanner homeBanner){
        this.homeBanner = homeBanner;
        if(homeBanner!=null){
            List<String> url = new ArrayList();
            url.add(Contants.BASE_URL+homeBanner.getData().get(0).getImg());
            url.add(Contants.BASE_URL+homeBanner.getData().get(1).getImg());
            url.add(Contants.BASE_URL+homeBanner.getData().get(2).getImg());

            for (int i = 0 ; i<url.size();i++){
                DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
                defaultSliderView.image(url.get(i));
                if(sliderShow!=null)
                    sliderShow.addSlider(defaultSliderView);
            }
        }
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Contants.lunbo)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        HomeBanner homeBanner = gson.fromJson(response.body(), HomeBanner.class);
                        Log.d("------", homeBanner.toString());
                        setData(homeBanner);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        //Log.d("------",response.body());
                    }
                });

    }
    protected HomeRecycleBean dongTaiBean;

    public void getReData(int type,int page){
        OkGo.<String>post(Contants.hall)
                .tag(getActivity())
                .params("type",type)
                .params("page",page)
                .params("token", Sputils.getString(getActivity(),"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        // TODO: 2017/11/22  数据有问题
                        if(response.body().contains("200")){
                            try {
                                Gson gson = new Gson();
                                dongTaiBean = gson.fromJson(response.body(), HomeRecycleBean.class);
                                if(homeTuiJianAdapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&homeTuiJianAdapter!=null){
                                                homeTuiJianAdapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            homeTuiJianAdapter.clear();
                                            homeTuiJianAdapter.addData(dongTaiBean.getData());
                                            recyclerView.scrollToPosition(0);
                                            materialRefreshLayout.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null){
                                                CommentUtils.toast(getActivity(),"没有更多数据");
                                                BaseHomeFragment2.this.page--;
                                            }else{
                                                homeTuiJianAdapter.addData(dongTaiBean.getData());
                                                recyclerView.scrollToPosition(homeTuiJianAdapter.getSize());

                                            }
                                            materialRefreshLayout.finishRefreshLoadMore();
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("201")){
                            //if(materialRefreshLayout.finishRefreshing())
                            CommentUtils.toast(getActivity(),"没有更多数据");
                            BaseHomeFragment2.this.page--;
                            materialRefreshLayout.finishRefreshLoadMore();
                            materialRefreshLayout.finishRefresh();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                      //  Log.d("------",response.body());
                    }
                });

    }


    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public static int page = 1;
    


    protected abstract void loadmoreownData(int type, int page);

    protected abstract void refreshownData(int type, int i) ;

    @Override
    protected void initListener() {
        super.initListener();

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                state = refresh;
                refreshownData(type,1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                state = loadmore;
                page++;
                loadmoreownData(type,page);
            }
        });
        homeTuiJianAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                //进入
                view.findViewById(R.id.home_tuijian_item_touxiang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), PersonHomePageActivity.class);
                        intent.putExtra("uid",((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).getUid());
                        intent.putExtra("guanzhu",((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).getLove()+"");
                        getActivity().startActivity(intent);
                    }
                });



                view.findViewById(R.id.home_tuijian_item_zhuboguanzhu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        int love = ((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).getLove();
                        if(love == 0){
                            //未关注
                            // isGuanzhu = false;
                            OkGo.<String>post(Contants.love)
                                    .params("token", Sputils.getString(getActivity(),"token",""))
                                    .params("uid",((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).getUid())
                                    .execute(new MyCallback() {
                                                 @Override
                                                 protected void doFailue(Response<String> response) {
                                                     CommentUtils.toast(getActivity(),"关注失败");
                                                 }

                                                 @Override
                                                 protected void excuess(Response<String> response) {
                                                     CommentUtils.toast(getActivity(),"关注成功");
                                                     ((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_on);
                                                     ((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).setLove(1);
                                                 }
                                             }
                                    );
                        }else if(love ==1){
                            //已关注
                            OkGo.<String>post(Contants.del_mylove)
                                    .params("token", Sputils.getString(getActivity(),"token",""))
                                    .params("uid",((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).getUid())
                                    .execute(new MyCallback() {
                                                 @Override
                                                 protected void doFailue(Response<String> response) {
                                                     CommentUtils.toast(getActivity(),"取消关注失败");
                                                 }

                                                 @Override
                                                 protected void excuess(Response<String> response) {
                                                     CommentUtils.toast(getActivity(),"取消关注");
                                                     ((ImageView)view.findViewById(R.id.home_tuijian_item_zhuboguanzhu)).setImageResource(R.drawable.attention_off);
                                                     ((HomeRecycleBean.DataBean) dongTaiBean.getData().get(position-1)).setLove(0);
                                                 }
                                             }
                                    );
                            // isGuanzhu = true;
                        }
                    }
                });
            }
        });
    }
}
