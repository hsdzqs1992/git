package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

public class ShenQingActivity extends AppCompatActivity {



    TextView name;
    TextView age;
    TextView person_xingzuo;
    RadioGroup group;
    TextView person_zone;
    ImageView zipai;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_qing);
        name = findViewById(R.id.person_name);
        age = findViewById(R.id.person_age);
        person_xingzuo= findViewById(R.id.person_xingzuo);
        group= findViewById(R.id.group);
        person_zone = findViewById(R.id.person_zone);
         zipai = findViewById(R.id.person_jibie);
        next = findViewById(R.id.next);

        //initData();
        initListener();
    }

    private void initListener() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShenQingActivity.this,ShenQing2Activity.class));
            }
        });
    }

    private void initData() {
        OkGo.<String>post(Contants.edit_information)
                .params("token", Sputils.getString(ShenQingActivity.this, "token", ""))
                .params("sex", group.getCheckedRadioButtonId() == R.id.person_btn_nv?"女":"男")
                .params("age", age.getText().toString().trim())
                .params("con", "星座")
                .params("city_code", "201")
                .params("city", "zhengzhou")
                .params("nickname", name.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(ShenQingActivity.this, "设置成功");
                            Gson gson = new Gson();

                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(ShenQingActivity.this, "设置失败");
                    }
                });

    }
}
