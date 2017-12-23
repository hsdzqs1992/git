package com.zhuye.hougong.weidgt;

import com.lzy.okgo.model.Response;
import com.zhuye.hougong.adapter.BaseRecycleAdapter;
import com.zhuye.hougong.bean.DongTaiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class PageUtils {


    public static final int normal = 0;
    public  static final int refresh = 1;
    public final int loadmore = 2;
    public int state = 0;
    public int page = 1;
    List<DongTaiBean.DataBean> datas = new ArrayList<>();


    public static void handledata(Response<String> response,int state, BaseRecycleAdapter adapter){


//        if(adapter!=null){
//                    switch (state){
//                        case normal:
//                            if(dongTaiBean!=null&&adapter!=null){
//                                adapter.addData(dongTaiBean.getData());
//                            }
//                            break;
//                        case refresh:
//                            adapter.clear();
//                            adapter.addData(dongTaiBean.getData());
//                            recyclerView.scrollToPosition(0);
//                            materialRefreshLayout.finishRefresh();
//                            break;
//                        case loadmore:
//                            if(dongTaiBean.getData()==null){
//                                CommentUtils.toast(getActivity(),"没有更多数据");
//                                ZuiXinFragment2.this.page--;
//                            }else{
//                                adapter.addData(dongTaiBean.getData());
//                                recyclerView.scrollToPosition(adapter.getSize());
//
//                            }
//                            materialRefreshLayout.finishRefreshLoadMore();
//                            break;
//                    }
//                }
    };



}
