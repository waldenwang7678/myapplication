package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.easemob.EMCallBack;
//import com.easemob.chat.EMChatManager;
//import com.easemob.exceptions.EaseMobException;
import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.util.Util;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

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
        findViewById(R.id.del_login_info).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String user = username.getText().toString().trim();
                String userpwd = pwd.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(userpwd)) {
                    Util.toast(HuanxinActivity.this, "账户或密码为空");
                    return;
                }
                boolean isLogin = Tool.login(user, userpwd, HuanxinActivity.this);
                if (isLogin) {
                    //跳转用聊天页面
                    Log.i("main", "登陆成功,页面跳转");
                    Intent intent = new Intent(HuanxinActivity.this, UserChatActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.register:
                Intent intent = new Intent(HuanxinActivity.this, RegesterActivity.class);
                startActivity(intent);
                break;
            case R.id.del_login_info:
                username.setText("");
                pwd.setText("");

                Tool.logout(HuanxinActivity.this);


                break;
        }
    }


}
