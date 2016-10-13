package com.onlylemi.androidprocess;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i(TAG, "handleMessage: app=" + getForegroundPackage(getApplicationContext()));
            Log.i(TAG, "handleMessage: is=" + isAppForeground(getApplicationContext()));

            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    public String getForegroundPackage(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return getForegroundPackage1(context);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1){
            return getForegroundPackage2(context);
        } else {
            return getForegroundPackage3(context);
        }
    }


    /**
     * 在 API 21 已被遗弃，不能使用
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public String getForegroundPackage1(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos == null || runningTaskInfos.size() == 0
                || runningTaskInfos.get(0) == null) {
            return null;
        }

        return runningTaskInfos.get(0).topActivity.getPackageName();
    }

    /**
     * 在 API 22 开始仅可以获取自己的应用，其他应用位于前台时获取到为 null，但可以通过此方式判断自己的应用是否处于前台
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public String getForegroundPackage2(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos = manager.getRunningAppProcesses();
        if (runningAppProcessInfos == null || runningAppProcessInfos.size() == 0) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo ra : runningAppProcessInfos) {
            if (ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return ra.processName;
            }
        }
        return null;
    }

    /**
     * 判断应用是否处于前台
     *
     * @param context
     * @return
     */
    public boolean isAppForeground(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos = manager.getRunningAppProcesses();
        if (runningAppProcessInfos == null || runningAppProcessInfos.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo ra : runningAppProcessInfos) {
            if (ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && Arrays.asList(ra.pkgList).contains(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * API 22 之后使用
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public String getForegroundPackage3(Context context) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long endTime = System.currentTimeMillis();
        long beginTime = endTime - 10000;

        UsageEvents.Event event = new UsageEvents.Event();
        UsageEvents usageEvents = usageStatsManager.queryEvents(beginTime, endTime);
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                return event.getPackageName();
            }
        }
        return null;
    }

    public class ProcessBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

}
