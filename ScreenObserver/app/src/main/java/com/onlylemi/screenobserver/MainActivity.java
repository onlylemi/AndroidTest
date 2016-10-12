package com.onlylemi.screenobserver;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ScreenObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mObserver = new ScreenObserver(this);

        PowerManager manager = (PowerManager) getSystemService(Activity.POWER_SERVICE);
        Log.i(TAG, "onCreate: "  + mObserver.isSceenOn(manager));
        Log.i(TAG, "onCreate: "  + manager.isScreenOn());

        mObserver.registerListener(new ScreenObserver.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.i(TAG, "onScreenOn: ");
            }

            @Override
            public void onScreenOff() {
                Log.i(TAG, "onScreenOff: ");
            }

            @Override
            public void onUserPresent() {
                Log.i(TAG, "onUserPresent: ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mObserver.unregisterListener();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasFocus);
    }
}
