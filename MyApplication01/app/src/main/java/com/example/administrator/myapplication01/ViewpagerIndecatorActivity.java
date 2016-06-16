package com.example.administrator.myapplication01;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class ViewpagerIndecatorActivity extends Activity {
    private int[] datas;
    private String[] titles;
    private PagerSlidingTabStrip tabs;
    private ViewPager vp;
    private List<View> viewData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_ind_layout);
        initdata();
        initView();

    }

    private void initdata() {
        viewData=new ArrayList<View>();
        titles = new String[]{"书籍","狗狗","融管家","订单","书籍",}; //标题
        datas=new int[]{R.drawable.a,R.drawable.dog,R.drawable.share_app,R.drawable.biaoqing_07,R.drawable.a};//图片

        for (int i=0;i<datas.length;i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(datas[i]);
            viewData.add(iv);
        }
    }
    private void initView() {
        tabs = (PagerSlidingTabStrip) findViewById(R.id.viewpage_tabs);
        tabs.setIndicatorColorResource(R.color.color_actionbar);
        tabs.setIndicatorHeight(6);
        tabs.setUnderlineHeight(1);
        tabs.setTextSize(this.getResources().getDimensionPixelSize(R.dimen.size_14));
        tabs.setTextColor(this.getResources().getColor(R.color.color_99));
        tabs.setTabSelectTextResource(R.color.color_actionbar);
        tabs.setTabPaddingLeftRight(this.getResources().getDimensionPixelSize(R.dimen.margin_12));

        vp = (ViewPager) findViewById(R.id.vp);
        MyAdapter adapter=new MyAdapter();
        vp.setAdapter(adapter);
        tabs.setViewPager(vp);
    }
    class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==(View)object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=viewData.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(viewData.get(position));
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
