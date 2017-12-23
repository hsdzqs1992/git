package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMNormalFileMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.UserLocal;
import com.hyphenate.util.TextFormater;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.List;

public class EaseChatRowFile extends EaseChatRow{
    private static final String TAG = "EaseChatRowFile";

    protected TextView fileNameView;
	protected TextView fileSizeView;
    protected TextView fileStateView;
    
    private EMNormalFileMessageBody fileMessageBody;

    public EaseChatRowFile(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
	protected View  onInflateView() {
	    View view = inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
	            R.layout.ease_row_received_file : R.layout.ease_row_sent_file, this);
        return view;
	}

	@Override
	protected void onFindViewById() {
	    fileNameView = (TextView) findViewById(R.id.tv_file_name);
        fileSizeView = (TextView) findViewById(R.id.tv_file_size);
        fileStateView = (TextView) findViewById(R.id.tv_file_state);
        percentageView = (TextView) findViewById(R.id.percentage);
	}


	@Override
	protected void onSetUpView() {
	    fileMessageBody = (EMNormalFileMessageBody) message.getBody();
        String filePath = fileMessageBody.getLocalUrl();
        fileNameView.setText(fileMessageBody.getFileName());
        fileSizeView.setText(TextFormater.getDataSize(fileMessageBody.getFileSize()));
        if (message.direct() == EMMessage.Direct.RECEIVE) {
            File file = new File(filePath);
            if (file.exists()) {
                fileStateView.setText(R.string.Have_downloaded);
            } else {
                fileStateView.setText(R.string.Did_not_download);
            }
            return;
        }

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
        if (percentageView != null)
            percentageView.setVisibility(View.INVISIBLE);
        statusView.setVisibility(View.INVISIBLE);
    }

    private void onMessageSuccess() {
        progressBar.setVisibility(View.INVISIBLE);
        if (percentageView != null)
            percentageView.setVisibility(View.INVISIBLE);
        statusView.setVisibility(View.INVISIBLE);
    }

    private void onMessageError() {
        progressBar.setVisibility(View.INVISIBLE);
        if (percentageView != null)
            percentageView.setVisibility(View.INVISIBLE);
        statusView.setVisibility(View.VISIBLE);
    }

    private void onMessageInProgress() {
        progressBar.setVisibility(View.VISIBLE);
        if (percentageView != null) {
            percentageView.setVisibility(View.VISIBLE);
            percentageView.setText(message.progress() + "%");
        }
        statusView.setVisibility(View.INVISIBLE);
    }
}
