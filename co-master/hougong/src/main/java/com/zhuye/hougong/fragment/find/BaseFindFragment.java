package com.zhuye.hougong.fragment.find;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.find.FindBaseAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.DongTaiBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.Sputils;

/**
 * Created by zzzy on 2017/11/23.
 */

public class BaseFindFragment extends BaseFragment {


    protected MaterialRefreshLayout materialRefreshLayout;
    protected RecyclerView recyclerView;

    @Override
    protected void initView() {

        materialRefreshLayout = rootView.findViewById(R.id.common_material);
        recyclerView = rootView.findViewById(R.id.commot_recycle);

        materialRefreshLayout.setLoadMore(true);
        // findZuiXinAdapter = new FindZuiXinAdapter(getActivity());
    }
    @Override
    protected int getResId() {
        return R.layout.common_recycle;
    }




    protected DongTaiBean dongTaiBean;

    protected void initFindBaseData(int type , String city_code, int page, final FindBaseAdapter adapter) {
        OkGo.<String>post(Contants.new_dynamic)
                .params("token", Sputils.getString(getActivity(),"token",""))
                .params("page",page)
                .params("type",type)
                .params("city_code",city_code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll",response.body());
                        Gson gson = new Gson();
                        try {
                            dongTaiBean = gson.fromJson(response.body(),DongTaiBean.class);
                            if(adapter!=null){
                                //finAdapter.setFindTongChengData(dongTaiBean);
                                if(dongTaiBean!=null&&adapter!=null){
                                    // finAdapter.setFindGuanZhuData(dongTaiBean);
                                    adapter.addData(dongTaiBean.getData());
                                }
                                // finAdapter.notifyDataSetChanged();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll",response.body());
                    }
                });
    }
}
