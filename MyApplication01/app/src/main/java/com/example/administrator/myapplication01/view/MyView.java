package com.example.administrator.myapplication01.view;

/**
 * Created by Administrator on 2016/7/11.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.activity.HuahuabanActivity;

public class MyView extends View {
    private Paint paint = null;
    private Bitmap originalBitmap = null;
    private Bitmap new1Bitmap = null;
    private Bitmap new2Bitmap = null;
    private float clickX = 0, clickY = 0;
    private float startX = 0, startY = 0;
    private boolean isMove = true;
    private boolean isClear = false;
    private int color = Color.GREEN;
    private float strokeWidth = 2.0f;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a1).copy(Bitmap.Config.RGB_565, true);
       // originalBitmap = Bitmap.createBitmap(480, 640, Bitmap.Config.RGB_565v);

        new1Bitmap = Bitmap.createBitmap(originalBitmap);
        setDrawingCacheEnabled(true);
        Log.i("RG", "new1Bit map--->>>" + new1Bitmap);
    }



    public void clear() {
        isClear = true;
        new2Bitmap = Bitmap.createBitmap(originalBitmap);
        invalidate();
    }

    Bitmap saveImage = null;

    public Bitmap saveImage() {
        if (saveImage == null) {
            return null;
        }
        return saveImage;
    }

    public void setImge() {
        saveImage = null;
    }

    public void setstyle(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Handler handler1;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(HandWriting(new1Bitmap), 0, 0, null);
        handler1 = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                Log.i("RG", "--------------------");
                int what = msg.what;
                if (what == 2) {
                    saveImage = Bitmap.createBitmap(HandWriting(new1Bitmap));
                    Message msg1 = new Message();
                    msg1 = Message.obtain(HuahuabanActivity.hh, 3);
                    HuahuabanActivity.hh.sendMessage(msg1);
                }
                super.handleMessage(msg);
            }

        };

    }

    @SuppressLint("HandlerLeak")
    Handler handler;

    public Bitmap HandWriting(Bitmap originalBitmap) {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                int what = msg.what;
                if (what == 1) {
                    startX = clickX;
                    startY = clickY;
                }
                super.handleMessage(msg);
            }

        };
        Canvas canvas = null;

        if (isClear) {
            canvas = new Canvas(new2Bitmap);
            Log.i("RG", "canvas ");
        } else {
            canvas = new Canvas(originalBitmap);
        }
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        Log.i("RG", "startX-->>>>" + startX);
        Log.i("RG", "startY-->>>>" + startY);
        if (isMove) {
            canvas.drawLine(startX, startY, clickX, clickY, paint);
        }

        if (isClear) {
            return new2Bitmap;
        }

        return originalBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Message msg = new Message();
        msg = Message.obtain(handler, 1);
        handler.sendMessage(msg);
        clickX = event.getX();
        clickY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            isMove = false;
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            isMove = true;
            invalidate();
            return true;
        }

        return super.onTouchEvent(event);
    }

}


