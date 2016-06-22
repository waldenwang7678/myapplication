package com.example.administrator.myapplication01;

import android.app.Application;
import android.util.Log;

import com.bugtags.library.Bugtags;
//import com.easemob.chat.EMChat;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class MyAplication extends Application {

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化BugTag
        Bugtags.start("e0dd62b04fbad2d051eff53c2a116482", this, Bugtags.BTGInvocationEventBubble);
        //初始化友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //初始化环信
//        Log.d("EMChat Demo", "initialize EMChat SDK");
//        EMChat.getInstance().init(getApplicationContext());
//        // debugmode设为true后，就能看到sdk打印的log了
//        EMChat.getInstance().setDebugMode(true);


    }

    public static Application getInstance() {
        return instance;
    }
}
