package com.zhuye.hougong.fragment.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.UserInfo;
import com.zhuye.hougong.model.Constant;
import com.zhuye.hougong.model.Modle;
import com.zhuye.hougong.utils.SpUtilss;
import com.zhuye.hougong.view.ChatAvtiviyt;
import com.zhuye.hougong.view.InvActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyphenate.easeui.EaseConstant.EXTRA_USER_ID;

/**
 * Created by admin on 2017/12/2.
 */

public class TongHuaFragment2 extends EaseContactListFragment {

    @Override
    protected void initView() {
        super.initView();
getView().findViewById(R.id.search_bar_view).setVisibility(View.GONE);
        //titleBar.setRightImageResource();

        //listView.addHeaderView();

       // titleBar.setRightLayoutClickListener();

        titleBar.setVisibility(View.GONE);

        view= View.inflate(getActivity(), R.layout.message_tonhua_item,null);
        listView.addHeaderView(view);

        hongdian = view.findViewById(R.id.message_item_hongdian);
       RelativeLayout rl = view.findViewById(R.id.head);

        hongdian.setVisibility(SpUtilss.getInstace().getBoolean(SpUtilss.NEW_INVITE,false)?View.VISIBLE:View.INVISIBLE);

       LocalBroadcastManager lb = LocalBroadcastManager.getInstance(getActivity());

        lb.registerReceiver(ConttactIvchangereceiver,new IntentFilter(Constant.NEW_INVITE_CHANGE));
        lb.registerReceiver(Conttactchangereceiver,new IntentFilter(Constant.CONTACT_CHANGE));
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hongdian.setVisibility(View.INVISIBLE);
                SpUtilss.getInstace().save(SpUtilss.NEW_INVITE,false);
                startActivity(new Intent(getActivity(),InvActivity.class));
            }
        });




        initData();
        setContactListItemClickListener(new EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
               Intent in =  new Intent(getActivity(),ChatAvtiviyt.class);

                in.putExtra(EXTRA_USER_ID,user.getUsername());
                startActivity(in);

            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void initData() {

        //获取联系人
        Modle.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //从服务器获取联系人
                    List<String> contacts =
                            EMClient.getInstance().contactManager().getAllContactsFromServer();

                    //保存数据库
                    //转化数据

                    List<UserInfo> userinfos = new ArrayList<UserInfo>();
                    for (int i = 0; i < contacts.size(); i++) {
                        userinfos.add(new UserInfo(contacts.get(i)));
                    }
                    //// TODO: 2017/12/4 位置有误
                    Modle.getInstance().getDbManager().getContactDao()
                            .saveContacts(userinfos,true);
                    //内存和网页
                    //判断activity是否为空
                    if (getActivity() == null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
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


    ImageView hongdian;
    View view;

    private BroadcastReceiver ConttactIvchangereceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            hongdian.setVisibility(View.VISIBLE);
            SpUtilss.getInstace().save(SpUtilss.NEW_INVITE,true);
        }
    };

    // jiahaoyou
    private BroadcastReceiver Conttactchangereceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          refreshdata();
        }
    };

    public void setView(View view) {
        this.view = view;
        //getdataframserver();

        //shanchu1
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 2

       EaseUser easeuser = (EaseUser) listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo)menuInfo).position);

       id = easeuser.getUsername();
        getActivity().getMenuInflater().inflate(R.menu.delete,menu);
    }

    String id;
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.delete){
           deleteconteact();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void deleteconteact() {
        Modle.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().deleteContact(id);
                    Modle.getInstance().getDbManager().getContactDao().deleteContactByHxId(id);

                    //jiamain
                    if(getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
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

//    private void getdataframserver() {
//
//
//        Modle.getInstance().getGlobalThread().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    List<String> data =EMClient.getInstance().contactManager().getAllContactsFromServer();
//
//                    List data1 = new ArrayList();
//
//                    for (String s : data){
//                        UserInfo user = new UserInfo(s);
//                        data1.add(user);
//                    }
//                    Modle.getInstance().getDbManager().getContactDao().saveContacts(data1,true);
//                    if(getActivity()==null){
//                        return;
//                    }
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //
//                            refreshdata();
//                        }
//                    });
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }

    private void refreshdata() {

        List<UserInfo> data =  Modle.getInstance().getDbManager().getContactDao().getContacts();
        if(data==null){
            return;
        }
        Map<String, EaseUser> map = new HashMap<>();


        for (UserInfo u : data){
            EaseUser e = new EaseUser(u.getHxid());
            map.put(u.getHxid(),e);
        }
        setContactsMap(map);
        refresh();
    }


}
