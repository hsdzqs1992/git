package com.zhuye.hougong.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.shawnlin.numberpicker.NumberPicker;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.Code1;
import com.zhuye.hougong.bean.MessageEvent;
import com.zhuye.hougong.bean.PersonInfoBean;
import com.zhuye.hougong.city.ChooseAddressActivity;
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
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected ImmersionBar mImmersionBar;
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Override
    protected void initview() {
        super.initview();
        if (isImmersionBarEnabled())
            initImmersionBar();
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
                            Glide.with(PersonDetailActivity.this).load(Contants.BASE_URL+personInfoBean.getData().getFace()).into(personDetailTouxiang);
                            personName.setText(personInfoBean.getData().getNickname());
                            personAge.setText(personInfoBean.getData().getAge() + "岁");
                            personXingzuo.setText(personInfoBean.getData().getCon());
                            personZone.setText(personInfoBean.getData().getCity());
                            if(personInfoBean.getData().getSex().equals("1")){
                                personBtnNv.setChecked(true);
                            }else if (personInfoBean.getData().getSex().equals("2")){
                                personBtnNan.setChecked(true);
                            }

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
        code =  Sputils.getString(PersonDetailActivity.this,"code","");
        ci =  Sputils.getString(PersonDetailActivity.this,"city","");
    }


    @Override
    protected void initListener() {
        super.initListener();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectRadioBtn();
            }
        });
    }

    String sex  ;
    private void selectRadioBtn(){
        RadioButton radioButton = (RadioButton)findViewById(group.getCheckedRadioButtonId());
        sex = radioButton.getText().toString();
    }

    private void tiJiaoData() {
        //// TODO: 2017/12/7 0007 星座地区的处理 
        OkGo.<String>post(Contants.edit_information)
                .params("token", Sputils.getString(PersonDetailActivity.this, "token", ""))
                .params("sex", group.getCheckedRadioButtonId()==R.id.person_btn_nv ? 1 :2)
                .params("age", personAge.getText().toString().trim())
                .params("con", personXingzuo.getText().toString().trim())
                .params("city_code", code)
                .params("city", ci)
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
                selectAge();
                break;
            case R.id.person_xingzuo:
                selectXing();
                break;
            case R.id.person_zone:

                selectCity();
                break;
            case R.id.person_jibie:
                break;
        }
    }

    private void selectCity() {
        //选择城市
        Intent in = new Intent(PersonDetailActivity.this, ChooseAddressActivity.class);
        in.putExtra("now","郑州市");
        startActivityForResult(in,10);
    }


    int pos1;
    private void selectXing() {

        final AlertDialog dial = new  AlertDialog.Builder(PersonDetailActivity.this).create();
        View view = View.inflate(PersonDetailActivity.this,R.layout.aliet,null);
        dial.setView(view);
        view.findViewById(R.id.shoufei).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.jinbi).setVisibility(View.INVISIBLE);

        final String[] city = new String[]{"白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座","水瓶座","双鱼座"};
        NumberPicker picker = view.findViewById(R.id.picker);
        picker.setDisplayedValues(city);
        picker.setMinValue(0);
        picker.setMaxValue(city.length - 1);
        picker.setValue(0);
        //picker.setTextSize(8f);

        //picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
        dial.show();
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                pos1= i1;
            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personXingzuo.setText(city[pos1]);
                if(dial!=null&&dial.isShowing()){
                    dial.dismiss();
                }
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dial!=null&&dial.isShowing()){
                    dial.dismiss();
                }
            }
        });
    }

    int pos ;
    private void selectAge() {
      final AlertDialog dialo = new  AlertDialog.Builder(PersonDetailActivity.this).create();
        View view = View.inflate(PersonDetailActivity.this,R.layout.aliet,null);
        dialo.setView(view);

        view.findViewById(R.id.shoufei).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.jinbi).setVisibility(View.INVISIBLE);
        List<String> data = new ArrayList();
        for(int i = 10 ;i< 60;i++){
            data.add(i+"");
        }
        final String[] city = new String[data.size()];
        for(int i = 0;i<data.size();i++){
            city[i]=data.get(i);
        }
        NumberPicker picker = view.findViewById(R.id.picker);
        picker.setDisplayedValues(city);
        picker.setMinValue(0);
        picker.setMaxValue(city.length - 1);
        picker.setValue(0);
        //picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
        dialo.show();


        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // Log.i("acy",i+"");
                Log.i("acy",i1+"asdfsd");
                pos= i1;
            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                personAge.setText(city[pos]);
                if(dialo!=null&&dialo.isShowing()){
                    dialo.dismiss();
                }
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialo!=null&&dialo.isShowing()){
                    dialo.dismiss();
                }
            }
        });
    }

    private void alertdialog() {
        final AlertDialog dialog = new  AlertDialog.Builder(PersonDetailActivity.this).create();
        View view = View.inflate(PersonDetailActivity.this,R.layout.aliet1,null);
        dialog.setView(view);
        dialog.show();
        final EditText ed = view.findViewById(R.id.content);


        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String tet = ed.getText().toString().trim();
                if(TextUtils.isEmpty(tet)){
                    CommentUtils.toast(PersonDetailActivity.this,"请输入昵称");
                    return;
                }

                personName.setText(tet);
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
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
    String code;
    String ci;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            ci= data.getStringExtra("city");
            code = data.getStringExtra("citycode");
            personZone.setText(ci);
        }
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
