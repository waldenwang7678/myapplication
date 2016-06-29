package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

//import com.easemob.EMCallBack;
//import com.easemob.chat.EMChatManager;
//import com.easemob.exceptions.EaseMobException;
import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.activity.MainActivity;
import com.example.administrator.myapplication01.util.Util;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText username;
    private EditText pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();

    }

    private void initView() {
        username = (EditText) findViewById(R.id.count);
        pwd = (EditText) findViewById(R.id.pwd);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String user = username.getText().toString().trim();
                String userpwd = pwd.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(userpwd)) {
                    Util.toast(LoginActivity.this, "账户或密码为空");
                    return;
                }
               Tool.login(user, userpwd, LoginActivity.this);
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegesterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //摁下返回键时候直接退回主页

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onKeyDown(keyCode, event);
    }

}
