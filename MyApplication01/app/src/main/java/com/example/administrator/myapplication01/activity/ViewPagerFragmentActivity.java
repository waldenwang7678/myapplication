package com.example.administrator.myapplication01.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bugtags.library.Bugtags;
import com.example.administrator.myapplication01.MyPagerFragment;
import com.example.administrator.myapplication01.utile.PagerSlidingTabStrip;
import com.example.administrator.myapplication01.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class ViewPagerFragmentActivity extends FragmentActivity {

    private ViewPager vp;
    private String[] titles;
    private PagerSlidingTabStrip tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpager_ind_layout);
        initData();
        initView();
    }

    private void initData() {
        //标题
        titles = new String[]{"书籍", "dog", "融管家", "订单", "书籍","dog", "融管家"};
    }

    private void initView() {
        MyPagerFragmentAdapter adapter = new MyPagerFragmentAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.vp);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.viewpage_tabs);
        tabs.setIndicatorColorResource(R.color.color_actionbar);
        tabs.setIndicatorHeight(12);
        tabs.setUnderlineHeight(4);
        tabs.setTextSize(this.getResources().getDimensionPixelSize(R.dimen.size_14));
        tabs.setTextColor(this.getResources().getColor(R.color.color_99));
        tabs.setTabSelectTextResource(R.color.color_actionbar);
        tabs.setTabPaddingLeftRight(this.getResources().getDimensionPixelSize(R.dimen.margin_12));

        vp.setAdapter(adapter);
        tabs.setViewPager(vp);
    }


    class MyPagerFragmentAdapter extends FragmentPagerAdapter {

        public MyPagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
           // MyPagerFragment fragment=new MyPagerFragment(position);
            MyPagerFragment fragment=MyPagerFragment.newInstance(position+"",null) ;
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
        MobclickAgent.onResume(this);
    }
}
