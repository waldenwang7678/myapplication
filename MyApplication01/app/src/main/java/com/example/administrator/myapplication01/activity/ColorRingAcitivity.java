package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.view.ColorRing;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class ColorRingAcitivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorring);
        final ColorRing iView = (ColorRing) findViewById(R.id.mytv);
        final TextView txtView = (TextView) findViewById(R.id.crtv);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                iView.setReferValue(682, new ColorRing.RotateListener() {
                    @Override
                    public void rotate(float sweepAngle, final float value) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtView.setText(Math.round(value) + "");
                            }
                        });
                    }
                });
            }
        }, 1000);

    }
}



