package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sideslippinglayout);
        initdata();
        initView();

    }

    private void initdata() {
        for (int i = 0; i < 20; i++) {
            datas.add("侧滑数据" + i);
        }
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);

        left = (RelativeLayout) findViewById(R.id.left);
        left_listview = (ListView) findViewById(R.id.left_listview);

        right = (RelativeLayout) findViewById(R.id.right);
        right_textview = (TextView) findViewById(R.id.right_textview);
        ListAdapter adapter = new ListAdapter();
        left_listview.setAdapter(adapter);

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

            return convertView;
        }
    }
}
