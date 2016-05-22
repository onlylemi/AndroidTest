package com.onlylemi.test1_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * SecondActivity
 *
 * @author: onlylemi
 * @time: 2016-05-22 16:06
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    private MyThread thread;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("SecondActivity");
        setContentView(textView);

        thread = new MyThread();
        thread.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG + " UI:", Thread.currentThread().getName());

                thread.handler1.sendEmptyMessageDelayed(1, 1000);
            }
        };
        handler.sendEmptyMessage(1);
    }

    class MyThread extends Thread {

        public Handler handler1;
        public Looper looper;

        @Override
        public void run() {
            Looper.prepare();
            looper = Looper.myLooper();

            handler1 = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG + " thread:", Thread.currentThread().getName());

                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            };
            Looper.loop();
            looper.quit();
        }
    }

}
