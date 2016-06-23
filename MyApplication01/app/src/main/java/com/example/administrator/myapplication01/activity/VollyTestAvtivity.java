package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.util.VollyHelper;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class VollyTestAvtivity extends Activity {
   // private  RequestQueue requestQueue = Volley.newRequestQueue(this, null);
   private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
         iv= (ImageView) findViewById(R.id.iv);
        iv.setImageResource(R.mipmap.ic_launcher);


    }
    public void image(View view){
        String url0="http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
        String url="http://avatar.csdn.net/8/B/B/1_sinyu890807.jpg";
   //Volly imageRequest加载图片
        VollyHelper.initImage(url0,iv);
   // Volly下的ImageLoader 加载图片
       /* ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this, null), new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv,
                R.drawable.a, R.mipmap.ic_launcher);//默认  ,失败

        imageLoader.get(url0, listener);*/
    }
}
