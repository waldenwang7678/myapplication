package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication01.R;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class JsActivity extends Activity {

    private WebView jswv;
    private Button previous;
    private Button next;
    private String url;
    private EditText js_et;
    private Button js_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jslayout);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
    }


    private void initView() {
        js_et = (EditText) findViewById(R.id.js_et);
        js_load = (Button) findViewById(R.id.js_load);

        jswv = (WebView) findViewById(R.id.js_wv);
        jswv.getSettings().setJavaScriptEnabled(true);
        previous = (Button) findViewById(R.id.previous);

        next = (Button) findViewById(R.id.next);
        url = js_et.getText().toString();

    }

    private void initEvent() {
        if (url == null || url.length() == 0) {
            jswv.loadUrl("file:///android_asset/javascript.html");
        } else {
            jswv.loadUrl(url);
        }
        jswv.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");

        jswv.setWebViewClient(new MyWebViewClient());

        js_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jswv.loadUrl(url);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jswv.canGoForward()) {
                    jswv.goForward();
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jswv.canGoBack()) {
                    jswv.goBack();
                }
            }
        });

    }

    public class JavascriptInterface {

        private Context context;

        public JavascriptInterface(Context context) {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img) {
            System.out.println(img);
            Intent intent = new Intent();
            intent.putExtra("image", img);
            intent.setClass(context, ShowWebImageActivity.class);
            context.startActivity(intent);
            System.out.println(img);
        }
    }

    // 监听 webView加载状态
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);

            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }

    //用js添加图片点击事件
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        jswv.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }


    /*
        javascript:(function(){
            var objs = document.getElementsByTagName("img");
            for(var i=0;i<objs.length;i++){
                objs[i].onclick=function() {
                    window.imagelistner.openImage(this.src); //this指的是一个image节点对象 src这个对象的属性
                }
            }
        })();
    */
}
