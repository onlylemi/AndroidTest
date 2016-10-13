package com.onlylemi.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * ShowNotificationBroadcast
 *
 * @author qijianbin
 * @time 2016-10-13 10:58
 */
public class ShowNotificationBroadcast extends BroadcastReceiver {

    private static final String TAG = ShowNotificationBroadcast.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Notification notification = intent.getParcelableExtra("notification");
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(new Random().nextInt(100), notification);
    }
}
