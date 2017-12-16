package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.HistoryBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_queding)
    ImageView searchQueding;
    @BindView(R.id.search_history)
    LinearLayout searchHistory;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();

        initListener();
    }

    private void initListener() {
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(SearchActivity.this, h.getData().get(position), Toast.LENGTH_SHORT).show();
                //处理edit
                searchEt.setText(h.getData().get(position));
                //再次请求数据
                sousuo(h.getData().get(position));
                return true;
            }
        });
    }

    HistoryBean h;
    private void initData() {

        //初始化历史搜索数据
        OkGo.<String>post(Contants.history_select)
                .params("token", Sputils.getString(SearchActivity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll", response.body());
                        //// TODO: 2017/11/29  解析历史数据加入流式布局中
                        try {
                            Gson gson = new Gson();
                            h = gson.fromJson(response.body(),HistoryBean.class);
                            if(h!=null){
                                idFlowlayout.setAdapter(new TagAdapter<String>(h.getData()) {
                                    @Override
                                    public View getView(FlowLayout parent, int position, String o) {
                                        View view = View.inflate(SearchActivity.this,R.layout.search_edit,null);
                                        TextView tv = view.findViewById(R.id.name);
                                        tv.setText(o);
                                        return view;
                                    }
                                });
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll", response.body());
                    }
                });
    }

    @OnClick({R.id.search_back, R.id.search_queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_queding:
                String text = searchEt.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    CommentUtils.toast(SearchActivity.this, "请输入内容");
                    return;
                }
                sousuo(text);
                break;
        }
    }

    //搜索
    private void sousuo(String search) {
        //// TODO: 2017/12/5 光标处理有问题
        searchEt.setCursorVisible(false);
      //  searchEt.setFocusable(false);
        OkGo.<String>post(Contants.select)
                .params("token", Sputils.getString(SearchActivity.this, "token", ""))
                .params("page", 1)
                .params("keyword", search)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //// TODO: 2017/12/5 无内容
                        Log.i("llllll", response.body());
                        searchEt.setCursorVisible(true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // searchEt.setFocusable(true);
                            }
                        });

                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll", response.body());
                        searchEt.setCursorVisible(true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // searchEt.setFocusable(true);
                            }
                        });
                    }
                });
    }
}
