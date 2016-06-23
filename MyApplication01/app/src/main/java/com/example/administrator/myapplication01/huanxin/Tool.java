package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.administrator.myapplication01.util.Util;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class Tool {
    private static Activity mContext;
    static boolean flag=false;  //登录状态


    /**
     * 登录   首次登录成功后，不需要再次调用登录方法，在下次APP启动时,SDK会自动登录
     * @param count
     * @param pwd
     * @param activity
     */
    public static boolean login(final String count, final String pwd,Activity activity) {
        mContext = activity;

        EMClient.getInstance().login(count,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                mContext.runOnUiThread(new Runnable() {
                    public void run() {
                        //以下两个方法是为了保证进入主页面后本地会话和群组都 load 完毕。
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Util.toast(mContext, "登录聊天服务器成功！");
                    }
                });
                flag=true;
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Util.toast(mContext, "登录失败!");
                Log.i("main","登录失败/Code:"+code+"message"+message);
                flag=false;
            }
        });
        return flag;
    }


    /**
     * 退出登录
     * @param context
     */
    public static void logout(final Context context) {

        //此方法为异步方法
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                Util.toast(context, "退出成功");

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Util.toast(context, "已经清空,请勿重复操作");
            }
        });
    }

    /**
     * 监听连接状态
     * @param context
     */
    public static void addConnectionListener(final Activity context) {
        mContext = context;
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }


    /**
     * 实现ConnectionListener接口
     */
    private static class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {
        }

        @Override
        public void onDisconnected(final int error) {
            mContext.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(mContext)) ;
                            //连接不到聊天服务器
                        else {    }

                        //当前网络不可用，请检查网络设置
                    }
                }
            });
        }
    }

}
