package com.example.administrator.myapplication01.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListViewItemSelecte extends AppCompatActivity {


    private ListView lv;
    private TextView tv1;
    private TextView tv2;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    List<Float> datas = new ArrayList<Float>();
    HashMap<Integer, Float> chackData = new HashMap<Integer, Float>();
    HashMap<Integer, Boolean> isCheackMap = new HashMap<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv1.setText("总价:" + gettotal() + "元");
            tv2.setText("(" + gettotalnum() + ")个");
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewitemselect_layout);
        initData();
        initView();
    }

    private void initData() {
        for (float i = 0; i < 30; i++) {
            datas.add(i);
            isCheackMap.put((int) i, false);
        }
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        final MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        tv1 = (TextView) findViewById(R.id.tv1);  //显示总价
        tv2 = (TextView) findViewById(R.id.tv2);  //显示数量
        bt1 = (Button) findViewById(R.id.bt1);    //点击删除选中的
        bt2 = (Button) findViewById(R.id.selectall);    //点击删除选中的
        bt3 = (Button) findViewById(R.id.selectnone);    //点击删除选中的
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initSetData(0);

                handler.sendEmptyMessage(0);
                adapter.notifyDataSetChanged();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initSetData(1);

                handler.sendEmptyMessage(0);
                adapter.notifyDataSetChanged();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initSetData(2);

                handler.sendEmptyMessage(0);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 更新集合数据  Araylist::datas    HashMap::chackData   HashMap::isCheackMap
     *
     * @param a
     */
    private void initSetData(int a) {
        if (a == 0) {//删除
            ArrayList<Integer> cache = new ArrayList<>();
            Iterator iter = isCheackMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                if ((boolean) entry.getValue()) {
                    int aaa = (int) entry.getKey();  //hashmap 值为 true 的 key
                    cache.add(aaa);  //保存位置信息
                }
            }
            int dif = 0;
            Iterator it = datas.iterator();
            while (it.hasNext()) {
                it.next();
                if (cache.contains(dif))
                    it.remove();
                dif++;
            }
            chackData.clear();
            isCheackMap.clear();
            for (int i = 0; i < datas.size(); i++) {
                isCheackMap.put((int) i, false);
            }
        } else if (a == 1) {     //全选
            chackData.clear();
            isCheackMap.clear();
            for (int i = 0; i < datas.size(); i++) {
                chackData.put(i, datas.get(i));
                isCheackMap.put(i, true);
            }
//            Iterator iter = isCheackMap.entrySet().iterator();
//            while (iter.hasNext()) {
//                Map.Entry entry = (Map.Entry) iter.next();
//                entry.setValue(true);
//            }
        } else if (a == 2) {     //反选
            for (int i = 0; i < datas.size(); i++) {
                if (chackData.containsKey(i)) {
                    chackData.remove(i);
                } else {
                    chackData.put(i, datas.get(i));
                }
            }
            Iterator iter = isCheackMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                if ((boolean) entry.getValue()) {
                    entry.setValue(false);
                } else {
                    entry.setValue(true);
                }
            }
        }
    }

    private float gettotal() {
        float total = 0;
        //遍历数据集合
        Iterator iter = chackData.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            float num = (float) entry.getValue();
            total += num;
        }
        return total;
    }

    private int gettotalnum() {
        return chackData.size();
    }


    class MyAdapter extends BaseAdapter {
        ViewHolder holder = null;

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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(ListViewItemSelecte.this);
                convertView = inflater.inflate(R.layout.listviewasd_item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.data);
                holder.cb = (AppCompatCheckBox) convertView.findViewById(R.id.cbasd);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.tv.setText("价格:" + datas.get(position) + "元");
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chackData.put(position, datas.get(position));  //收集选中条目的的价格数据
                        handler.sendEmptyMessage(0);
                    } else {
                        chackData.remove(position);
                        handler.sendEmptyMessage(0);
                    }
                    isCheackMap.put(position, isChecked); //收集选中条目位置信息,用于显示
                }
            });
            holder.cb.setChecked(isCheackMap.get(position));
            return convertView;
        }
    }

    class ViewHolder {
        public TextView tv;
        public AppCompatCheckBox cb;
    }
}
