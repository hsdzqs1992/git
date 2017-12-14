package com.zhuye.hougong.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.InvitationInfo;
import com.zhuye.hougong.model.Constant;
import com.zhuye.hougong.model.Modle;
import com.zhuye.hougong.utils.CommentUtils;

import java.util.ArrayList;
import java.util.List;


public class InvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv);

        ListView js = (ListView) findViewById(R.id.listview);

        adaper = new InvAdaper(this);
        js.setAdapter(adaper);
        refreshdata();
        adaper.setOnInviteListener(new InvAdaper.OnInviteListener() {
            @Override
            public void invitedSuccess(final InvitationInfo info) {
                Modle.getInstance().getGlobalThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().acceptInvitation(info.getUserInfo().getHxid());

                            Modle.getInstance().getDbManager().getInvitationDao().updateInvitationStatus(InvitationInfo.InvitationStatus.INVITE_ACCEPT,info.getUserInfo().getHxid());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshdata();
                                }
                            });
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void invitedReject(final InvitationInfo info) {
                Modle.getInstance().getGlobalThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().declineInvitation(info.getUserInfo().getHxid());

                            Modle.getInstance().getDbManager().getInvitationDao().removeInvitation(info.getUserInfo().getHxid());

                            Modle.getInstance().getDbManager().getContactDao()
                                    .deleteContactByHxId(info.getUserInfo().getHxid());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshdata();
                                   // CommentUtils.toast(InvActivity.class,"拒绝成功");
                                }
                            });
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        //
        LocalBroadcastManager  lb =  LocalBroadcastManager.getInstance(this);

        lb.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                refreshdata();
            }
        },new IntentFilter(Constant.NEW_INVITE_CHANGE));

    }

    InvAdaper adaper;
    private void refreshdata() {

       List data =  Modle.getInstance().getDbManager().getInvitationDao().getInvitations();
        adaper.setData(data);

    }


    static class InvAdaper extends BaseAdapter{

        private Context mContext;

private List<InvitationInfo> data = new ArrayList<>();
        public InvAdaper(Context context) {
            mContext = context;
        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void setData(List<InvitationInfo> data){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;
            if(view ==null){
                holder = new ViewHolder() ;
                view = View.inflate(mContext,R.layout.item_inv,null);
                holder.tvInviteName=view.findViewById(R.id.tv_invite_name);
                holder.tvInviteReason=view.findViewById(R.id.tv_invite_reason);
                holder.btInviteAccept=view.findViewById(R.id.bt_invite_accept);
                holder.btInviteReject=view.findViewById(R.id.bt_invite_reject);

                view.setTag(holder);

            }else{
                holder = (ViewHolder) view.getTag();
            }
            final InvitationInfo in = data.get(i);

            holder.tvInviteName.setText(in.getUserInfo().getHxid());
            //
            if((in.getStatus()==InvitationInfo.InvitationStatus.NEW_INVITE)){


               // holder.tvInviteReason.setText(in.getStatus());
            }else if (in.getStatus()==InvitationInfo.InvitationStatus.INVITE_ACCEPT){
                CommentUtils.toast(mContext,"接受了");
                holder.btInviteReject.setVisibility(View.GONE);
                holder.btInviteAccept.setVisibility(View.GONE);

            }else if (in.getStatus()==InvitationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER){

            }



            holder.tvInviteName.setText(in.getUserInfo().getUserName());


            //接受邀请的监听
            holder.btInviteAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onInviteListener != null){
                        onInviteListener.invitedSuccess(in);
                    }
                }
            });
            //拒绝邀请的监听
            holder.btInviteReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onInviteListener != null){
                        onInviteListener.invitedReject(in);
                    }
                }
            });

            return view;
        }

        class ViewHolder {
            TextView tvInviteName;
            TextView tvInviteReason;
            Button btInviteAccept;
            Button btInviteReject;
            ViewHolder() {

            }
        }
        /*
    *
    * 回调方法
    * */
        private OnInviteListener onInviteListener;

        public void setOnInviteListener(OnInviteListener onInviteListener) {
            this.onInviteListener = onInviteListener;
        }

        public interface OnInviteListener{
            void invitedSuccess(InvitationInfo info); //接受邀请
            void invitedReject(InvitationInfo info); //拒绝邀请
        }

    }

}
