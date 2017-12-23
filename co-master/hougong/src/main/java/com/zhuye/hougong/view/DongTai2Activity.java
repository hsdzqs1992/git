package com.zhuye.hougong.view;

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
import com.zhuye.hougong.adapter.find.LiWuAdapter;
import com.zhuye.hougong.adapter.me.DongTaiAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.DensityUtil;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.WrapRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

public class DongTai2Activity extends BaseActivity {

    int type;
    String token;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.fragment_home_recycle)
    WrapRecyclerView fragmentHomeRecycle;
    @BindView(R.id.fragment_home_materrial)
    MaterialRefreshLayout fragmentHomeMaterrial;
    DongTaiAdapter adapter;
    View header;
    @Override
    protected void initview() {
        super.initview();
        type = getIntent().getIntExtra("type", 1);
        token = getIntent().getStringExtra("token");
        adapter = new DongTaiAdapter(this);
        fragmentHomeMaterrial.setLoadMore(true);

       // View header = View.inflate(this,R.layout.dongtai_header,null);


        if (type == 1) {
            mywalotQianbao.setText("我的动态");
            header = getLayoutInflater().from(this).inflate(R.layout.dongtai_header,null);
            fragmentHomeRecycle.addHeaderView(header);
        } else if (type == 2) {
            mywalotQianbao.setText("好友动态");
            // header.setVisibility(View.GONE);
        }
        fragmentHomeRecycle.setAdapter(adapter);
        fragmentHomeRecycle.setLayoutManager(new LinearLayoutManager(this));


    }

    private final int normal = 0;
    private final int refresh = 1;
    private final int loadmore = 2;
    private int state = 0;
    public static int page = 1;

    @Override
    protected void initListener() {
        super.initListener();

        fragmentHomeMaterrial.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                state = refresh;
                //  initFindData(1,"201");
                getData(1);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                state = loadmore;
                page++;
                // initFindData(page,"201");
                getData(page);
            }
        });

        if(type ==1){
            header.findViewById(R.id.fabu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = (new Intent(DongTai2Activity.this, FaBuActivity.class));
                    in.putExtra("from","me");
                    startActivity(in);
                }
            });

            header.findViewById(R.id.fabu1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = (new Intent(DongTai2Activity.this, FaBuActivity.class));
                    in.putExtra("from","me");
                    startActivity(in);
                }
            });
        }

        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {

                view.findViewById(R.id.find_zuixin_pinglun).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DongTai2Activity.this, PingLunActivity.class);
                        intent.putExtra("dynamic_id",dongTaiBean.getData().get(position).getDynamic_id());
                        startActivity(intent);
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
                                    .params("token", Sputils.getString(DongTai2Activity.this,"token",""))
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
                                    .params("token", Sputils.getString(DongTai2Activity.this,"token",""))
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
                    }
                });


                view.findViewById(R.id.find_zuixin_liwu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //请求礼物数据
                        requestLiwuData(position);

                    }
                });
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
                                View vie = View.inflate(DongTai2Activity.this,R.layout.bottom_window,null);
                                final PopupWindow popupWindow = new PopupWindow(DongTai2Activity.this);
                                popupWindow.setContentView(vie);
                                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                                popupWindow.setHeight(DensityUtil.dip2px(DongTai2Activity.this,349f));
                                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                                popupWindow.setOutsideTouchable(true);
                                popupWindow.setFocusable(true);
                                ViewPager vp = vie.findViewById(R.id.viewpager);
                                vp.setAdapter(new LiWuAdapter(DongTai2Activity.this,2,liwu.getData(),((DongTaiBean.DataBean)dongTaiBean.getData().get(pos)).getUid(),((DongTaiBean.DataBean)dongTaiBean.getData().get(pos)).getDynamic_id()));
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
                                        CommentUtils.toast(DongTai2Activity.this,"songba");
                                    }
                                });




                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }else if(response.body().contains("208")){
                            startActivity(new Intent(DongTai2Activity.this, LoginActivity.class));
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
        getData(1);

    }


    DongTaiBean dongTaiBean;

    private void getData(int page) {
        OkGo.<String>post(Contants.dynamiclists)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //  Log.i("---", response.body());
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            try {
                                dongTaiBean = gson.fromJson(response.body(), DongTaiBean.class);
                                if (adapter != null) {
                                    switch (state) {
                                        case normal:
                                            if (dongTaiBean != null && adapter != null) {
                                                adapter.addData(dongTaiBean.getData());
                                                fragmentHomeRecycle.scrollToPosition(0);
                                            }
                                            break;
                                        case refresh:
                                            adapter.clear();
                                            adapter.addData(dongTaiBean.getData());
                                            fragmentHomeRecycle.scrollToPosition(0);
                                            fragmentHomeMaterrial.finishRefresh();
                                            break;
                                        case loadmore:
                                            if (dongTaiBean.getData() == null) {
                                                CommentUtils.toast(DongTai2Activity.this, "没有更多数据");
                                                DongTai2Activity.this.page--;
                                            } else {
                                                adapter.addData(dongTaiBean.getData());
                                                fragmentHomeRecycle.scrollToPosition(adapter.getSize());

                                            }
                                            fragmentHomeMaterrial.finishRefreshLoadMore();
                                            break;
                                    }
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        } else if (response.body().contains("201")) {
                            CommentUtils.toast(DongTai2Activity.this, "没有更多数据");
                            DongTai2Activity.this.page--;
                            fragmentHomeMaterrial.finishRefreshLoadMore();
                        } else if (response.body().equals("null")) {
                            CommentUtils.toast(DongTai2Activity.this, "没有更多数据");
                            DongTai2Activity.this.page--;
                            fragmentHomeMaterrial.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());
                    }
                });
    }

    @Override
    protected int getResId() {
        return R.layout.activity_dongtai2;
    }

    @OnClick(R.id.person_detail_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //// TODO: 2017/12/18 0018 解决刷新的问题

        adapter.clear();
        initData();
    }
}
