package com.example.administrator.myapplication01.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.myapplication01.R;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class webViewActivity extends BaseActivity {
    String baidu="http://www.baidu.com";
    String baidu1="https://www.baidu.com/?tn=47018152_dg";

    String csdn="http://blog.csdn.net/guolin_blog/article/details/17482165#";
    @Override
    public void onCreate(Bundle savedInstanceState/*, PersistableBundle persistentState*/) {
        super.onCreate(savedInstanceState/*, persistentState*/);
        setContentView(R.layout.webview);
        WebView wv= (WebView) findViewById(R.id.wb);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);  //滚动条

        WebSettings settings = wv.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NORMAL);
        settings.setJavaScriptEnabled(true); // 设置支持javaScript
        settings.setSaveFormData(true);
        settings.setSavePassword(false); // 不保存密码
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setLightTouchEnabled(true);
        settings.setBuiltInZoomControls(false);	//不支持页面放大


        settings.setDefaultTextEncodingName("utf-8");	//非常关键，否则设置了WebChromeClient后会出现乱码

        settings.setUseWideViewPort(false);
        settings.setLoadWithOverviewMode(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
       // wv.setWebChromeClient(new WebChromeClient());
        /** 播放视频需要  */
        //wSettings.setPluginState(PluginState.ON);
        //wSettings.setAllowFileAccess(true);
        //wSettings.setUseWideViewPort(true);

        wv.setWebViewClient(new MyCliect());

      //  wv.loadUrl(baidu);
       wv.loadUrl(baidu);

    }
    public class MyCliect extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

        }

    }
}
