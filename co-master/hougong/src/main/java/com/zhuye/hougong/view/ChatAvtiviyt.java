package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.zhuye.hougong.R;
import com.zhuye.hougong.fragment.message.MessageChatFragment;


public class ChatAvtiviyt extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_avtiviyt);

        iniData();
    }

    private void iniData() {

        MessageChatFragment fr = new MessageChatFragment();

       // String id =  getIntent().getStringExtra(EXTRA_USER_ID);
        fr.setArguments(getIntent().getExtras());
        FragmentTransaction tr =  getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.ff,fr);
        tr.commit();
    }
}
