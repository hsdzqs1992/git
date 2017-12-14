package com.zhuye.hougong.model.db;

import android.content.Context;

import com.zhuye.hougong.model.dao.ContactDao;

/**
 * Created by admin on 2017/12/2.
 */

public class DBManager {

    private final ContactDao contactDao;
    private final InvitationDao invitationDao;
    private final DBHelpter dbHelpter;

    public DBManager(Context context, String name){
        dbHelpter = new DBHelpter(context, name);

        //创建联系人操作类
        contactDao = new ContactDao(dbHelpter);
        //创建邀请信息操作类
        invitationDao = new InvitationDao(dbHelpter);
    }

    public ContactDao getContactDao(){
        return contactDao;
    }

    public InvitationDao getInvitationDao(){
        return invitationDao;
    }

    public void close(){
        dbHelpter.close();
    }
}
