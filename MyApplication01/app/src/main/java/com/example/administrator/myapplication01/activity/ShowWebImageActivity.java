package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.util.MyDialog;
import com.example.administrator.myapplication01.util.Util;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class ShowWebImageActivity extends Activity {

    private String str;
    private Drawable drawable;
    private ImageView iv;
    private MyDialog md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewlayout);
        initData();
        initView();
    }


    private void initData() {
        Intent intent = getIntent();
        str = intent.getStringExtra("image");
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv_wv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!ShowWebImageActivity.this.isFinishing()) {
                    md = MyDialog.show(ShowWebImageActivity.this, "正在加载");
                }
                drawable = loadImageFromNetwork(str);
                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        if (md != null) {
                            md.dismiss();
                        }
                        if (drawable == null) {
                            Util.toast(ShowWebImageActivity.this, "获取图片失败");
                        } else {
                            iv.setImageDrawable(drawable);
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 通过url获取图片
     *
     * @param url
     * @return
     */
    private Drawable loadImageFromNetwork(String url) {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromStream(new URL(url).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }

        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }
        return drawable;
    }
}
