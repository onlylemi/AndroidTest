package com.onlylemi.test_sildingfinish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * 滑动返回
 *
 * @author: onlylemi
 * @time: 2016-07-22 20:04
 */
public class SlidingFinishActivity extends AppCompatActivity {

    private static final String TAG = SlidingFinishActivity.class.getSimpleName();

    private View decorView;

    private float startTouchX;
    private int screenW;

    private boolean isSliding = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decorView = getWindow().getDecorView();
        decorView.setBackgroundColor(Color.parseColor("#fff9f9f9"));

        screenW = AppUtils.screeWidth(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() == startTouchX) {
                isSliding = false;
            }
        }
        if (isSliding) {
            return onTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startTouchX = event.getX();
            if (startTouchX < 50) {
                // 判断为侧滑退出事件
                isSliding = true;
            }
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: x=" + event.getX());

        if (isSliding) {
            float moveDistX = event.getX() - startTouchX;

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE: {
                    // 随着手指移动
                    if (moveDistX > 0) {
                        decorView.setX(moveDistX);
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    // 滑动屏幕一半时处理
                    if (moveDistX > screenW / 2) {
                        finish(moveDistX);
                    } else {
                        ObjectAnimator.ofFloat(decorView, "x", 0).start();
                    }
                    isSliding = false;
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 退出的动画
     *
     * @param dist
     */
    public void finish(float dist) {
        ValueAnimator animator = ValueAnimator.ofFloat(dist, screenW);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                decorView.setX(x);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
    }
}
