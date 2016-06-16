package com.example.administrator.myapplication01;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class VollyHelper {



    private static RequestQueue requestQueue = Volley.newRequestQueue(MyAplication.getInstance() );

    public static void add(Request request){
        if(requestQueue==null){
            requestQueue=Volley.newRequestQueue(MyAplication.getInstance());
        }
        requestQueue.add(request);
    }
    public static void cancel(Object tag) {
        if (null == requestQueue) {
            requestQueue = Volley.newRequestQueue(MyAplication.getInstance() );
        }
        requestQueue.cancelAll(tag);
    }
//
//    public static void mainFragReq(Context context, String tag, String url, String key, final JSONObject contentParams,
//                                   HttpListener<String> callback) {

    public static void initImage( String url,final ImageView iv){
/*
        ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                    iv.setImageBitmap(bitmap);
            }
        }, 200, 200, Bitmap.Config.RGB_565, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    iv.setImageResource(R.drawable.a);
                }
        });*/
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        iv.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iv.setImageResource(R.drawable.a);
                    }
                });
        add(request);
    }


}
