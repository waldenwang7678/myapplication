package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class SideSlippingActivity extends Activity {

    private FrameLayout frameLayout;
    private RelativeLayout left;
    private ListView left_listview;
    private RelativeLayout right;
    private TextView right_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sideslippinglayout);

        initView();

    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);

        left = (RelativeLayout) findViewById(R.id.left);
        left_listview = (ListView) findViewById(R.id.left_listview);

        right = (RelativeLayout) findViewById(R.id.right);
        right_textview = (TextView) findViewById(R.id.right_textview);
    }
}
