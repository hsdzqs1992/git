package com.zhuye.hougong.fragment.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.message.TongHuaListAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

import butterknife.BindView;

/**
 * Created by zzzy on 2017/12/5.
 */

public class TongHuaListFragment extends BaseFragment {


    @BindView(R.id.commot_recycle)
    RecyclerView commotRecycle;
    @BindView(R.id.common_material)
    MaterialRefreshLayout commonMaterial;

    TongHuaListAdapter adapter;
    @Override
    protected void initView() {

        adapter = new TongHuaListAdapter(getActivity());
        commotRecycle.setAdapter(adapter);
        commotRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int getResId() {
        return R.layout.common_recycle;
    }

    int page = 1;
    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Contants.call_record)
                .params("token", Sputils.getString(getActivity(),"token",""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        Gson gson = new Gson();
                        try {
//                            if(adapter!=null){
//                                //finAdapter.setFindTongChengData(dongTaiBean);
//                                if(dongTaiBean!=null&&adapter!=null){
//                                    // finAdapter.setFindGuanZhuData(dongTaiBean);
//                                    adapter.addData(dongTaiBean.getData());
//                                }
//                                // finAdapter.notifyDataSetChanged();
//                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        int i = 0;
                        Log.i("llllll",response.body());
                    }
                });

    }
}
