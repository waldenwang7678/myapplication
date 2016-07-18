package com.example.administrator.myapplication01.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.view.MyCircleImageView;

public class MyCircleActivity extends AppCompatActivity {

    private MyCircleImageView iv;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circe);

        this.iv = (MyCircleImageView) this.findViewById(R.id.my_cycle_image);
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
        //iv.setImageBitmap(baseBitmap);

        //iv.setBackgroundResource(R.drawable.bg);
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
                        //  iv.setImageBitmap(baseBitmap);
                        break;
                }
                return true;
            }
        });
    }
}

