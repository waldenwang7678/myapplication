package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class SideSlippingActivity extends Activity {

    private FrameLayout frameLayout;
    private RelativeLayout left;
    private ListView left_listview;
    private RelativeLayout right;
    private TextView right_textview;
    private ArrayList<String> datas = new ArrayList<String>();
    private Button bt_right_side;
    private Button bt_left_side;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sideslippinglayout);
        initdata();
        initView();
        initEvent();
    }


    private void initdata() {
        for (int i = 0; i < 20; i++) {
            datas.add("侧滑数据" + i);
        }
    }

    private void initView() {
        // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);  //右边禁止滑动

        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);

        left = (RelativeLayout) findViewById(R.id.left);
        left_listview = (ListView) findViewById(R.id.left_listview);

        right = (RelativeLayout) findViewById(R.id.right);
        right_textview = (TextView) findViewById(R.id.right_textview);

        bt_left_side = (Button) findViewById(R.id.bt_left_side);
        bt_right_side = (Button) findViewById(R.id.bt_right_side);


    }

    private void initEvent() {

        ListAdapter adapter = new ListAdapter();
        left_listview.setAdapter(adapter);

        bt_left_side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
            }
        });
        bt_right_side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {  //侧滑
                System.out.println("侧滑中...");

                View mContent = drawerLayout.getChildAt(0);
//                if(drawerView instanceof FrameLayout){
//                    System.out.println("FrameLayout");
//                }else if(drawerView instanceof RelativeLayout){
//                    System.out.println("RelativeLayout");
//                }
//                System.out.println("##############"+slideOffset);
//                if (drawerView.getTag().equals("LEFT")) {
//                    System.out.println("left");
//                }
                View mMenu = drawerView;
                //View mMenu = mContent;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {

                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,  mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else {
                    ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View view) {   //侧滑打开
              //  Util.toast(SideSlippingActivity.this, "侧滑打开");
            }

            @Override
            public void onDrawerClosed(View view) {  //侧滑关闭
               // Util.toast(SideSlippingActivity.this, "侧滑关闭");
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int i) {  //侧滑改变
                System.out.println("侧滑改变");
            }
        });

    }


    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(SideSlippingActivity.this);
            }
            ((TextView) convertView).setText(datas.get(position));
            ((TextView) convertView).setTextSize(24);
            return convertView;
        }
    }
}
