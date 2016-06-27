package com.example.administrator.myapplication01.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.huanxin.Tool;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class SettingFragment extends Fragment implements View.OnClickListener{

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setting,null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Button bt_logout= (Button) rootView.findViewById(R.id.logout);
        bt_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout:
                Tool.logout(getActivity());
                break;

        }
    }
}
