package com.zhuye.hougong.fragment.message;

import android.content.Intent;
import android.view.View;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.zhuye.hougong.view.ChatAvtiviyt;

import java.util.List;

/**
 * Created by admin on 2017/12/3.
 */

public class ChatFragment extends EaseConversationListFragment {

    @Override
    protected void initView() {
        super.initView();
        titleBar.setVisibility(View.GONE);
        //errorItemContainer.setVisibility(View.GONE);
        //跳转
        //头像处理
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent in = new Intent(getActivity(), ChatAvtiviyt.class);
                in.putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
               // if(conversation.getType())
                //in.putExtra(EaseConstant.EXTRA_CHAT_TYPE,)
               startActivity(in);
            }
        });
        //conversationListView.setPadding(0,(int)getActivity().getResources().getDimension(Integer.parseInt("-30")),0,0);
        //conversationListView.setPadding(0,(-10),0,0);
        //shan
        conversationList.clear();
      //shuju
        EMClient.getInstance().chatManager().addMessageListener(listener);
    }

    EMMessageListener listener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //shezhi
            EaseUI.getInstance().getNotifier().onNewMesg(list);
            refresh();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

}
