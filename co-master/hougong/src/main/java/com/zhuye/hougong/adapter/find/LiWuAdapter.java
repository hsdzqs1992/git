package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.bean.LiWu;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

/**
 * Created by zzzy on 2017/12/1.
 */

public class LiWuAdapter extends PagerAdapter {

    private String dongid;
    private int type1;
    private String uid;
    public List data;
    public Context con;

    public LiWuAdapter(Context con,int type1 ,List data,String uid,String dongid) {
        this.data = data;
        this.con = con;
        this.uid = uid;
        this.type1 = type1;
        this.dongid = dongid;
    }

    @Override
    public int getCount() {
        if(data.size()%8==0){
            return data.size()/8;
        }else{
            return data.size()/8 +1;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(con, R.layout.liwu,null);
        List data1 = new ArrayList();
       // int i = position;
        if(position<getCount()-1){
            for (int i = 0;i<8;i++){
                data1.add(data.get(position*8+i));
            }
        }else if (position==getCount()-1){
            for (int i = position*8;i<data.size();i++){
                data1.add(data.get(i));
            }
        }


        RecyclerView rv = view.findViewById(R.id.liwu);
        adapter2= new LiWuAdapter2(con,data1);
        rv.setAdapter(adapter2);
        rv.setLayoutManager(new GridLayoutManager(con,4));
        container.addView(view);
        initListener(position);
        return view;
    }

    private void initListener(final int page) {
        adapter2.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                if(type1 == 1){
                    //主页的
                    songli(page,position);
                }else if(type1 ==2){
                    //动态的
                    songli2(page,position);
                }
            }
        });
    }

    private void songli2(int page,int position) {
        OkGo.<String>post(Contants.rebate)
                .params("token", Sputils.getString(con, "token", ""))
                .params("gift_id", ((LiWu.DataBean)data.get(page*8+position)).getGift_id())
                .params("uid", uid)
                .params("type", 1)
                .params("ping_id", dongid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(con, "赠送成功");
                            if(type==1){

                            }else if(type==2){

                            }
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(con, "余额不足");
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(con, "赠送失败");
                    }


                });
    }

    private void songli(final int page, final int position) {
        OkGo.<String>post(Contants.rebate)
                .params("token", Sputils.getString(con, "token", ""))
                .params("gift_id", ((LiWu.DataBean)data.get(page*8+position)).getGift_id())
                .params("uid", uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            CommentUtils.toast(con, "赠送成功");
                            liwuanswer.success(response,Contants.BASE_URL+((LiWu.DataBean)data.get(page*8+position)).getPhoto());
                            if(type==1){

                            }else if(type==2){

                            }
                        }else if(response.body().contains("201")){
                            CommentUtils.toast(con, "余额不足");
                            liwuanswer.failed(response);
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(con, "赠送失败");
                        liwuanswer.failed( response);
                    }


                });
    }


    LiWuAdapter2 adapter2;

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    Liwuanswer liwuanswer;
    public void setliwueand(Liwuanswer liwuanswer){
        this.liwuanswer = liwuanswer;
    }


    public interface Liwuanswer{
        void success(Response<String> response,String url);
        void failed(Response<String> response);
    }
}
