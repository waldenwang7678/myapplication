package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication01.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class hhActivity extends Activity {
    private ImageView iv;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hh);
        this.iv = (ImageView) this.findViewById(R.id.ivaaa);
        // 创建一张空白图片
        baseBitmap = Bitmap.createBitmap(480, 640, Bitmap.Config.ARGB_8888);
        // 创建一张空白画布
        canvas = new Canvas(baseBitmap);
        // 画布背景为灰色
        canvas.drawColor(Color.GRAY);
        // 创建画笔
        paint = new Paint();
        // 画笔颜色为红色
        paint.setColor(Color.RED);
        // 宽度5个像素
        paint.setStrokeWidth(5);
        // 先将灰色背景画上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        iv.setImageBitmap(baseBitmap);

        iv.setBackgroundResource(R.drawable.bg);
        iv.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int startY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取手按下时的坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取手移动后的坐标
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();
                        // 在开始和结束坐标间画一条线
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        // 实时更新开始坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        iv.setImageBitmap(baseBitmap);
                        break;
                }
                return true;
            }
        });
    }
    public void save(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            OutputStream stream = new FileOutputStream(file);
            baseBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();
            // 模拟一个广播，通知系统sdcard被挂载
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment
                    .getExternalStorageDirectory()));
            sendBroadcast(intent);
            Toast.makeText(this, "保存图片成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "保存图片失败",  Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}