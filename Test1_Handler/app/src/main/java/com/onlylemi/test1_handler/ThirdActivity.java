package com.onlylemi.test1_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * ThirdActivity
 *
 * @author: onlylemi
 * @time: 2016-05-22 16:41
 */
public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = ThirdActivity.class.getSimpleName();

    private Handler handler;

    private HandlerThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("ThirdActivity");
        setContentView(textView);

        thread = new HandlerThread("Handler Thread");
        thread.start();

        handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "thread: " + Thread.currentThread().getName());
            }
        };

        handler.sendEmptyMessageDelayed(1, 1000);
    }
}
