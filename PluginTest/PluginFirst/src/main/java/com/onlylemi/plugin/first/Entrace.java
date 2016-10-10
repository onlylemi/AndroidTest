package com.onlylemi.plugin.first;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.onlylemi.plugin.sdk.IPlugin;

/**
 * Entrace
 *
 * @author qijianbin
 * @time 2016-10-9 14:24
 */
public class Entrace implements IPlugin {

    private static final String TAG = Entrace.class.getSimpleName();

    @Override
    public void execute() {
        Log.i(TAG, "execute: 插件正在执行！");
    }

    @Override
    public void execute(Activity activity) {
        Log.i(TAG, "execute: 插件已执行，正在打开 Activity");
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
}