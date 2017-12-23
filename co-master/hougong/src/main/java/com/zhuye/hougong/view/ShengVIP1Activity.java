package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhuye.hougong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShengVIP1Activity extends AppCompatActivity {

    @BindView(R.id.shengji1_back)
    ImageView shengji1Back;
    @BindView(R.id.shengji1_kaitong)
    Button shengji1Kaitong;
    @BindView(R.id.activity_sheng_vip1)
    RelativeLayout activityShengVip1;
    @BindView(R.id.mywalot_back)
    ImageView mywalotBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sheng_vip1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.shengji1_back, R.id.shengji1_kaitong,R.id.mywalot_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shengji1_back:
                finish();
                break;
            case R.id.shengji1_kaitong:
                startActivity(new Intent(ShengVIP1Activity.this, ShengJiVIP2Activity.class));
                break;

            case R.id.mywalot_back:

                finish();
                break;
        }
    }
}
