package com.zhuye.hougong.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.Code1;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.bean.PersonInfoBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.Sputils;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    TextView personName;
    @BindView(R.id.person_age)
    TextView personAge;
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



    private void tiJiaoData() {
        //// TODO: 2017/12/7 0007 星座地区的处理 
        OkGo.<String>post(Contants.edit_information)
                .params("token", Sputils.getString(PersonDetailActivity.this, "token", ""))
                .params("sex", group.getCheckedRadioButtonId() == R.id.person_btn_nv ? "女" : "男")
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
                            CommentUtils.toast(PersonDetailActivity.this, "修改成功");
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

    @OnClick({R.id.person_detail_back, R.id.mywalot_zhuanqian, R.id.person_detail_touxiang, R.id.person_go, R.id.person_name, R.id.person_age, R.id.person_xingzuo, R.id.person_zone, R.id.person_jibie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
            case R.id.mywalot_zhuanqian:
                tiJiaoData();
                break;
            case R.id.person_detail_touxiang:
            case R.id.person_go:
                seleciPicture();
                break;
            case R.id.person_name:
                alertdialog();
                break;
            case R.id.person_age:
                break;
            case R.id.person_xingzuo:
                break;
            case R.id.person_zone:
                break;
            case R.id.person_jibie:
                break;
        }
    }

    private void alertdialog() {


    }

    private void seleciPicture() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setSelectLimit(1);
        imagePicker.setCrop(false);
        Intent intent = new Intent(getApplicationContext(), ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }

    List<ImageItem> images = new ArrayList<>();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                //noinspection unchecked
                images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                //tasks = adapter.updateData(images);
                upload();
            } else {
                //showToast("没有数据");
                CommentUtils.toast(PersonDetailActivity.this,"没有数据");
            }
        }
    }

    private void upload() {
        if(images.size()<1){
            CommentUtils.toast(this,"请选择图片");
            return;
        }
        personDetailTouxiang.setImageURI(Uri.fromFile(new File(images.get(0).path)));
        PostRequest request = OkGo.<String>post(Contants.user_face)
                .params("token", Sputils.getString(PersonDetailActivity.this,"token",""))
                .params("file",new File(images.get(0).path));
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("---",response.body());
                Gson gson = new Gson();
                Code1 code = gson.fromJson(response.body(),Code1.class);
                if(response.body().contains("200")){
                    CommentUtils.toast(PersonDetailActivity.this,code.getMessage());
                }else if (response.body().contains("202")){
                    CommentUtils.toast(PersonDetailActivity.this,code.getMessage());
                }
            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("---",response.body());
            }
        });
    }
}
