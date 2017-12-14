package com.zhuye.hougong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/21.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder binder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        binder = ButterKnife.bind(this);
        initview();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected void initview() {
    }

    protected abstract int getResId();
}
