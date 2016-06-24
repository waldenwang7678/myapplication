package com.example.administrator.myapplication01.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.view.DotProgressBar;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class MyDialog extends AlertDialog {
    private Context context;

    public MyDialog(Context context, int theme) {
        super(context,theme);
        this.context =context;
    }

    TextView msg;
    DotProgressBar dpb;
    static String message ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        dpb = (DotProgressBar) findViewById(R.id.progress);
        dpb.startAnimation();
        msg = (TextView) findViewById(R.id.message);
        msg.setText(message);
        setCanceledOnTouchOutside(false);
        setTitle(message);

    }

    public static MyDialog show(Context context, String msg) {
        if (context == null || message == null) {
            return null;
        }
        message = msg;
        MyDialog dlg = new MyDialog(context, android.R.style.Theme_Holo_Dialog_NoActionBar);
        dlg.show();
        return dlg;
    }

    public static MyDialog show(Context context, int messageId) {
        if (context == null || messageId <= 0) {
            return null;
        }
        message = context.getString(messageId);
        return show(context, message);
    }

}
