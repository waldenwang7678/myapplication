package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapplication01.util.MyDialog;
import com.example.administrator.myapplication01.util.Util;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.NetUtils;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class Tool {
    private static Activity mContext;
    private static MyDialog md;

    /**
     * 注册账户
     *
     * @param name
     * @param pwd
     * @param activity
     */
    public static void regester(final String name, final String pwd, Activity activity) {
        mContext = activity;
        if (!activity.isFinishing()) {
            md = MyDialog.show(activity, "正在注册...");
        }
        new Thread(new Runnable() {  //注册时同步方法,而且访问网络,需要放在子线程中
            public void run() {
                try {
                    EMClient.getInstance().createAccount(name, pwd);    //同步方法
                    mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            md.dismiss();
                            Intent intent = new Intent(mContext, UserChatActivity.class);
                            mContext.startActivity(intent);
                        }
                    });
                } catch (final HyphenateException e) {
                    mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            md.dismiss();
                            int errorCode = e.getErrorCode();
                            if (errorCode == EMError.NETWORK_ERROR) {
                                Toast.makeText(mContext, "NETWORK_ERROR", Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                Toast.makeText(mContext, "USER_ALREADY_EXIST", Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                Toast.makeText(mContext, "USER_AUTHENTICATION_FAILED", Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                Toast.makeText(mContext, "USER_ILLEGAL_ARGUMENT", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "other", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();
    }


    /**
     * 登录   首次登录成功后，不需要再次调用登录方法，在下次APP启动时,SDK会自动登录
     *
     * @param count
     * @param pwd
     * @param activity
     */
    public static void login(final String count, final String pwd, Activity activity) {
        mContext = activity;
        if (!activity.isFinishing()) {
            md = MyDialog.show(activity, "正在登录...");
        }

        EMClient.getInstance().login(count, pwd, new EMCallBack() {//回调的三个方法都在子线程中执行  方法异步
            @Override
            public void onSuccess() {

                mContext.runOnUiThread(new Runnable() {
                    public void run() {
                        md.dismiss();
                        //以下两个方法是为了保证进入主页面后本地会话和群组都 load 完毕。
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Util.toast(mContext, "登录聊天服务器成功！");
                        //跳转聊天页面
                        Log.i("main", "登陆成功,页面跳转");
                        Intent intent = new Intent(mContext, UserChatActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(final int code, final String message) {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        md.dismiss();
                        Util.toast(mContext, "登录失败:Code:" + code + "message" + message);
                        Log.i("main", "登录失败:Code:" + code + "message" + message);
                    }
                });


            }
        });

    }


    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(final Activity context) {
        if (!context.isFinishing()) {
            md = MyDialog.show(context, "正在退出...");
        }
        EMClient.getInstance().logout(true);
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        md.dismiss();
        //此方法为异步方法
       /* EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                md.dismiss();
                Util.toast(context, "退出成功");
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                md.dismiss();
                Util.toast(context, "已经清空,请勿重复操作");
            }
        });*/
    }

    /**
     * 监听连接状态
     *
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
                        else {
                        }

                        //当前网络不可用，请检查网络设置
                    }
                }
            });
        }
    }

}
