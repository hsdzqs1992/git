package com.zhuye.hougong.fragment.message;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.zhuye.hougong.R;

/**
 * Created by zzzy on 2017/12/5.
 */

public class MessageChatFragment extends EaseChatFragment {

    @Override
    protected void initView() {
        super.initView();
        titleBar.setBackgroundResource(R.color.primary);
    }
}
