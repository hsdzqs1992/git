package com.zhuye.hougong.model;

import android.content.Context;

import com.zhuye.hougong.model.dao.AccoutDao;
import com.zhuye.hougong.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2017/11/26.
 */

public class Modle {

    private static Modle modle = new Modle();

    private Modle(){};

    public static Modle getInstance(){
        return modle;
    }


    AccoutDao accountDao;
    private Context context;
    private DBManager dbManager;
    public void init(Context context){

        this.context = context;
        //创建AccountDB数据库
        accountDao = new AccoutDao(context);

        //初始化全局监听
        new GlobalListener(context);
    }

    /*
    * 线程池分为四种
    * 第一种 缓存线程池 有多少可以开启多少
    * 第二种 定长线程池  固定大小
    * 第三种 调度线程池  可以延时周期执行
    * 第四种  单例线程池  单个
    *
    * */
    private ExecutorService thread = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread(){

        return thread;
    }
    public AccoutDao getAccountDao(){
        return accountDao;
    }

    public DBManager getDbManager(){
        return dbManager;
    }

    public void loginSuccess(String currentUser) {

        if (dbManager !=null){
            dbManager.close();
        }
        dbManager = new DBManager(context, currentUser + ".db");

    }
}
