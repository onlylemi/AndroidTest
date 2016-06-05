package com.onlylemi.test2_asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    private MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 显示下载后的图片
        imageView = (ImageView) findViewById(R.id.inage_view);
        // 显示图片下载的进度
        textView = (TextView) findViewById(R.id.text_view);

        String urlStr = "http://ww2.sinaimg.cn/large/610dc034gw1f4hvgpjjapj20ia0ur0vr.jpg";
        asyncTask = new MyAsyncTask(imageView, textView);
        asyncTask.execute(urlStr);

    }
}
