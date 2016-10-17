package com.onlylemi.plugin.first;

import android.os.Bundle;
import android.widget.TextView;

import com.onlylemi.plugin.sdk.BaseActivity;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("这是 SecondActivity");
        setContentView(textView);
//        setContentView(R.layout.activity_second);
    }
}
