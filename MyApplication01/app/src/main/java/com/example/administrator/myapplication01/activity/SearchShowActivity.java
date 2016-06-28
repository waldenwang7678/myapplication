package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication01.Bean.MyBean;
import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.adapter.BaseViewHolder;
import com.example.administrator.myapplication01.adapter.MyCallBack;
import com.example.administrator.myapplication01.adapter.SearchResultAdapter;
import com.example.administrator.myapplication01.listener.NetOnListener;
import com.example.administrator.myapplication01.util.NetUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchShowActivity extends Activity {

    private ListView listview_searchshow_pic;
    private ImageButton btn_top_left;
    private TextView txt_top_center;
    private String searchName;
    private List<MyBean.ResultBean.PetsBean> mListItem = new ArrayList<MyBean.ResultBean.PetsBean>();
    private ImageView img_item_dog_show;
    private TextView txt_dog_name;
    private ImageView img_dog_sex;
    private TextView txt_dog_brithday;
    private TextView idtxt_dog_type;
    private TextView txt_dog_sale_price;
    private TextView txt_dog_drder_price;

    public Activity getActivity() {
        return SearchShowActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_searchshow);
        Intent intent = getIntent();
        searchName = intent.getStringExtra("SearchName");
        initView();
        initEvent();
    }

    private void initView() {//初始化视图
        listview_searchshow_pic = (ListView) findViewById(R.id.listview_searchshow_pic);//list列表
        btn_top_left = (ImageButton) findViewById(R.id.btn_top_left);//左边返回箭头
        txt_top_center = (TextView) findViewById(R.id.txt_top_center);//中间的文字



        txt_top_center.setVisibility(View.VISIBLE);
        txt_top_center.setText(searchName);

        btn_top_left.setVisibility(View.VISIBLE);
    }

    private void initEvent() {//访问网络
        final String uri = "http://taochongbao.cn:8980/taochongbao/service/allPet/find";
        final Map<String, String> params = new HashMap<String, String>();
        params.put("categoryName", searchName);

       final NetUtils<String> uiNetUtils = new NetUtils<String>(getActivity()     );

        new Thread(new Runnable(){

            @Override
            public void run() {
                uiNetUtils.get(uri, params, new NetOnListener() {

                    @Override
                    public void success(Object obj) {
                        String CallBackJs = (String) obj;
                        parseJson(CallBackJs);
                        initSetAdapter();
                        // initAdapter();
                    }
                });
            }
        }).start();


    }

    protected void initAdapter() {
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("这是测试数据" + i);
        }
        listview_searchshow_pic.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("hahha");
                return textView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    protected void parseJson(String callBackJs) {
        Gson gson = new Gson();
//		Result searchResultBean = gson.fromJson(callBackJs,
//				SearchResultBean.Result.class);

        // SearchResultBean fromJson = gson.fromJson(callBackJs, SearchResultBean.class);
        MyBean fromJson = gson.fromJson(callBackJs, MyBean.class);
        List<MyBean.ResultBean.PetsBean> productList = fromJson.result.pets;

//		List<ProductListItem> productList = searchResultBean.pets;
        mListItem.addAll(productList);
//        for (ProductListItem item : productList) {
//            birthday = item.birthday;//出生值
//            bodyType = item.bodyType;//犬的类型
//            categoryName = item.categoryName;//犬的名称
//            imageUrl = item.imageUrl;//图片的地址
//            orderPrice = item.orderPrice;//定金
//            String petId = item.petId;
//            price = item.price;//售价
//            sex = item.sex;//性别
//
//
//
//        }
    }

    protected void initSetAdapter() {
        listview_searchshow_pic.setAdapter(new SearchResultAdapter(mListItem, R.layout.list_item_cat_show,
                new MyCallBack() {
                    @Override
                    public void refresh(BaseViewHolder viewHolder, int position) {
                        img_item_dog_show = (ImageView)  viewHolder.getView(R.id.img_item_dog_show);//图片
                        txt_dog_name = (TextView) viewHolder.getView(R.id.txt_dog_name);//名字
                        img_dog_sex = (ImageView)  viewHolder.getView(R.id.img_dog_sex);//性别
                        txt_dog_brithday = (TextView)  viewHolder.getView(R.id.txt_dog_brithday);//出生值
                        idtxt_dog_type = (TextView) viewHolder.getView(R.id.txt_dog_type);//犬的类型
                        txt_dog_sale_price = (TextView)  viewHolder.getView(R.id.txt_dog_sale_price);//售价
                        txt_dog_drder_price = (TextView)  viewHolder.getView(R.id.txt_dog_drder_price);//定金
                        MyBean.ResultBean.PetsBean petsBean = mListItem.get(position);
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity());//填充图片
                        bitmapUtils.display(img_item_dog_show, petsBean.imageUrl);
                       txt_dog_name.setText(petsBean.categoryName);//名字
                        txt_dog_sale_price.setText(petsBean.price);//售价
                        txt_dog_drder_price.setText(petsBean.orderPrice);//定金
                        txt_dog_brithday.setText(petsBean.birthday);//出生值
                        //设置性别
                        if (petsBean.sex.equals("0")) {//母
                            img_dog_sex.setBackgroundResource(R.drawable.img_sex_woman);
                        } else if (petsBean.sex.equals("1")) {//公
                            img_dog_sex.setBackgroundResource(R.drawable.img_sex_man);
                        }
                        //设置犬的类型
                        if (petsBean.bodyType.equals("0")) {
                            idtxt_dog_type.setText("小型犬");
                        } else if (petsBean.bodyType.equals("1")) {
                            idtxt_dog_type.setText("中型犬");
                        } else if (petsBean.bodyType.equals("2")) {
                            idtxt_dog_type.setText("大型犬");
                        }
                    }
                }));
    }
}
