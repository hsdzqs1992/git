package com.zhuye.hougong.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.me.PictureListAdapter;
import com.zhuye.hougong.base.BaseActivity;
import com.zhuye.hougong.bean.PhotoBean;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.Sputils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

;

public class SelectPictureActivity extends BaseActivity {


    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;
    @BindView(R.id.mywalot_qianbao)
    TextView mywalotQianbao;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    PictureListAdapter adapter;
    @Override
    protected void initview() {
        super.initview();
        adapter= new PictureListAdapter(this);
        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(new GridLayoutManager(this,3));
    }

    @Override
    protected int getResId() {
        return R.layout.activity_select_picture2;
    }
    PhotoBean bean;

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if(position==0){
                    seleciPicture();
                }else {
                    deletePhoto(position);
                }
             }
        });
    }

    private void deletePhoto(final int position) {

        if(position==0){
            return;
        }
        // TODO: 2017/12/11 0011
        OkGo.<String>post(Contants.img_del)
                .params("token", Sputils.getString(SelectPictureActivity.this, "token", ""))
                .params("id", bean.getData().get(position).getL_id())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            bean.getData().remove(position);
                           //adapter.removeData(bean.getData().get(position),position);
                           adapter.removeData(position);
                            CommentUtils.toast(SelectPictureActivity.this, "删除成功");
                        } else if (response.body().contains("201")) {
                            CommentUtils.toast(SelectPictureActivity.this, "无更多数据");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(SelectPictureActivity.this, "");
                    }
                });


    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Contants.photos)
                .params("token", Sputils.getString(SelectPictureActivity.this, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().contains("200")) {
                            Gson gson = new Gson();
                            PhotoBean.DataBean dt = new PhotoBean.DataBean();
                            dt.setPhoto("");
                            bean= gson.fromJson(response.body(),PhotoBean.class);
                            bean.getData().add(0,dt);
                            adapter.addData(bean.getData());

                        } else if (response.body().contains("201")) {
                            CommentUtils.toast(SelectPictureActivity.this, "无更多数据");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        CommentUtils.toast(SelectPictureActivity.this, "");
                    }
                });
    }


    @OnClick(R.id.person_detail_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_detail_back:
                finish();
                break;
//            case R.id.select_photo:
//                //startActivity();
//                seleciPicture();
//                break;
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
                CommentUtils.toast(SelectPictureActivity.this,"没有数据");
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
        PostRequest request = OkGo.<String>post(Contants.img_upload)
                .params("token", Sputils.getString(SelectPictureActivity.this,"token",""));

        for (int i = 0 ;i<files.size() ;i++){
            request.params("file"+i,files.get(i));
        }

        request.execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---",response.body());
                        if(response.body().contains("200")){
                            bean.getData().clear();
                            adapter.clear();
                            initData();
                        }else if(response.body().contains("202")){
                           /// Gson gson = new Gson();
                            CommentUtils.toast(SelectPictureActivity.this,"出错了");
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
