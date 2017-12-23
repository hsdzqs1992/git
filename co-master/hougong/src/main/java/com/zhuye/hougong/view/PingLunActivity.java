package com.zhuye.hougong.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.find.PingLunAdapter;
import com.zhuye.hougong.bean.PingLunBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.http.MyCallback;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PingLunActivity extends AppCompatActivity {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.pinglun)
    EditText pinglun;
    String id;
    PingLunAdapter pingLunAdapter;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;
    ImageView faibao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycle3);
        faibao = findViewById(R.id.fabiao);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("dynamic_id");
        pingLunAdapter = new PingLunAdapter(getApplication());
        commotRecycle.setAdapter(pingLunAdapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(this));

        initData();
        initListener();
    }



    private void initListener() {
        faibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = pinglun.getText().toString().trim();
                pinglun.setText("");
                hideSoftKeyboard(pinglun,PingLunActivity.this);
                dapingLun(content);
            }
        });


        pingLunAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                //点赞 反复点击bug // TODO: 2017/12/5
                view.findViewById(R.id.piniglun_item_dianzantubiao).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        if (((PingLunBean.DataBean) bea.getData().get(position)).getZan_type() == 0) {
                            OkGo.<String>post(Contants.comment_zan)
                                    .params("token", Sputils.getString(PingLunActivity.this, "token", ""))
                                    .params("ping_id", ((PingLunBean.DataBean) bea.getData().get(position)).getPing_id())
                                    .execute(new MyCallback() {
                                        @Override
                                        protected void doFailue(Response<String> response) {

                                        }
                                        @Override
                                        protected void excuess(Response<String> response) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ((ImageView) view.findViewById(R.id.piniglun_item_dianzantubiao)).setImageResource(R.drawable.praise_on);
                                                   // ((TextView)view.findViewById(R.id.piniglun_item_zanshu)).setText(1+"");
                                                    bea.getData().get(position).setZan_type(1);
                                                }
                                            });

                                        }
                                    });


                        } else {
                            OkGo.<String>post(Contants.comment_qxzan)
                                    .params("token", Sputils.getString(PingLunActivity.this, "token", ""))
                                    .params("ping_id", ((PingLunBean.DataBean) bea.getData().get(position)).getPing_id())
                                    .execute(new MyCallback() {
                                        @Override
                                        protected void doFailue(Response<String> response) {

                                        }
                                        @Override
                                        protected void excuess(Response<String> response) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ((ImageView) view.findViewById(R.id.piniglun_item_dianzantubiao)).setImageResource(R.drawable.praise_of);
                                                   // ((TextView)view.findViewById(R.id.piniglun_item_zanshu)).setText(1+"");
                                                    bea.getData().get(position).setZan_type(0);
                                                }
                                            });

                                        }
                                    });
                        }
                    }
                });


            }
        });
    }

    private void dapingLun(String content) {
        OkGo.<String>post(Contants.comment)
                .params("token", Sputils.getString(PingLunActivity.this, "token", ""))
                .params("dynamic_id", id)
                .params("content", content)
                .params("type", 0)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---", response.body());
                        if(response.body().contains("200")){
                            pingLunAdapter.clear();
                            initData();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());

                    }
                });
    }

    PingLunBean bea;

    private void initData() {
        OkGo.<String>post(Contants.comment_list)
                .params("token", Sputils.getString(PingLunActivity.this, "token", ""))
                .params("dynamic_id", id)
                .params("page", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---", response.body());
                        try {
                            Gson gson = new Gson();
                            bea = gson.fromJson(response.body(), PingLunBean.class);
                            pingLunAdapter.addData(bea.getData());

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.i("---", response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---", response.body());

                    }
                });
    }




    @OnClick({R.id.person_detail_back, R.id.common_material,R.id.pinglun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
            case R.id.common_material:

                break;
            case R.id.pinglun:
               // showSoftInputFromWindow(PingLunActivity.this,pinglun);
                break;
        }
    }

    /**
     * EditText获取焦点并隐藏软键盘
     */
    public static void hideSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
