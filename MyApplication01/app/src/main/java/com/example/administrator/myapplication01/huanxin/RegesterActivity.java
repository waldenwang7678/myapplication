package com.example.administrator.myapplication01.huanxin;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.util.Util;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class RegesterActivity extends Activity {

    private EditText et_name;
    private EditText et_pwd;
    private EditText et_pwd_confirm;
    private Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.regester);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.rg_count);
        et_pwd = (EditText) findViewById(R.id.rg_pwd);
        et_pwd_confirm = (EditText) findViewById(R.id.pwd_confirm);
        bt = (Button) findViewById(R.id.rg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                String pwd_confirm = et_pwd_confirm.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_confirm)) {
                    Util.toast(RegesterActivity.this, "请填写完整");
                    return;
                }
//                try {
//                    EMClient.getInstance().createAccount(name, pwd);//同步方法
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

}
