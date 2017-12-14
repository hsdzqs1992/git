package com.zhuye.hougong.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.Sputils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ZiPaiActivity extends BaseActivity {


    @BindView(R.id.mywalot_back)
    ImageView mywalotBack;
    @BindView(R.id.selectphoto)
    ImageView selectphoto;
    @BindView(R.id.zipai_kaiqi)
    Button zipaiKaiqi;

    @Override
    protected int getResId() {
        return R.layout.activity_zi_pai;
    }
    List<String> ids;

    @Override
    protected void initview() {
        super.initview();
        ids = getIntent().getStringArrayListExtra("id");
    }



    @OnClick({R.id.mywalot_back, R.id.selectphoto, R.id.zipai_kaiqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywalot_back:
                finish();
                break;
            case R.id.selectphoto:
                seleciPicture();


                break;
            case R.id.zipai_kaiqi:
                tijiao();
                break;
        }
    }

    private void tijiao() {
        if(TextUtils.isEmpty(path)){
            CommentUtils.toast(ZiPaiActivity.this,"请选择照片");
            return;
        }

        OkGo.<String>post(Contants.host_apply)
                .params("token", Sputils.getString(ZiPaiActivity.this,"token",""))
                .params("photo",path)
                .addUrlParams("feature",ids)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---",response.body());
                        if(response.body().contains("200")){
                            CommentUtils.toast(ZiPaiActivity.this,"提交成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("---",response.body());
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
                CommentUtils.toast(ZiPaiActivity.this,"没有数据");
            }
        }
    }

    private void upload() {
        if(images.size()<1){
            CommentUtils.toast(this,"请选择图片");
            return;
        }
        //// TODO: 2017/12/11 0011
//        ArrayList<File> files = new ArrayList<>();
//        if(images != null && images.size() > 0){
//
//            for (int i = 0 ;i< images.size();i++){
//                File file = new File(images.get(i).path);
//                files.add(file);
//            }
//        }

        PostRequest request = OkGo.<String>post(Contants.host_pic)
                .params("token", Sputils.getString(ZiPaiActivity.this,"token",""))
                .params("file",new File(images.get(0).path));
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("---",response.body());
                if(response.body().contains("200")){
                    Gson gson = new Gson();
                    Code1 code = gson.fromJson(response.body(),Code1.class);
                    path = code.getData();
                }else if (response.body().contains("202")){

                }
            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                //Log.i("---",response.body());
            }
        });


    }

    String path ;
}
