package com.onlylemi.test6_qiongyou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.onlylemi.test6_qiongyou.adapter.MyViewPageAdapter;
import com.onlylemi.test6_qiongyou.fragments.Fragment1;
import com.onlylemi.test6_qiongyou.fragments.Fragment2;
import com.onlylemi.test6_qiongyou.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color
                .holo_orange_dark));

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        titleList.add("亚洲");
        titleList.add("欧洲");
        titleList.add("北美洲");

        viewPager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), fragmentList,
                titleList));

    }
}
