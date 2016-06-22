package com.example.administrator.myapplication01.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.security.Policy;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class TActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TestScrollerActivity";
    private Scroller scroller;
    @Override
    public void onCreate(Bundle savedInstanceState/*, PersistableBundle persistentState*/) {
      super.onCreate(savedInstanceState/*, persistentState*/);
        scroller=new Scroller(this,new DecelerateInterpolator(2.0F));
        LinearLayout.LayoutParams p0=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        p.weight=1;

        LinearLayout lay0=new LinearLayout(this);
        lay0.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(lay0, p0);
        MyLinearLayout lay1=new MyLinearLayout(this);
        MyLinearLayout lay2=new MyLinearLayout(this);
        lay1.setBackgroundColor(Color.GRAY);
        lay2.setBackgroundColor(Color.parseColor("#ceffc2"));

        lay0.addView(lay1,p);
        lay0.addView(lay2,p);

        Button b1=new Button(this);
        Button b2=new Button(this);
        b1.setText("wow");
        b2.setText("craft");

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        lay1.addView(b1);
        lay2.addView(b2);
        //必须设置
        lay0.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        lay1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        lay2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

       setContentView(lay0,p0);
}

    @Override
    public void onClick(View v) {
        scroller.startScroll(0, 0, -200, 0, 500);
    }

    class MyLinearLayout extends LinearLayout
    {
        public MyLinearLayout(Context ctx) {
            super(ctx);
        }
        public MyLinearLayout(Context context, AttributeSet attrs ) {
            super(context, attrs);
        }

        @Override
        /**
         * Called by a parent to request that a child update its values for mScrollX
         * and mScrollY if necessary. This will typically be done if the child is
         * animating a scroll using a {@link android.widget.Scroller Scroller}
         * object.
         */
        public void computeScroll()
        {
            Log.d("TAG", this.toString() + " computeScroll-----------");
            System.out.println(this.toString() + " computeScroll-----------");
            if (scroller.computeScrollOffset())//如果mScroller没有调用startScroll，这里将会返回false。
            {
                //因为调用computeScroll函数的是MyLinearLayout实例，
                //所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
                scrollTo(scroller.getCurrX(), 0);
                Log.d("TAG", "getCurrX = " +  scroller.getCurrX());
                System.out.println("getCurrX = " +  scroller.getCurrX());
                postInvalidate();
            }
        }
    }
}
