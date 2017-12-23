package com.zhuye.hougong.http;

import android.util.Log;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by zzzy on 2017/11/27.
 */

public abstract class MyCallback extends StringCallback {
    @Override
    public void onSuccess(Response<String> response) {
        if(response.body().contains("200")){
            excuess(response);
        }else{
            doFailue(response);
        }
        //T t - gson.fromJson(response.body(),T.class);
    }


    protected abstract void doFailue(Response<String> response);

    protected abstract void excuess(Response<String> response);
    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        Log.i("-----------------",response.message());
    }

}
