package com.example.administrator.myapplication01.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class Util {
    public static void toast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
