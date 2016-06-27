package com.example.administrator.myapplication01.huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.activity.MainActivity;
import com.example.administrator.myapplication01.fragment.FirFragment;
import com.example.administrator.myapplication01.fragment.SecFragment;
import com.example.administrator.myapplication01.fragment.SettingFragment;
import com.example.administrator.myapplication01.util.Util;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class UserChatActivity extends FragmentActivity  implements View.OnClickListener{

    private FrameLayout user_chat_fg;
    private Fragment cacheFragment;
    private FirFragment firFragment;   //聊天页面
    private SecFragment secFragment;//联系人页面
    private SettingFragment settingFragment;//会话列表页面
    SparseArray<Fragment> fragments = new SparseArray<Fragment>();
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public UserChatActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userchat);

        initView();


    }



    private void initView() {


        fragmentManager = getSupportFragmentManager();
        firFragment=new FirFragment();
        cacheFragment=firFragment;
        transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.user_chat_fg, firFragment ).commit();


    }


    public void onTabClicked(View v) {

        transaction = fragmentManager.beginTransaction();
        if(cacheFragment!=null){
            transaction.hide(cacheFragment);
        }
        switch (v.getId()) {
            case R.id.btn_container_conversation:
                Util.toast(UserChatActivity.this,"1");
                /*if(firFragment==null){
                    firFragment=new FirFragment();
                    transaction.add(R.id.user_chat_fg, firFragment );
                }else{
                    transaction.show(firFragment);
                }
                cacheFragment=firFragment;
                transaction.commit();*/
                break;
            case R.id.btn_container_address_list:
                Util.toast(UserChatActivity.this,"2");
               /* if(secFragment==null){
                    secFragment=new SecFragment();
                    transaction.add(R.id.user_chat_fg, secFragment );
                }else{
                    transaction.show(secFragment);
                }
                cacheFragment=secFragment;
                transaction.commit();*/
                break;
            case R.id.btn_container_setting:
                Util.toast(UserChatActivity.this,"3");
                /*if(settingFragment==null){
                    settingFragment=new SettingFragment();
                    transaction.add(R.id.user_chat_fg, settingFragment );
                }else{
                    transaction.show(settingFragment);
                }
                cacheFragment=settingFragment;
                transaction.commit();*/
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_address_list:
                Util.toast(UserChatActivity.this,"bt1");
        }
    }
}
