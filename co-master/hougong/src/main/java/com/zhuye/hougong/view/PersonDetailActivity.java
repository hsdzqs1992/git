package com.zhuye.hougong.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.bean.PersonInfoBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzzy on 2017/11/21.
 */

public class PersonDetailActivity extends BaseActivity {
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_zhuanqian)
    TextView mywalotZhuanqian;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.person_go)
    ImageView personGo;
    //名字
    @BindView(R.id.person_name)
    EditText personName;
    @BindView(R.id.person_age)
    EditText personAge;
    @BindView(R.id.person_xingzuo)
    TextView personXingzuo;
    @BindView(R.id.person_zone)
    TextView personZone;
    @BindView(R.id.person_jibie)
    TextView personJibie;
    @BindView(R.id.person_detail_touxiang)
    RoundedCornerImageView personDetailTouxiang;
    @BindView(R.id.person_btn_nv)
    RadioButton personBtnNv;
    @BindView(R.id.person_btn_nan)
    RadioButton personBtnNan;
    @BindView(R.id.group)
    RadioGroup group;

    @Override
    protected int getResId() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initview() {
        super.initview();
        String token = Sputils.getString(this, "token", "");
        //获取个人信息
        OkGo.<String>post(Contants.information)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CommentUtils.toast(PersonDetailActivity.this, response.body());
                        Log.i("---------", response.body());
                        Gson gson = new Gson();
                        PersonInfoBean personInfoBean = gson.fromJson(response.body(), PersonInfoBean.class);
                        if (response.body().contains("200")) {

                            personName.setText(personInfoBean.getData().getNickname());
                            personAge.setText(personInfoBean.getData().getAge() + "岁");
                            personXingzuo.setText(personInfoBean.getData().getCon());
                            personZone.setText(personInfoBean.getData().getCity());
                            //  personJibie.setText(personInfoBean.getData().getLevel()+"平");
                            if (personInfoBean.getData().getSex().contains("0")) {
                                personBtnNan.setChecked(true);
                                personBtnNv.setChecked(false);
                            } else {
                                personBtnNan.setChecked(false);
                                personBtnNv.setChecked(true);
                            }

                            if (personInfoBean.getData().getLevel().contains("0")) {
                                personJibie.setText("平民");
                            } else {
                                personJibie.setText("VIP");
                            }

                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.person_detail_back, R.id.mywalot_zhuanqian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
            case R.id.mywalot_zhuanqian:
                tiJiaoData();
                break;

        }
    }

    private void tiJiaoData() {
        //// TODO: 2017/12/7 0007 星座地区的处理 
        OkGo.<String>post(Contants.edit_information)
                .params("token", Sputils.getString(PersonDetailActivity.this, "token", ""))
                .params("sex", group.getCheckedRadioButtonId() == R.id.person_btn_nv?"女":"男")
                .params("age", personAge.getText().toString().trim())
                .params("con", "星座")
                .params("city_code", "201")
                .params("city", "zhengzhou")
                .params("nickname", personName.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("llllll", response.body());
                        if (response.body().contains("200")) {
                           CommentUtils.toast(PersonDetailActivity.this,"修改成功");
                            //发消息通知修改界面
                            EventBus.getDefault().post(new MessageEvent());
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("llllll", response.body());
                    }
                });

    }
}
