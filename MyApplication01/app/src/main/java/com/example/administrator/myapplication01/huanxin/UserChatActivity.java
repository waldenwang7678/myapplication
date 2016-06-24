package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.activity.MainActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class UserChatActivity extends Activity implements View.OnClickListener {

    private FrameLayout user_chat_fg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userchat);

        initView();


    }

    private void initView() {
        findViewById(R.id.logout).setOnClickListener(this);
        user_chat_fg = (FrameLayout) findViewById(R.id.user_chat_fg);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                Tool.logout(UserChatActivity.this);

                break;




        }
    }










    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //摁下返回键时候直接退回主页

        Intent intent = new Intent(UserChatActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onKeyDown(keyCode, event);
    }
}
