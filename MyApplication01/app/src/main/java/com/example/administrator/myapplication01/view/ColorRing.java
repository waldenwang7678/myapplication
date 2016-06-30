package com.example.administrator.myapplication01.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication01.R;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class ColorRing extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 渐变色环画笔，抗锯齿
    private final int[] mColors = new int[]{0xffff0000, 0xffffff00, 0xff00ff00, 0xff00ffff, 0xff0000ff, 0xffff00ff};// 渐变色环颜色

    private Paint paintGap1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintGap2 = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint paintMiddleCircle = new Paint(Paint.ANTI_ALIAS_FLAG);//中间圆
    private Paint paintInnerCircle = new Paint(Paint.ANTI_ALIAS_FLAG);//内圆

    private Paint paintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint paintMiddleArc = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int width;
    private int height;

    private int CENTER_X;//中心点x
    private int CENTER_Y;//中心点x

    private float totalRotateAngle = 0f;   //指示图片旋转角度
    private float currentRotateAngle = 0f;
    /**
     * 单位时间内旋转角度
     */
    private float rotateAngle = 0f;
    private boolean isSetReferValue = false;


    private static final String[] text = {"950", "极好", "700", "优秀", "650", "良好", "600", "中等", "550", "较差", "350", "很差", "150"};

    private Bitmap bitmapLocation;
    private int bitmapWidth, bitmapHeight;

    /**
     * 构造函数
     *
     * @param context
     */
    public ColorRing(Context context) {
        super(context);
        init();
    }

    public ColorRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Shader s = new SweepGradient(0, 0, mColors, null);
        mPaint.setShader(s);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);

        paintGap1.setColor(Color.WHITE);
        paintGap2.setColor(Color.WHITE);
        paintGap1.setStrokeWidth(2);
        paintGap2.setStrokeWidth(4);

        paintMiddleCircle.setColor(Color.GRAY);
        paintInnerCircle.setColor(Color.GRAY);
        paintMiddleCircle.setStrokeWidth(4);
        paintInnerCircle.setStrokeWidth(4);
        paintMiddleCircle.setStyle(Paint.Style.STROKE);
        paintInnerCircle.setStyle(Paint.Style.STROKE);

        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        paintInnerCircle.setPathEffect(effects);

        paintBg.setColor(Color.WHITE);

        paintText.setColor(Color.GRAY);
        paintText.setTextSize(20);

        paintMiddleArc.setColor(0xff00d4af);
        paintMiddleArc.setStrokeWidth(6);
        paintMiddleArc.setStyle(Paint.Style.STROKE);

        bitmapLocation = BitmapFactory.decodeResource(getResources(), R.drawable.location1_03);
        bitmapWidth = bitmapLocation.getWidth();
        bitmapHeight = bitmapLocation.getHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //c测量
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        width = MeasureSpec.getSize(widthMeasureSpec);   //宽测测量大小
        height = (int) ((Math.tan(Math.PI / 6) + 1) * width / 2);//高度与宽度正相关 ,将圆环限制为圆弧
        CENTER_X = CENTER_Y = width / 2;//中心点
        setMeasuredDimension(width, height);//设置尺寸


