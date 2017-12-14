package com.zhuye.hougong.adapter.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuye.hougong.R;
import com.zhuye.hougong.contants.Contants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/29.
 */

public class Find2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List data = new ArrayList();
private Context conn;
    public Find2Adapter(Context con,List data) {
        this.data = data;
        conn = con;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv = null;
        switch (viewType){
            case 0 :
                View view = View.inflate(conn, R.layout.find_image1,null);
                Find2Holder find = new Find2Holder(view);
                rv= find;
                break;
            case 1 :
                View view1 = View.inflate(conn, R.layout.find_image3,null);
                Find2Holder find2 = new Find2Holder(view1);
                rv= find2;
                break;
        }
        return rv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof Find2Holder){
           ImageView  iv =  ((Find2Holder)holder).iv;
            Glide.with(conn).load(Contants.BASE_URL+data.get(position)).into(iv);
        }else if(holder instanceof Find3Holder){
            ImageView  iv =  ((Find3Holder)holder).iv;
            Glide.with(conn).load(Contants.BASE_URL+data.get(position)).into(iv);
        }




    }

    @Override
    public int getItemCount() {
        if(data== null|| data.size()<=0){
            return 0;
        }
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(data.size() == 1 ){
            return super.getItemViewType(position);
        }else if(data.size() > 1 ){
            return 1;
        }
        return 0;
    }

    class Find2Holder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public Find2Holder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivv);
        }
    }

    class Find3Holder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public Find3Holder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivv);
        }
    }
}
