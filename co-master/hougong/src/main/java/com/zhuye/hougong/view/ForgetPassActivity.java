package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends AppCompatActivity {

    @BindView(R.id.forget_username)
    EditText forgetUsername;
    @BindView(R.id.forget_yanzhengma)
    EditText forgetYanzhengma;
    @BindView(R.id.forget_sendcode)
    Button forgetSendcode;
    @BindView(R.id.forget_next)
    Button forgetNext;

    String phone;
    String code;
    @BindView(R.id.regeist_back)
    ImageView regeistBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.forget_sendcode, R.id.forget_next,R.id.regeist_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regeist_back:

                finish();

                break;
            case R.id.forget_sendcode:
                phone = forgetUsername.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ForgetPassActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }


                getForgetCode();

                break;
            case R.id.forget_next:
                code = forgetYanzhengma.getText().toString().trim();
                //测试
//                Intent intent = new Intent(ForgetPassActivity.this, ForgetPass2Activity.class);
//                intent.putExtra("phone", phone);
//                startActivity(intent);


            yanzhengma();


            break;
        }
    }

    private void getForgetCode() {
        OkGo.<String>post(Contants.GET_REGEIST_URL)
                .params("mobile", phone).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
               // Gson gson = new Gson();
               // Code code = gson.fromJson(response.body(), Code.class);
                if (response.body().contains("200")) {
                    Toast.makeText(ForgetPassActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ForgetPassActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(ForgetPassActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void yanzhengma() {
        OkGo.<String>post(Contants.yz_code)
                .params("mobile", phone)
                .params("code", code).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                //Code code = gson.fromJson(response.body(), Code.class);
                if (response.body().contains("200")) {
                    Intent intent = new Intent(ForgetPassActivity.this, ForgetPass2Activity.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("code", code);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgetPassActivity.this, "提交验证码错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(ForgetPassActivity.this, "提交验证码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
