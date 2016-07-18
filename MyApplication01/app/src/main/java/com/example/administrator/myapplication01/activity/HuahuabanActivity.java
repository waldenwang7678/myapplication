package com.example.administrator.myapplication01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.view.MyView;

public class HuahuabanActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    private MyView handWrite = null;
    private Button clear = null;
    private Button save = null;
    private ImageView saveImage = null;
    private Button bt_hh;
    private Button bt_hh2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huahuaban);

        handWrite = (MyView) findViewById(R.id.handwriteview);
        save = (Button) findViewById(R.id.save);
        saveImage = (ImageView) findViewById(R.id.saveimage);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new clearListener());
        save.setOnClickListener(new saveListener());
        handerl();

        bt_hh = (Button) findViewById(R.id.jump);
        bt_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuahuabanActivity.this, hhActivity.class);
                startActivity(intent);
            }
        });
        bt_hh2 = (Button)findViewById(R.id.jump1);
        bt_hh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuahuabanActivity.this, MyCircleActivity.class);
                startActivity(intent);
            }
        });
    }

    private class clearListener implements View.OnClickListener {

        public void onClick(View v) {
            handWrite.clear();
        }
    }

    public static Handler hh;

    public void handerl() {
        hh = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                if (what == 3) {
                    saveImage.setVisibility(View.VISIBLE);
                    saveImage.setImageBitmap(handWrite.saveImage());
                    handWrite.setImge();
                }
                super.handleMessage(msg);
            }
        };
    }

    private class saveListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Message msg = new Message();
            msg = Message.obtain(handWrite.handler1, 2);
            handWrite.handler1.sendMessage(msg);

            if (handWrite.saveImage() != null) {
                Log.i("RG", "111111111111111111111");

            } else {
                saveImage.setVisibility(View.GONE);
            }
        }

    }
}
