package com.zhuye.hougong.fragment.paihang;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class MeiLiFragment extends BasePaiHangFragment {

    @Override
    protected void initData() {
        super.initData();
        //默认请求日的数据
        type1 = 3;
        day = 1;
        getBangData(1,type1,day);
    }

    // 对page 的处理
    @Override
    protected void onClickMonth() {
        type1 = 3;
        day = 3;
        getBangData(1,type1,day);
    }

    @Override
    protected void onClickZhou() {
        type1 = 3;
        day = 2;
        getBangData(1,type1,day);
    }

    @Override
    protected void onClickRi() {
        type1 = 3;
        day = 1;
        getBangData(1,type1,day);
    }



//    protected void handleData() {
//        if (bean.getData().size() == 1) {
//            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
//        } else if (bean.getData().size() == 2) {
//            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
//            handletoudata(paihangName,findZuixinAge,paihangMoney,paihangTouxiang,1);
//        } else if (bean.getData().size() >= 3) {
//            handletoudata(paihangName1,findZuixinAge1,paihangMoney1,paihangTouxiang1,0);
//            handletoudata(paihangName,findZuixinAge,paihangMoney,paihangTouxiang,1);
//            handletoudata(paihangName3,findZuixinAge3,paihangMoney3,paihangTouxiang3,2);
//            converData();
//            ada.clear();
//            ada.addData(newdata);
//        }
//
//    }
//
//
//    public void handletoudata(TextView name , TextView age , TextView money , ImageView face , int po) {
//        //设置数据  需要处理 // TODO: 2017/12/6 0006   uid
//        Glide.with(getActivity()).load(Contants.BASE_URL + bean.getData().get(po).getFace()).into(face);
//        name.setText(bean.getData().get(po).getNickname());
//        age.setText(bean.getData().get(po).getAge());
//        if (bean.getData().get(po).getSex().equals("1")) {
//            Drawable drawable = getResources().getDrawable(R.drawable.miss);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            age.setCompoundDrawables(drawable, null, null, null);
//        } else if (bean.getData().get(po).getSex().equals("0")) {
//            Drawable drawable = getResources().getDrawable(R.drawable.boy);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            age.setCompoundDrawables(drawable, null, null, null);
//        }
//        money.setText(bean.getData().get(po).getCount());
//    }
}
