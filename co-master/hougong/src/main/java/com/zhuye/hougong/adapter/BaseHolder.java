package com.zhuye.hougong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzzy on 2017/11/21.
 */

public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View  rootView;



    public OnItemClickListener mOnItemClickListener;

    public BaseHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        itemView.setOnClickListener(this);

    }

    public <V extends View> V getView(int ResId){
        return (V)rootView.findViewById(ResId);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.OnItemClick(view,getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

}
