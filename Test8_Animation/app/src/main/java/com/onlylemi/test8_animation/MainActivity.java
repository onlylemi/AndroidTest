package com.onlylemi.test8_animation;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button;
    private ListView listView;
    private String[] strs = {"item", "item", "item", "item", "item", "item", "item",
            "item", "item", "item", "item", "item", "item", "item", "item", "item", "item",
            "item", "item", "item", "item", "item"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view 动画
//                startViewAnimation(button);

                // Frame 动画
//                startFrameAnimation(button);

                // 属性动画
                startPropertyAnimation(button);


                // activity 跳转动画
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
//                // 必须在startActivity之后调用
//                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, strs);
        listView.setAdapter(arrayAdapter);

        // listview item 动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        listView.setLayoutAnimation(controller);

    }

    /**
     * view 动画
     *
     * @param view
     */
    private void startViewAnimation(final View view) {
        // 平移
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 200, 200);
        translateAnimation.setDuration(1000);
        // 移动之后保留状态
        translateAnimation.setFillAfter(true);

        // 旋转
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        RotateAnimation rotateAnimation = new RotateAnimation(360, 0);
        rotateAnimation.setDuration(1000);

        // 缩放
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, view.getWidth() / 2,
                view.getHeight() / 2);
        scaleAnimation.setDuration(1000);
        // 设置重复次数
        scaleAnimation.setRepeatCount(5);
        // 重复模式，正/逆
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                scaleAnimation.setRepeatMode(Animation.RESTART);
            }
        });

        // 透明
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(1000);


        //view.startAnimation(animation1);

        view.startAnimation(animation1);
    }

    /**
     * 帧动画
     *
     * @param view
     */
    private void startFrameAnimation(View view) {
        view.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable drawable = (AnimationDrawable) view.getBackground();
        drawable.start();
    }

    private void startPropertyAnimation(final View view) {
        // 改变一个对象
//        ObjectAnimator.ofFloat(view, "translationY", 500).start();
//
//        // 实现颜色渐变
//        ValueAnimator animator = ObjectAnimator.ofInt(view, "textColor", R.color
//                .colorAccent, R.color.colorPrimary);
//        animator.setDuration(3000);
//        animator.setEvaluator(new ArgbEvaluator());
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.start();

        // 动画集合
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(
//                ObjectAnimator.ofFloat(view, "translationY", 500),
//                ObjectAnimator.ofFloat(view, "translationX", 100)
//        );
//        animatorSet.setDuration(2000).start();


        // 对于view的特殊属性width、height进行变化，view内部没有setWidth方法
        // 采用ViewDecorator 对view进行装饰
        ObjectAnimator.ofInt(ViewWrapper.decorator(view), "width", 800).start();

        // 或 改变top、right
//        int width = view.getWidth();
//        PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("left", view.getLeft() - (800 -
//                width) / 2);
//        PropertyValuesHolder holder2 = PropertyValuesHolder.ofInt("right", view.getRight() + (800
//                - width) / 2);
//        ObjectAnimator.ofPropertyValuesHolder(view, holder2, holder1).start();

//        ObjectAnimator.ofPropertyValuesHolder(ViewWrapper.decorator(view),
//                PropertyValuesHolder.ofInt("width", 800),
//                PropertyValuesHolder.ofInt("height", 500)
//        ).start();

        // 通过 ValueAnimator 的 addUpdateListener 动态改变
        ValueAnimator valueAnimator = ValueAnimator.ofInt(view.getWidth(), 800);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int value = (Integer) animator.getAnimatedValue();
                view.getLayoutParams().width = value;
                view.requestLayout();
            }
        });
        valueAnimator.start();

//        performAnimate(view, view.getWidth(), 800);
    }

    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, 100);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            // 持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                Log.d(TAG, "current value: " + currentValue);

                // 获得当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = animator.getAnimatedFraction();
                // 直接调用整型估值器通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });

        valueAnimator.start();
    }

}
