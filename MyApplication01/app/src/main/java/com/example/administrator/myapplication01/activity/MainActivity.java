package com.example.administrator.myapplication01.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.huanxin.LoginActivity;
import com.example.administrator.myapplication01.huanxin.UserChatActivity;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<String> datas = new ArrayList<String>();
    Class clazz = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initdata();
        initView();
    }

    private void initView() {
        ListView lv = (ListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);
        //listView条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //如果之前登陆过,直接跳转到聊天页
                        if (EMClient.getInstance().isLoggedInBefore()) {
                            clazz = UserChatActivity.class;
                        } else {
                            clazz = LoginActivity.class;
                        }
                        break;
                    case 1:
                        clazz = SearchActivity.class;
                        break;
                    case 2:
                        clazz = ColorRingAcitivity.class;
                        break;
                    case 3:
                        clazz = VideoActivity.class;
                        break;
                    case 4:
                        clazz = ListViewItemSelecte.class;
                    case 5:
                        clazz = SideSlippingActivity.class;
                        break;

                }
                if (clazz != null) {
                    Intent intent = new Intent(MainActivity.this, clazz);
                    startActivity(intent);
                    clazz = null;
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

    //button点击事件
    public void click(View view) {

        switch (view.getId()) {
            case R.id.bt1:
                clazz = webViewActivity.class;
                break;
            case R.id.bt2:
                clazz = TActivity.class;
                break;
            case R.id.bt3:
                clazz = VollyTestAvtivity.class;
                break;
            case R.id.bt4:
                clazz = ViewpagerIndecatorActivity.class;
                break;
            case R.id.bt5:
                clazz = ViewPagerFragmentActivity.class;
                break;
        }
        if (clazz != null) {
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
            clazz = null;
        }
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
                tv.setText("环信");
                tv.setBackgroundColor(Color.parseColor("#CEFFC2"));
            } else if (position == 1) {
                tv.setText("dogSearch");

            } else if (position == 2) {
                tv.setText("ColorRingView");

            } else if (position == 3) {
                tv.setText("Video_SurfaceView");
            } else if (position == 4) {
                tv.setText("ListViewItemSeleccted");
            } else if (position == 5) {
                tv.setText("sldeslipping");
            } else {
                tv.setText(datas.get(position));
            }
            tv.setTextSize(30);

            return tv;
        }

    }
}












