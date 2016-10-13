package com.onlylemi.notificationtest;

import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            showNotification();
        }
    };

    private AlarmManager alarmManager;
    private PendingIntent pi;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                showNotification();
                break;
            case R.id.button1:
                checkNotificationState();
                break;
            case R.id.button2:
                showByHandler();
                break;
            case R.id.button22:
                removeTaskByHandler();
                break;
            case R.id.button3:
                showByAlarmManager();
                break;
            case R.id.button33:
                removeTaskByAlarmManager();
                break;
            case R.id.button4:
                showByTimer();
                break;
            case R.id.button44:
                removeTaskByTimer();
                break;
        }
    }

    public Notification getNotification() {
        //        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//        stackBuilder.addParentStack(ResultActivity.class);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("通知 Title" + new Random().nextInt(100))
                .setContentText("通知消息内容")
                .setContentIntent(pendingIntent)
                .setGroup("mmmmm")
                .setWhen(System.currentTimeMillis() + 60 * 1000)
//                        .setFullScreenIntent(pendingIntent, false)
                .setAutoCancel(true);

        Notification notification = mBuilder.build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
//        notification.flags |= Notification.FLAG_INSISTENT;

        return notification;
    }

    public void showNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                mNotificationManager.notify(new Random().nextInt(100), mBuilder.build());
        mNotificationManager.notify(new Random().nextInt(100), getNotification());
    }

    public void checkNotificationState() {
        Integer op = JavaCalls.getStaticField(AppOpsManager.class, "OP_POST_NOTIFICATION");
        if (op == null) {
            Log.i(TAG, "onClick: unknown");
        }
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), 0);
            AppOpsManager mAppOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            Integer mode = JavaCalls.callMethod(mAppOps, "checkOpNoThrow", op, info.uid, getPackageName());

            if (mode != null && mode == 00) {
                Log.i(TAG, "onClick: allowed");
                Toast.makeText(MainActivity.this, "allowed", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "onClick: not_allowed");
                Toast.makeText(MainActivity.this, "not_allowed", Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showByHandler() {
        Log.i(TAG, "showByHandler: ");

        int what = 0;
        mHandler.sendEmptyMessageDelayed(what, 5 * 1000);
    }

    public void removeTaskByHandler() {
        mHandler.removeMessages(0);
    }

    public void showByAlarmManager() {
        Log.i(TAG, "showByAlarmManager: ");

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), ShowNotificationBroadcast.class);
        intent.putExtra("notification", getNotification());
        pi = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5 * 1000, pi);
    }

    public void removeTaskByAlarmManager() {
        if (pi == null) return;

        alarmManager.cancel(pi);
    }

    public void showByTimer() {
        Log.i(TAG, "showByTimer: ");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "run: " + Thread.currentThread().getName());
                showNotification();
            }
        }, 5 * 1000);
    }

    public void removeTaskByTimer() {
        if (timer == null) return;

        timer.cancel();
    }
}
