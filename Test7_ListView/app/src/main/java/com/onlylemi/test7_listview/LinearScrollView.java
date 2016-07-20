package com.onlylemi.test7_listview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * LinearScrollView
 *
 * @author: onlylemi
 * @time: 2016-07-20 19:26
 */
public class LinearScrollView extends ScrollView {

    private static final String TAG = LinearScrollView.class.getSimpleName();

    private int startTouchX;
    private int startTouchY;

    private int scrollDist = 0;

    public LinearScrollView(Context context) {
        super(context);
    }

    public LinearScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;

        // linear_layout
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        // list_view
        View view = viewGroup.getChildAt(0);
        ListView view1 = (ListView) viewGroup.getChildAt(viewGroup.getChildCount() - 1);
        Log.i(TAG, "onInterceptTouchEvent: getScrollY:" + getScrollY());
        Log.i(TAG, "onInterceptTouchEvent: getHeight:" + view.getHeight());
        Log.i(TAG, "onInterceptTouchEvent: getTop:" + view1.getTop());
        Log.i(TAG, "onInterceptTouchEvent: getHeight:" + view1.getHeight());

        if (getScrollY() >= 603) {
            intercepted = false;
        } else {
            intercepted = super.onInterceptTouchEvent(ev);
        }

        Log.i(TAG, "onInterceptTouchEvent: intercepted=" + intercepted);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onInterceptTouchEvent: getScrollY1:" + getScrollY());

        super.onDraw(canvas);
    }
}