//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthSize;
//        } else {
//             int desired = (int) (getPaddingLeft() + widthSize + getPaddingRight());
//            width = desired;
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightSize;
//        } else {
//             int desired = (int) (getPaddingTop() + heightSize + getPaddingBottom());
//            height = desired;
//        }
//        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.setDrawFilter(pfd);//抗锯齿
//        获取“空心”的边框的宽度
        float r = CENTER_X - mPaint.getStrokeWidth() * 0.5f;

        canvas.save();

        // 移动中心
        canvas.translate(CENTER_X, CENTER_X);
        // 画出色环和中心园
        canvas.rotate(150);
        //画椭圆
        canvas.drawOval(new RectF(-r, -r, r, r), mPaint);

        canvas.restore();
        int a = (int) (2 * CENTER_X - mPaint.getStrokeWidth());
        //在变色线上绘制刻度  在右边开始划线,不断旋转圆环,在同一位置划
        for (int i = 0; i <= 60; i++) {
            canvas.save();
            canvas.rotate(-(-30 + 4 * i), CENTER_X, CENTER_X);
            if (i % 10 == 0) {
                canvas.drawLine(a, CENTER_X, 2 * CENTER_X, CENTER_X, paintGap2);
            } else {
                canvas.drawLine(a, CENTER_X, 2 * CENTER_X, CENTER_X, paintGap1);
            }
            canvas.restore();
        }

        canvas.save();
        canvas.translate(CENTER_X, CENTER_X);   //画笔起始点移动到中心点
        //  X,Y,半径,画笔
        canvas.drawCircle(0, 0, CENTER_X * 5 / 8, paintInnerCircle);
        canvas.drawCircle(0, 0, CENTER_X * 3 / 4, paintMiddleCircle);

        canvas.restore();//画笔起始点会重置到 0, 0
        //画个三角形, 三角形面积能够覆盖之前的视图
        Path path = new Path();
        path.moveTo(CENTER_X, CENTER_X);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.lineTo(CENTER_X, CENTER_X);
        path.close();
        canvas.drawPath(path, paintBg);
        //绘制刻度标识 , 同绘制刻度原理一样(旋转画板)
        for (int i = 0; i <= 12; i++) {
            canvas.save();
            canvas.rotate(-(-30 + 20 * i - 90), CENTER_X, CENTER_X);
            canvas.drawText(text[i], CENTER_X - 20, CENTER_X * 3 / 16, paintText);
            canvas.restore();
        }
        if (isSetReferValue) {  //初始化时为false

            float r1 = CENTER_X * 6 / 8;
            canvas.save();
            canvas.translate(CENTER_X, CENTER_X);
            //  弧线  参数:区域,起始角,扫描角(顺时针),是否封闭(true 椭圆封闭,false 弧线),画笔
            canvas.drawArc(new RectF(-r1, -r1, r1, r1), -210, currentRotateAngle, false, paintMiddleArc);
            canvas.rotate(-30 + currentRotateAngle);

            Matrix matrix = new Matrix();
            matrix.preTranslate(-r1 - bitmapWidth * 3 / 8, -bitmapHeight / 2);
            //图形 , 矩阵 , 画笔
            canvas.drawBitmap(bitmapLocation, matrix, paintBitmap);
            canvas.restore();

        }
        super.onDraw(canvas);
    }


    public static interface RotateListener {
        public void rotate(float sweepAngle, float value);
    }

    /**
     * 设置参考值计算旋转角度
     *
     * @param referValue
     */
    public void setReferValue(int referValue, final RotateListener rotateListener) {

        isSetReferValue = !isSetReferValue;

        if (referValue <= 150) {
            totalRotateAngle = 0f;    //指示图片旋转角度
        } else if (referValue <= 550) {
            totalRotateAngle = (referValue - 150) * 80 / 400f;
        } else if (referValue <= 700) {
            totalRotateAngle = (referValue - 550) * 120 / 150f + 80;
        } else if (referValue <= 950) {
            totalRotateAngle = (referValue - 700) * 40 / 250f + 200;
        } else {
            totalRotateAngle = 210f;
        }

        rotateAngle = totalRotateAngle / 60;

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean rotating = true;
                float value = 350;

                while (rotating) {
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentRotateAngle += rotateAngle;
                    if (currentRotateAngle >= totalRotateAngle) {
                        currentRotateAngle = totalRotateAngle;
                        rotating = false;
                    }

                    if (null != rotateListener) {
                        if (currentRotateAngle <= 80) {
                            value = 350 + (currentRotateAngle / 80) * 400;
                        } else if (currentRotateAngle <= 200) {
                            value = 550 + ((currentRotateAngle - 80) / 120) * 150;
                        } else {
                            value = 700 + ((currentRotateAngle - 200) / 40) * 250;
                        }
                        rotateListener.rotate(currentRotateAngle, value);
                    }

                    postInvalidate();  //在子线程中更行View   (循环时候不停在更新视图)
                }
            }
        }).start();

    }
}
