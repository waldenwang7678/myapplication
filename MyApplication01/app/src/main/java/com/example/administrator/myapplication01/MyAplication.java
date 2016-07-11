package com.example.administrator.myapplication01;

import android.app.Application;

import com.example.administrator.myapplication01.util.Util;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.umeng.analytics.MobclickAgent;

//import com.easemob.chat.EMChat;

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
        //  Bugtags.start("e0dd62b04fbad2d051eff53c2a116482", this, Bugtags.BTGInvocationEventBubble);
        //初始化友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);


//        int fd = open("/proc/diskstats", O_RDONLY);
//        bytes = read(fd, buf, bytes);
//        IMEI 识别码
//        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String imei = tm.getDeviceId();
//        String imsi = tm.getSubscriberId();
//        System.out.println("imei:"+imei+"......imsi:"+imsi);

//        String str = Util.getPhoneNum(this);
//先判断真机还是模拟器在初始化环信
        if (Util.getSerialNum().matches("nox")) {
            EMOptions options = new EMOptions();
            // 默认添加好友时，是不需要验证的，改成需要验证
            options.setAcceptInvitationAlways(false);
            //初始化环信
            EMClient.getInstance().init(this, options);
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(false);
        }
    }


    public static Application getInstance() {


        return instance;
    }
}
