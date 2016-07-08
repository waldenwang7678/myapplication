package com.example.administrator.myapplication01.activity;

import android.content.Intent;
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
    Class[][] classDatas = new Class[][]{
            {UserChatActivity.class, LoginActivity.class},
            {SearchActivity.class},
            {ColorRingAcitivity.class},
            {VideoActivity.class},
            {ListViewItemSelecte.class},
            {SideSlippingActivity.class},
            {JavaScriptActivity.class},
            {JsonParseActivity.class},
            {SheetFatActivity.class},
            {Dagger2Activity.class},
            {MiniMusicActivity.class},
            {MaterialTextFieldActivity.class},
            {RippleBGActivity.class},
            {SpinnerLoadingActivity.class},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initdata();
        initView();
    }

    private void initdata() {
        int lengh = classDatas.length;
        for (int i = 0; i < 30; i++) {
            if (i < lengh) {
                String classs = classDatas[i][0].getSimpleName();
                datas.add(classs.substring(0, classs.length() - 8));
            } else {
                datas.add("data" + i);
            }
        }
    }

    private void initView() {
        ListView lv = (ListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);
        //listView条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {



                    if (EMClient.getInstance().isLoggedInBefore()) {
                        clazz = classDatas[0][0];
                    } else {
                        clazz = classDatas[0][1];
                    }
                } else if(position<classDatas.length) {
                    clazz = classDatas[position][0];
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


    //button点击事件
    public void click(View view) {

        switch (view.getId()) {
            case R.id.bt1:
                clazz = webViewActivity.class;
                break;
            case R.id.bt2:
                clazz = ScrollerActivity.class;
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
            tv.setText(datas.get(position));
            tv.setTextSize(30);
            return tv;
        }

    }
}












