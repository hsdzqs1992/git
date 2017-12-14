package com.zhuye.hougong.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.FeatureBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenQing2Activity extends AppCompatActivity {
    TagFlowLayout tag;
    ImageView quereb;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.next)
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_qing2);
        ButterKnife.bind(this);
        tag = findViewById(R.id.id_flowlayout);
        quereb = findViewById(R.id.mywalot_zhuanqian);

        dat = new HashMap(30);
        for (int i = 0; i < 30; i++) {
            dat.put(i, false);
        }

        initData();
        iinitListener();

    }

    public Map<Integer, Boolean> dat;

    private void iinitListener() {
        tag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            //// TODO: 2017/12/11 0011 dat 没值
            public boolean onTagClick(final View view, int position, FlowLayout parent) {
                if (dat.get(position)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.name).setBackgroundColor(getResources().getColor(R.color.white));
                            ((TextView) view.findViewById(R.id.name)).setTextColor(Color.BLACK);
                        }
                    });

                    //dat.get(position)=false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.name).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            ((TextView) view.findViewById(R.id.name)).setTextColor(Color.WHITE);
                        }
                    });

                }

                dat.put(position, !dat.get(position));
                return true;
            }
        });

        quereb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 2017/12/11 0011 shangchuantechang

                OkGo.<String>post(Contants.feature)
                        .params("token", Sputils.getString(ShenQing2Activity.this, "token", ""))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                if (response.body().contains("200")) {
                                    CommentUtils.toast(ShenQing2Activity.this, "设置成功");
                                    Gson gson = new Gson();
                                    bean = gson.fromJson(response.body(), FeatureBean.class);
//                                    dat = new HashMap(bean.getData().size());
//                                    for(int i = 0; i<bean.getData().size();i++){
//                                        dat.put(i,false);
//                                    }
                                    tag.setAdapter(new TagAdapter<FeatureBean.DataBean>(bean.getData()) {
                                        @Override
                                        public View getView(FlowLayout parent, int position, FeatureBean.DataBean o) {
                                            View view = View.inflate(ShenQing2Activity.this, R.layout.search_edit, null);
                                            TextView tv = view.findViewById(R.id.name);
                                            tv.setText(o.getTitle());
                                            return view;
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                CommentUtils.toast(ShenQing2Activity.this, "设置失败");
                            }
                        });
            }
        });

    }

    //private Boolean chose = false;

    FeatureBean bean;

    private void initData() {
        OkGo.<String>post(Contants.feature)
                .params("token", Sputils.getString(ShenQing2Activity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            // CommentUtils.toast(ShenQing2Activity.this, "设置成功");
                            Gson gson = new Gson();
                            bean = gson.fromJson(response.body(), FeatureBean.class);
                            //   dat = new HashMap(bean.getData().size());
                            tag.setAdapter(new TagAdapter<FeatureBean.DataBean>(bean.getData()) {
                                @Override
                                public View getView(FlowLayout parent, int position, FeatureBean.DataBean o) {
                                    View view = View.inflate(ShenQing2Activity.this, R.layout.search_edit, null);
                                    TextView tv = view.findViewById(R.id.name);
                                    tv.setText(o.getTitle());
                                    return view;
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        // CommentUtils.toast(ShenQing2Activity.this, "设置失败");
                    }
                });
    }

    @OnClick({R.id.person_detail_back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
            case R.id.next:
                List<FeatureBean.DataBean> choose = new ArrayList<>();
                for(int i = 0;i<bean.getData().size();i++){
                    if(dat.get(i)){
                        choose.add(bean.getData().get(i));
                    }
                }
//                if(choose.size()==0){
//                    CommentUtils.toast(ShenQing2Activity.this,"请选择特长");
//                    return;
//                }

                //String[] dd = new String[choose.size()];
                ArrayList<String> dd = new ArrayList();
                for (int i = 0;i<choose.size();i++){
                    dd.add(choose.get(i).getId());
                }

                Intent intent = new Intent(ShenQing2Activity.this,ZiPaiActivity.class);
                intent.putStringArrayListExtra("id",dd);
                startActivity(intent);
                finish();
                //upload(dd);
                break;
        }
    }

    private void upload(List<String> dd) {

        OkGo.<String>post(Contants.host_apply)
                .params("token", Sputils.getString(ShenQing2Activity.this, "token", ""))
                .params("token", "/sdfa")
                .params("feature", "6")
                //.addUrlParams("feature",dd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(ShenQing2Activity.this, "设置成功");

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(ShenQing2Activity.this, "设置失败");
                    }
                });
    }
}
