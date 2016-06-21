package com.example.administrator.myapplication01;

import android.app.Application;

import com.bugtags.library.Bugtags;

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
        Bugtags.start("e0dd62b04fbad2d051eff53c2a116482", this, Bugtags.BTGInvocationEventBubble);
    }
    public static Application getInstance(){
        return instance;
    }
}
