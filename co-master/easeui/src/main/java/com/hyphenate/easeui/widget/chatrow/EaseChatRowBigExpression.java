package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.UserLocal;
import com.hyphenate.easeui.domain.EaseEmojicon;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * big emoji icons
 *
 */
public class EaseChatRowBigExpression extends EaseChatRowText{

    private ImageView imageView;


    public EaseChatRowBigExpression(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }
    
    @Override
    protected View onInflateView() {
        View view = inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_bigexpression : R.layout.ease_row_sent_bigexpression, this);
        return view;
    }

    @Override
    protected void onFindViewById() {
        percentageView = (TextView) findViewById(R.id.percentage);
        imageView = (ImageView) findViewById(R.id.image);
    }


    @Override
    public void onSetUpView() {
        String emojiconId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, null);
        EaseEmojicon emojicon = null;
        if(EaseUI.getInstance().getEmojiconInfoProvider() != null){
            emojicon =  EaseUI.getInstance().getEmojiconInfoProvider().getEmojiconInfo(emojiconId);
        }
        if(emojicon != null){
            if(emojicon.getBigIcon() != 0){
                Glide.with(activity).load(emojicon.getBigIcon()).placeholder(R.drawable.ease_default_expression).into(imageView);
            }else if(emojicon.getBigIconPath() != null){
                Glide.with(activity).load(emojicon.getBigIconPath()).placeholder(R.drawable.ease_default_expression).into(imageView);
            }else{
                imageView.setImageResource(R.drawable.ease_default_expression);
            }
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
}
