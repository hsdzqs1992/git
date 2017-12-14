package com.zhuye.hougong.fragment.find;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.find.FindZuiXinAdapter;
import com.zhuye.hougong.adapter.find.LiWuAdapter;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.view.LoginActivity;
import com.zhuye.hougong.view.PingLunActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class ZuiXinFragment2 extends BaseFindFragment {

    FindZuiXinAdapter adapter;
    @Override
    protected void initView() {
        super.initView();
        adapter = new FindZuiXinAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
        //初始化关注数据
        initZhuiXinData(1);
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                view.findViewById(R.id.find_zuixin_pinglun).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), PingLunActivity.class);
                        intent.putExtra("dynamic_id",dongTaiBean.getData().get(position).getDynamic_id());
                        getActivity().startActivity(intent);
                    }
                });


                final TextView tv =view.findViewById(R.id.find_zuixin_dianzan);
                final ImageView im =view.findViewById(R.id.zan);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        if(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan_id()==0){
                            //未赞
                            OkGo.<String>post(Contants.dynamic_zan)
                                    .params("token", Sputils.getString(getActivity(),"token",""))
                                    .params("id",((DongTaiBean.DataBean)adapter.getDat().get(position)).getDynamic_id())
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            if(response.body().contains("200")){
                                                tv.setText(Integer.parseInt(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan())+1+"");
                                                im.setImageResource(R.drawable.praise_on);
                                                ((DongTaiBean.DataBean)adapter.getDat().get(position)).setZan_id(1);
                                                ((DongTaiBean.DataBean)adapter.getDat().get(position)).setZan(Integer.parseInt(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan())+1+"");
                                            }
                                        }
                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);

                                        }
                                    });

                        }else if(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan_id()==1){
                            OkGo.<String>post(Contants.dynamic_qxzan)
                                    .params("token", Sputils.getString(getActivity(),"token",""))
                                    .params("id",((DongTaiBean.DataBean)adapter.getDat().get(position)).getDynamic_id())
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            if(response.body().contains("200")){
                                                tv.setText(Integer.parseInt(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan())-1+"");
                                                im.setImageResource(R.drawable.praise_of);
                                                ((DongTaiBean.DataBean)adapter.getDat().get(position)).setZan_id(0);
                                                ((DongTaiBean.DataBean)adapter.getDat().get(position)).setZan(Integer.parseInt(((DongTaiBean.DataBean)adapter.getDat().get(position)).getZan())-1+"");
                                            }
                                        }

                                        @Override
                                        public void onError(Response<String> response) {
                                            super.onError(response);

                                        }
                                    });

                        }
                        //findGuanZhuData.getData().get(position).get

                    }
                });

                view.findViewById(R.id.find_zuixin_liwu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //请求礼物数据
                        requestLiwuData(position);
                        //conn.startActivity(new Intent(conn, PingLunActivity.class));new ColorDrawable(0x00000000)

//                        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//                            @Override
//                            public boolean onTouch(View v, MotionEvent event) {
//                                if (popupWindow.isShowing()) {
//                                    popupWindow.dismiss();
//                                    return true;
//                                }
//                                return false;
//                            }
//                        });
                    }
                });
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //有bug
                state =refresh;
                initZhuiXinData(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                page++;
                state= loadmore;
                initZhuiXinData(page);
            }
        });
    }

    protected void requestLiwuData(final int pos) {
        OkGo.<String>post(Contants.giftlist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                LiWu liwu= gson.fromJson(response.body(),LiWu.class);
                                View vie = View.inflate(getActivity(),R.layout.bottom_window,null);
                                final PopupWindow popupWindow = new PopupWindow(getActivity());
                                popupWindow.setContentView(vie);
                                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                                popupWindow.setHeight(DensityUtil.dip2px(getActivity(),349f));
                                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                                popupWindow.setOutsideTouchable(true);
                                popupWindow.setFocusable(true);
                                ViewPager vp = vie.findViewById(R.id.viewpager);
                                vp.setAdapter(new LiWuAdapter(getActivity(),2,liwu.getData(),((DongTaiBean.DataBean)dongTaiBean.getData().get(pos)).getUid(),((DongTaiBean.DataBean)dongTaiBean.getData().get(pos)).getDynamic_id()));
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
                                        CommentUtils.toast(getActivity(),"songba");
                                    }
                                });




                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("208")){
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    DongTaiBean dongTaiBean;
    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public int page = 1;
    List<DongTaiBean.DataBean> datas = new ArrayList<>();


    private void initZhuiXinData(int page) {
        OkGo.<String>post(Contants.new_dynamic)
                .params("token", Sputils.getString(getActivity(), "token", ""))
                .params("type", 0)
                .params("page", page)
                .params("city_code", "201")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().contains("200")){
                            Gson gson = new Gson();
                            try {
                                dongTaiBean = gson.fromJson(response.body(),DongTaiBean.class);
                                if(adapter!=null){
                                    switch (state){
                                        case normal:
                                            if(dongTaiBean!=null&&adapter!=null){
                                                adapter.addData(dongTaiBean.getData());
                                            }
                                            break;
                                        case refresh:
                                            adapter.clear();
                                            adapter.addData(dongTaiBean.getData());
                                            recyclerView.scrollToPosition(0);
                                            materialRefreshLayout.finishRefresh();
                                            break;
                                        case loadmore:
                                            if(dongTaiBean.getData()==null){
                                                CommentUtils.toast(getActivity(),"没有更多数据");
                                                ZuiXinFragment2.this.page--;
                                            }else{
                                                adapter.addData(dongTaiBean.getData());
                                                recyclerView.scrollToPosition(adapter.getSize());

                                            }
                                            materialRefreshLayout.finishRefreshLoadMore();
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(getActivity(),"没有更多数据");
                            ZuiXinFragment2.this.page--;
                            materialRefreshLayout.finishRefreshLoadMore();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());
                    }
                });

    }
}
