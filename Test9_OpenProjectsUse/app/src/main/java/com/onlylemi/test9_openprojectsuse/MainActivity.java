package com.onlylemi.test9_openprojectsuse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        Log.i(TAG, "onCreate: " + Thread.currentThread().getId());

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent event) {
        String msg = event.message;
        Log.i(TAG, "onEvent: " + Thread.currentThread().getId() + " - " + msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        String msg = event.message;
        Log.i(TAG, "onEventMainThread: " + Thread.currentThread().getId() + " - " + msg);
//        textView.setText(msg);
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(MessageEvent event) {
        String msg = event.message;
        Log.i(TAG, "onEventBackgroundThread: " + Thread.currentThread().getId() + " - " + msg);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(MessageEvent event) {
        String msg = event.message;
        Log.i(TAG, "onEventAsync: " + Thread.currentThread().getId() + " - " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
