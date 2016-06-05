package com.onlylemi.test1_handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;
    private Thread thread;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            textView.setText("Text View2");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);

        thread = new Thread(this);
        thread.start();

        Log.i(TAG, "OnCreate");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            // 更新UI
            // 方法1
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    textView.setText("Text View1");
//                }
//            });

            // 方法2
            handler.sendEmptyMessage(1);
            Message msg = Message.obtain();

            // 方法3
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    textView.setText("Text View3");
//                }
//            });

            // 方法4
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Text View4");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
