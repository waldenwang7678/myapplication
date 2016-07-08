package com.example.administrator.myapplication01.minimusic;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;



public class ProgressTextUtils {
    /**
     *  时间转换
     * @param time
     * @return
     */
    public static String getProgressText(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        double minute = calendar.get(Calendar.MINUTE);
        double second = calendar.get(Calendar.SECOND);

        DecimalFormat format = new DecimalFormat("00");
        return format.format(minute) + ":" + format.format(second);
    }
}
