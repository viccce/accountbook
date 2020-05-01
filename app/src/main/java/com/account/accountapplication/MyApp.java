package com.account.accountapplication;

import android.app.Application;
import android.content.Context;

import com.xuexiang.xui.XUI;

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
        mContext = getApplicationContext();
    }

    public static Context getInstance() {
        return mContext;
    }
}