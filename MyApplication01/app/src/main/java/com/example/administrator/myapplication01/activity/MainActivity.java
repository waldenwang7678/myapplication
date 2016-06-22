package com.example.administrator.myapplication01.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication01.huanxin.HuanxinActivity;
import com.example.administrator.myapplication01.R;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
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
        initView();


        scroller = new Scroller(this);
        int X = scroller.getCurrX();
        Log.i("TAG", "" + X);
        //scroller.startScroll(0, 0, 0, 100);
        //invalidate();
    }

    private void initView() {
        ListView lv = (ListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class clazz = null;
                switch (position) {
                    case 0:
                        clazz = HuanxinActivity.class;
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;

                }


                if (clazz != null) {
                    Intent intent = new Intent(MainActivity.this, clazz);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initdata() {
        for (int i = 0; i < 30; i++) {
            datas.add("data" + i);
        }
    }

    public void click1(View view) {
        Intent intent = new Intent(this, webViewActivity.class);
        startActivity(intent);
        //友盟统计
    }

    public void click2(View view) {
        Intent intent = new Intent(this, TActivity.class);
        startActivity(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent(this, VollyTestAvtivity.class);
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
            if (position == 0) {
                tv.setText("进入环信");
                tv.setTextSize(30);
                tv.setBackgroundColor(Color.parseColor("#CEFFC2"));
            } else {
                tv.setText(datas.get(position));
            }
            tv.setTextSize(20);
            return tv;
        }

    }
}












