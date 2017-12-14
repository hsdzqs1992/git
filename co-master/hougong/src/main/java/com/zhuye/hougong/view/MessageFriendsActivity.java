package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhuye.hougong.R;
import com.zhuye.hougong.model.Modle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFriendsActivity extends AppCompatActivity {

    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.queren)
    Button queren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_friends);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.queren)
    public void onViewClicked() {

        final String name = search.getText().toString().trim();

        Modle.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                try {

                    EMClient.getInstance().contactManager().addContact(name,"jiagehaoyou");
                    finish();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
