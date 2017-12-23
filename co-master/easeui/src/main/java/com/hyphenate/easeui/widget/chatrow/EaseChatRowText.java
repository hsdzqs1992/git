package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.UserLocal;
import com.hyphenate.easeui.utils.EaseSmileUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

public class EaseChatRowText extends EaseChatRow{

	private TextView contentView;

    public EaseChatRowText(Context context, EMMessage message, int position, BaseAdapter adapter) {
		super(context, message, position, adapter);
	}

	@Override
	protected View  onInflateView() {
		View view = inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
				R.layout.ease_row_received_message : R.layout.ease_row_sent_message, this);
        return view;
	}

	@Override
	protected void onFindViewById() {
		contentView = (TextView) findViewById(R.id.tv_chatcontent);
	}

    @Override
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
        contentView.setText(span, BufferType.SPANNABLE);


        if (message.direct() == EMMessage.Direct.RECEIVE) {
            Log.i("as",message.getFrom());
            List<UserLocal> songs = DataSupport.findAll(UserLocal.class);
            if(songs!=null && songs.size()>0){
                for (UserLocal user : songs){
                    if(user.getHxname().equals(message.getFrom())){
                        Log.i("as",user.getFacepath());
                        //holder.name.setText(user.getName());
                        // EaseUserUtils.setUserAvatar(getContext(),message.getFrom(), holder.avatar);
                        Glide.with(getContext()).load(user.getFacepath()).into(userAvatarView);
                    }
                }
            }
        } else {
            Glide.with(context).load(context.getSharedPreferences("con",Context.MODE_PRIVATE).getString("face","")).into(userAvatarView);
        }
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
        switch (msg.status()) {
            case CREATE:
                onMessageCreate();
                break;
            case SUCCESS:
                onMessageSuccess();
                break;
            case FAIL:
                onMessageError();
                break;
            case INPROGRESS:
                onMessageInProgress();
                break;
        }
    }

    private void onMessageCreate() {
        progressBar.setVisibility(View.VISIBLE);
        statusView.setVisibility(View.GONE);
    }

    private void onMessageSuccess() {
        progressBar.setVisibility(View.GONE);
        statusView.setVisibility(View.GONE);
    }

    private void onMessageError() {
        progressBar.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    private void onMessageInProgress() {
        progressBar.setVisibility(View.VISIBLE);
        statusView.setVisibility(View.GONE);
    }
}
