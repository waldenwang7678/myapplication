package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class ColorRing extends Activity {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 渐变色环画笔，抗锯齿
    private final int[] mColors = new int[] { 0xffff0000, 0xffffff00, 0xff00ff00,
            0xff00ffff,0xff0000ff,0xffff00ff };// 渐变色环颜色
    private int CENTER_X=100;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {


        Canvas canvas=new Canvas();
        Shader s = new SweepGradient(0, 0, mColors, null);
        mPaint.setShader(s);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);

        float r = CENTER_X - mPaint.getStrokeWidth() * 0.5f;
        canvas.save() ;
        canvas.translate(CENTER_X, CENTER_X);// 移动中心
        canvas.rotate(150);
        canvas.drawOval(new RectF(-r, -r, r, r), mPaint);// 画出色环和中心园
        canvas.restore();
    }
}
