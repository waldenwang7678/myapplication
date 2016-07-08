package com.example.administrator.myapplication01.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class Util {
    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static String getMacAddress(Context context) {
        // 获取mac地址：
        String macAddress = "000000000000";
        try {
            WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
            if (null != info) {
                if (!TextUtils.isEmpty(info.getMacAddress()))
                    macAddress = info.getMacAddress().replace(":", "");
                else
                    return macAddress;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return macAddress;
        }
        return macAddress;
    }

    public static String getPhoneNum(Context context) {
        // Context context = getWindow().getContext();
        String num = "00000000000";
        TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String str = telephonemanage.getDeviceId();
        String sim=telephonemanage.getSimSerialNumber();
        if(sim!=null){
            System.out.println("SimSerialNumber:"+telephonemanage.getSimSerialNumber());
        }else{
            System.out.println("SimSerialNumber:空");
        }

        if (str != null && str.length() != 0) {
            num = str;
        }

        boolean isEmulator = "00000000000".equalsIgnoreCase(telephonemanage.getDeviceId());
        return num;
    }
}
