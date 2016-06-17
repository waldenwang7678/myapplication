package com.example.administrator.myapplication01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> datas = new ArrayList<String>();
    private Scroller scroller;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.bt1);
        Button b2 = (Button) findViewById(R.id.bt2);

//        //布局方式
//        LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams
//                (LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
//        this.setContentView(ml, p0);  //设置根布局

        initdata();
        ListView lv = (ListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        scroller = new Scroller(this);
        int X = scroller.getCurrX();
        Log.i("TAG", "" + X);
        //scroller.startScroll(0, 0, 0, 100);
        //invalidate();
    }

    private void initdata() {
        for (int i = 0; i < 30; i++) {
            datas.add("data" + i);
        }
    }

    public void click1(View view) {
        Intent intent = new Intent(this, webViewActivity.class);
        startActivity(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent(this, TActivity.class);
        startActivity(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent(this, VollyTest.class);
        startActivity(intent);
    }

    public void click4(View view) {
        Intent intent = new Intent(this, ViewpagerIndecatorActivity.class);
        startActivity(intent);
    }
    public void click5(View view) {
        Intent intent = new Intent(this, ViewPagerFragmentActivity.class);
        startActivity(intent);
    }

    class MyAdapter extends BaseAdapter {

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
            TextView tv = new TextView(MainActivity.this);
            tv.setText(datas.get(position));
            tv.setTextSize(20);
            return tv;
        }

    }
}












