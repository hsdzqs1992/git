package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.Sputils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                break;
        }
    }

    private void seleciPicture() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setSelectLimit(9);
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
        ArrayList<File> files = new ArrayList<>();
        if(images != null && images.size() > 0){

            for (int i = 0 ;i< images.size();i++){
                File file = new File(images.get(i).path);
                files.add(file);
            }
        }
        PostRequest request = OkGo.<String>post(Contants.host_pic)
                .params("token", Sputils.getString(ZiPaiActivity.this,"token",""));

        for (int i = 0 ;i<files.size() ;i++){
            request.params("file"+i,files.get(i));
        }

        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("---",response.body());
                CommentUtils.toast(ZiPaiActivity.this,"上传成功");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("---",response.body());
            }
        });
    }
}
