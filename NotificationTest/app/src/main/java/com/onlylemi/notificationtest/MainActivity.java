package com.onlylemi.notificationtest;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton1 = (Button) findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//                stackBuilder.addParentStack(ResultActivity.class);
//                stackBuilder.addNextIntent(intent);
//                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


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
                notification.flags |= Notification.FLAG_INSISTENT;

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                mNotificationManager.notify(new Random().nextInt(100), mBuilder.build());
                mNotificationManager.notify(new Random().nextInt(100), notification);
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    }else {
                        Log.i(TAG, "onClick: not_allowed");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
