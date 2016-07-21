package com.onlylemi.test10_viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private List<Entity> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (viewPager != null) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
            sendEmptyMessageDelayed(0, 2000);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 5; i++) {
            list.add(new Entity(R.mipmap.ic_launcher, null, "ViewPager is good!!! " + i));
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);

        adapter.bindList(list);
        adapter.notifyDataSetChanged();

        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        handler.sendEmptyMessageDelayed(0, 2000);
    }

}
