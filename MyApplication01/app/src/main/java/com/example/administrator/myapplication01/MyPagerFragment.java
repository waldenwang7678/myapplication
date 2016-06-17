package com.example.administrator.myapplication01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class MyPagerFragment extends Fragment {
   int[] datas = new int[]{R.drawable.a, R.drawable.dog, R.drawable.share_app, R.drawable.biaoqing_07, R.drawable.a,R.drawable.dog, R.drawable.share_app};//图片
   private int position;
    public MyPagerFragment(int position) {
        this.position=position;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        ImageView iv=new ImageView(getActivity());
        int length=datas.length;
        if(position>length){
            position=length;  //如果标题位置大于图片数组大小,则标题位置=数组大小
        }
        iv.setImageResource(datas[position]);
        iv.setBackgroundColor(Color.parseColor("#CEFFC2"));
        return iv;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
