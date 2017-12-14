package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegeistActivity extends AppCompatActivity {


    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.activity_regeist)
    LinearLayout activityRegeist;

    String phone;
    String pass;
    String repassword;
    String regeistYanzhengma1;
    @BindView(R.id.regeist_back)
    ImageView regeistBack;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_code)
    EditText loginCode;
    @BindView(R.id.login_sendcode)
    Button loginSendcode;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_repassword)
    EditText loginRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeist);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.login_sendcode, R.id.login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_sendcode:
                phone=loginUsername.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegeistActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                OkGo.<String>post(Contants.GET_REGEIST_URL)
                        .params("mobile", phone).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //Gson gson = new Gson();
                        //Code code = gson.fromJson(response.body(), Code.class);

                        if (response.body().contains("200")) {
                            Toast.makeText(RegeistActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegeistActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }

//                        if (code.getCode() == 200) {
//                            Toast.makeText(RegeistActivity.this, "获取验证码成功，为" + code.getData(), Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(RegeistActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(RegeistActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.login_login:
                phone=loginUsername.getText().toString().trim();
                regeistYanzhengma1=loginCode.getText().toString().trim();
                pass=loginPassword.getText().toString().trim();
                repassword=loginRepassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegeistActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(repassword)) {
                    Toast.makeText(RegeistActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                regeistFromServer();
                break;
        }
    }

    private void regeistFromServer() {
        OkGo.<String>post(Contants.REGEIST_URL).params("mobile", phone)
                .params("password", pass)
                .params("code", regeistYanzhengma1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        //JsonObject jsonObject = new JsonObject();
                        //JsonObject s = jsonObject.getAsJsonObject(response.body());
                        //s.get("Code");
                        if(response.body().contains("200")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        EMClient.getInstance().createAccount(phone,pass);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(new Intent(RegeistActivity.this, LoginActivity.class));
                                                CommentUtils.toast(RegeistActivity.this,"注册成功");
                                                finish();
                                            }
                                        });
                                    } catch (HyphenateException e) {
                                        CommentUtils.toast(RegeistActivity.this,"注册失败"+e.getMessage());
                                        //e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
//                        Toast.makeText(RegeistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(RegeistActivity.this, LoginActivity.class));
//                        finish();

//                                Gson gson = new Gson();
//                                Code code = gson.fromJson(response.body(), Code.class);
//                                if(code.getCode()==200){
//                                    Toast.makeText(RegeistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(RegeistActivity.this,LoginActivity.class));
//                                    finish();
//                                }else {
//                                    Toast.makeText(RegeistActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
//                                }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
