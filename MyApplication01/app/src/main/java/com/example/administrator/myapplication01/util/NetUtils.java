package com.example.administrator.myapplication01.util;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.myapplication01.listener.NetOnListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.Map;

/**
 * annotation:网络连接的工具类
 */
public class NetUtils<T> {

    //上下文对象
    private Context mContext;
    //回调接口
    private NetOnListener mListener;

    public NetUtils(Context context) {
        this.mContext = context;
    }

    /**
     * GET请求
     * 参数1：url地址
     * 参数2：参数
     * 参数3：回调函数
     */
    public void get(String url, Map<String, String> params, NetOnListener listener) {
        mListener = listener;

        HttpUtils utils = new HttpUtils();


        //若提交参数不为空,拼接字符串到url上
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            sb.deleteCharAt(sb.length() - 1);

            url = url + sb.toString();
        }

        utils.send(HttpMethod.GET, url, new RequestCallBack<T>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(ResponseInfo<T> arg0) {
                mListener.success(arg0.result);
            }
        });

    }

    //POST请求
    public void post(String url, Map<String, String> values, NetOnListener listener) {
        mListener = listener;

        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        //若传入的参数为空,则不返回数据
        if (values == null && values.size() == 0) {
            return;
        }
        //遍历保存参数的Map
        for (Map.Entry<String, String> item : values.entrySet()) {

            params.addBodyParameter(item.getKey(), item.getValue());
        }


        utils.send(HttpMethod.POST, url, params, new RequestCallBack<T>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(ResponseInfo<T> arg0) {

                mListener.success(arg0.result);
            }
        });
    }

}
