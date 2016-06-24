package com.example.administrator.myapplication01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.administrator.myapplication01.R;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class DotProgressBar extends LinearLayout {
    int dotMargin = 50;

    View root;
    View leftDot;
    View middleDot;
    View rightDot;
    Animation leftAnim;
    Animation middleAnim;
    Animation rightAnim;

    int count = 0;

    public DotProgressBar(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        root = inflater.inflate(R.layout.progress_bar, this, true);
    }

    public DotProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        root = inflater.inflate(R.layout.progress_bar, this, true);
    }

    public DotProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        root = inflater.inflate(R.layout.progress_bar, this, true);
    }

    private void init() {
        leftDot = root.findViewById(R.id.left);
        middleDot = root.findViewById(R.id.middle);
        rightDot = root.findViewById(R.id.right);
        leftAnim = createAnimation();
        middleAnim = createAnimation();
        rightAnim = createAnimation();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }



    private Animation createAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.dot_anim);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation anim) {
                count++;
                if (count % 4 == 0) {
                    leftDot.startAnimation(leftAnim);
                } else if ((count - 2) % 4 == 0) {
                    rightDot.startAnimation(rightAnim);
                } else {
                    middleDot.startAnimation(middleAnim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
        return animation;
    }

    public void startAnimation() {
        leftDot.startAnimation(leftAnim);

    }
}
