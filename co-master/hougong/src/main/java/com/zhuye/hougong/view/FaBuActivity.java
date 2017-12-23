package com.zhuye.hougong.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.ImageAdapter;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.CommentUtils;
import com.zhuye.hougong.utils.FileUtil;
import com.zhuye.hougong.utils.GlideImageLoader;
import com.zhuye.hougong.utils.Sputils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaBuActivity extends AppCompatActivity {


    @BindView(R.id.fubu_content)
    EditText mFubuContent;
    @BindView(R.id.fabu_xuanze)
    TextView mFabuXuanze;
    @BindView(R.id.fabu_weizhi)
    TextView mFabuWeizhi;
    @BindView(R.id.fabu_fabiao)
    TextView fabuFabiao;
    @BindView(R.id.image)
    RecyclerView image;
    ImageAdapter adapter;
    ImageView location;
    String from;
    @BindView(R.id.person_detail_back)
    ImageView personDetailBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu);
        ButterKnife.bind(this);
        adapter = new ImageAdapter(this);
        image.setAdapter(adapter);
        image.setLayoutManager(new GridLayoutManager(this, 3));

        from = getIntent().getStringExtra("from");
        location = findViewById(R.id.fabu_location_iv);
        initData();
        initListener();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                images.remove(position);
                adapter.clear();
                adapter.addData(images);
            }
        });
    }

    private void initData() {

        OkGo.<String>post(Contants.cityindex)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("---", response.body());
                    }
                });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isslectedloat();
            }
        });
    }

    @OnClick({R.id.fabu_xuanze, R.id.fabu_weizhi, R.id.fabu_fabiao,R.id.person_detail_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabu_xuanze:
                //startActivity(new Intent(FaBuActivity.this, SelectPictureActivity.class));
                seleciPicture();
                break;
            case R.id.fabu_weizhi:
                isslectedloat();
                break;
            case R.id.fabu_fabiao:

                fabiao();
                break;

            case R.id.person_detail_back:
                finish();
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
                ArrayList<File> files = new ArrayList<>();
                if (images != null && images.size() > 0) {

                    for (int i = 0; i < images.size(); i++) {
                        File file = new File(images.get(i).path);
                        files.add(file);
                    }
                }
                adapter.addData(images);

//                Intent intent = new Intent(FaBuActivity.this,PingLunActivity.class);
//                intent.putParcelableArrayListExtra("image", (ArrayList<? extends Parcelable>) images);


            } else {
                //showToast("没有数据");
                CommentUtils.toast(FaBuActivity.this, "没有数据");
            }
        }
    }

    private void fabiao() {

        if (isshowlocation) {

        }

        String content = mFubuContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            CommentUtils.toast(FaBuActivity.this, "请输入内容");
            return;
        }
        fabuFabiao.setTextColor(Color.RED);
        fabuFabiao.setEnabled(false);
        ArrayList<File> files = new ArrayList<>();
        if (images != null && images.size() > 0) {

            for (int i = 0; i < images.size(); i++) {
                //File file = new File(images.get(i).path);
                files.add(FileUtil.getSmallBitmap(FaBuActivity.this, images.get(i).path));
            }
        }

        //// TODO: 2017/12/11 0011  chuanbushang
        PostRequest request = OkGo.<String>post(Contants.addcontent).params("token", Sputils.getString(FaBuActivity.this, "token", ""))
                .params("content", content)
                .params("city_code", "100")
                .params("city", "北京市");


        // Bitmap bitmap = new

        for (int i = 0; i < files.size(); i++) {
            request.params("file" + i, files.get(i));
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("---", response.body());
                CommentUtils.toast(FaBuActivity.this, "发布成功");
                fabuFabiao.setEnabled(true);
                fabuFabiao.setTextColor(Color.BLACK);
                if (from.equals("me")) {
                    Intent in = new Intent();
                    in.putExtra("type", "ai");
                    setResult(1000);
                }
                finish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("---", response.body());
            }
        });
//
//        UploadTask<String> task = OkUpload.request("haha", postRequest)//
//                .priority(10)//
//                .save()
//                .register(new MyListener("haa"));
//        //values.add(task);
//
//        task.start();


    }


    private Boolean isshowlocation = true;

    public void isslectedloat() {
        if (isshowlocation) {
            location.setImageResource(R.drawable.check_off);
        } else {
            location.setImageResource(R.drawable.check_on);
        }
        isshowlocation = !isshowlocation;
    }
}
