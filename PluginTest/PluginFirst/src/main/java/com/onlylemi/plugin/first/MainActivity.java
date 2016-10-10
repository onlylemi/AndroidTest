package com.onlylemi.plugin.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("这是插件 Activity");
        // 插件中 Activity 无法加载 res 资源
//        setContentView(R.layout.activity_main);
        setContentView(textView);
    }
}
