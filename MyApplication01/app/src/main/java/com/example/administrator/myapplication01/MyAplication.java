package com.example.administrator.myapplication01;

import android.app.Application;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class MyAplication extends Application {

    public static Application instance;
    //程序运行事机会初始化
    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;

    }
    public static Application getInstance(){
        return instance;
    }
}
