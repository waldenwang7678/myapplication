package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.easemob.EMCallBack;
//import com.easemob.chat.EMChatManager;
//import com.easemob.exceptions.EaseMobException;
import com.example.administrator.myapplication01.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class HuanxinActivity extends Activity implements View.OnClickListener {

    private EditText username;
    private EditText pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huanxin);
//        if (DemoHelper.getInstance().isLoggedIn()) {
//            autoLogin = true;
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//            return;
//        }
        initView();

    }

    private void initView() {
        username = (EditText) findViewById(R.id.count);
        pwd = (EditText) findViewById(R.id.pwd);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String user = username.getText().toString().trim();
                String userpwd = pwd.getText().toString().trim();
                login(user, userpwd);
                Toast.makeText(HuanxinActivity.this, "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                Toast.makeText(HuanxinActivity.this, "等待中...", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void login(final String count, final String pwd) {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                EMChatManager.getInstance().login(count, pwd, new EMCallBack() {
//
//                    @Override
//                    public void onSuccess() {
//                        //登录成功
//                        Toast.makeText(HuanxinActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                        try {
//                            //获取好友名称
//                            List<String> usernames = EMChatManager.getInstance().getContactUserNames();
//                        } catch (EaseMobException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onProgress(int progress, String status) {
//                        Toast.makeText(HuanxinActivity.this, "正在登陆", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onError(int code, final String message) {
//                        Toast.makeText(HuanxinActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                });
//            }
//       }).start();

    }
}
