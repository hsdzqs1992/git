package com.zhuye.hougong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public abstract class BaseFragment extends Fragment {


    public View rootView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),getResId(),null);
        //initView();
        unbinder = ButterKnife.bind(this, view);
        rootView = view;
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initListener();
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected abstract void initView();

    protected abstract int getResId() ;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
