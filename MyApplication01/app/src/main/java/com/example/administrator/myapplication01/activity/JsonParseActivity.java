package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication01.Bean.Liu;
import com.example.administrator.myapplication01.R;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class JsonParseActivity extends Activity {

    private String jsonStr=null;
    private ListView lv_parse;
    private Liu liu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jsonparselayout);
        initView();

        jsonStr = "{\n" +
                "    \"data\": {\n" +
                "        \"pageCount\": 2,\n" +
                "        \"pageNo\": 1,\n" +
                "        \"pageSize\": 10,\n" +
                "        \"pages\": 1,\n" +
                "        \"result\": [\n" +
                "            {\n" +
                "                \"code\": \"精选推荐\",\n" +
                "                \"createTime\": 1466757200000,\n" +
                "                \"description\": \"西亭脆饼\",\n" +
                "                \"id\": 6,\n" +
                "                \"imagesUrl\": \"product/08b53bde-e01a-493e-9a24-5c1f85e388c0.jpg\",\n" +
                "                \"isDelete\": 0,\n" +
                "                \"photograph\": \"product/07736e6d-9b5d-49fb-9705-a40e5ded793d乳腐.jpg\",\n" +
                "                \"productName\": \"西亭脆饼\",\n" +
                "                \"recommendReason\": \"做工精细，具有酥甜香脆、美味可口的独特风格\",\n" +
                "                \"sku\": \"100025\",\n" +
                "                \"updateTime\": 1466757203000,\n" +
                "                \"zoneId\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"code\": \"精选推荐\",\n" +
                "                \"createTime\": 1466757095000,\n" +
                "                \"description\": \"乳腐\",\n" +
                "                \"id\": 5,\n" +
                "                \"imagesUrl\": \"product/d92f4d11-18bf-48b9-9623-ec54fa757962.jpg\",\n" +
                "                \"isDelete\": 0,\n" +
                "                \"photograph\": \"product/07736e6d-9b5d-49fb-9705-a40e5ded793d乳腐.jpg\",\n" +
                "                \"productName\": \"糟方乳腐\",\n" +
                "                \"recommendReason\": \"香味浓郁、质地细腻、口感酥糯\",\n" +
                "                \"sku\": \"100024\",\n" +
                "                \"updateTime\": 1466757100000,\n" +
                "                \"zoneId\": 3\n" +
                "            }\n" +
                "        ],\n" +
                "        \"totalCount\": 2\n" +
                "    },\n" +
                "    \"errCode\": \"\",\n" +
                "    \"message\": \"成功\",\n" +
                "    \"success\": true\n" +
                "}";

    }

    private void initView() {
        lv_parse = (ListView) findViewById(R.id.lv_parse);
    }

    /**
     * 解析json
     * @param s
     * @return
     */
    private Liu parseJson(String s) {
        Gson gson = new Gson();
        Liu liu = gson.fromJson(s, Liu.class);
        return liu;
    }

    /**
     * 按钮点击事件
     * @param view
     * @return
     */
    public void parse(View view){
        liu = parseJson(jsonStr);
//        for(Liu.DataBean.ResultBean b  :  liu.data.result){
//            System.out.println(b.productName);
//        }
        lv_parse.setAdapter(new ParseAdapter());

    }
    class ParseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return liu.data.result.size();
        }

        @Override
        public Object getItem(int position) {
            return liu.data.result.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv=new TextView(JsonParseActivity.this);
            tv.setText("产品名称: "+liu.data.result.get(position).productName);
            tv.setTextSize(24);
            return tv;
        }
    }
}
