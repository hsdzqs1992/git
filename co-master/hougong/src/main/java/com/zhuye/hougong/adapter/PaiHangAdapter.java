package com.zhuye.hougong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhuye.hougong.R;
import com.zhuye.hougong.view.PersonHomePageActivity;

import java.util.List;

/**
 * Created by zzzy on 2017/11/20.
 */

public class PaiHangAdapter extends PagerAdapter {

    //@BindView(R.id.paihang_two)
    LinearLayout paihangTwo;
    //@BindView(R.id.paihang_one)
    LinearLayout paihangOne;
   // @BindView(R.id.paihang_three)
    LinearLayout paihangThree;
    private Context conn;
    List<String> titles;

    public PaiHangAdapter(Context con, List<String> titles) {
        conn = con;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(conn, R.layout.paihang_vp_item, null);
        paihangThree = view.findViewById(R.id.paihang_three);
        container.addView(view);
        initLisenter();
        return view;
    }

    private void initLisenter() {
        paihangThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conn.startActivity(new Intent(conn, PersonHomePageActivity.class));
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
