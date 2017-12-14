package com.zhuye.hougong.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzzy on 2017/11/21.
 */

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.feed_content)
    EditText feedContent;
    @BindView(R.id.feed_kefu)
    Button feedKefu;
    @BindView(R.id.feed_yijian)
    Button feedYijian;

    String content;
    @Override
    protected int getResId() {
        return R.layout.acttivitty_feedback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.feed_kefu, R.id.feed_yijian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.feed_kefu:



                break;
            case R.id.feed_yijian:
                clickFanTui();
                break;
        }
    }

    private void clickFanTui() {
        content = feedContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            Toast.makeText(FeedBackActivity.this, "请输入反馈内容",Toast.LENGTH_SHORT).show();
            return;
        }

        String token = Sputils.getString(FeedBackActivity.this,"token","");

        if(TextUtils.isEmpty(token)){

            //没登录
            Toast.makeText(FeedBackActivity.this, "请先登录",Toast.LENGTH_SHORT).show();
        }else{
            //登录
            //startActivity(new Intent(FeedBackActivity.this, PersonDetailActivity.class));
            OkGo.<String>post(Contants.feedback)
                    .params("token",token)
                    .params("content",content)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                           // Gson gson = new Gson();
                           // Code code = gson.fromJson(response.body(), Code.class);
                            if(response.body().contains("200")){
                                Toast.makeText(FeedBackActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();
                               finish();
                            }else{
                                Toast.makeText(FeedBackActivity.this,"反馈失败",Toast.LENGTH_SHORT).show();
                            }
//                            if(code.getCode()==200){
//                                Toast.makeText(FeedBackActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();
//                                finish();
//                            }else {
//                                Toast.makeText(FeedBackActivity.this,"反馈失败",Toast.LENGTH_SHORT).show();
//                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            Toast.makeText(FeedBackActivity.this,"反馈失败",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
