package com.onlylemi.screenobserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ScreenObserver
 *
 * @author qijianbin
 * @time 2016-10-12 15:00
 */
public class ScreenObserver {

    private Context mContext;
    private BroadcastReceiver mReceiver;
    private ScreenStateListener mListener;
    private Method method;

    public ScreenObserver(Context context) {
        mContext = context;
        mReceiver = new ScreenBroadcastReceiver();

        try {
            method = PowerManager.class.getMethod("isScreenOn", new Class[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public class ScreenBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mListener == null) {
                return;
            }

            String action = intent.getAction();
            Log.i("ScreenObserver", "onReceive: " + action);
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                mListener.onScreenOn();
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                mListener.onScreenOff();
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                mListener.onUserPresent();
            }
        }
    }

    public boolean isSceenOn(PowerManager manager){
        Boolean state = false;
        try {
            state = (Boolean) method.invoke(manager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            state = false;
        }
        return state;
    }

    public void registerListener(ScreenStateListener listener) {
        mListener = listener;

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON); // 开屏
        filter.addAction(Intent.ACTION_SCREEN_OFF); // 关屏
        filter.addAction(Intent.ACTION_USER_PRESENT); // 解锁
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS); // home
        mContext.registerReceiver(mReceiver, filter);
    }

    public void unregisterListener() {
        mListener = null;
        mContext.unregisterReceiver(mReceiver);
    }

    public interface  ScreenStateListener {
        void onScreenOn();
        void onScreenOff();
        void onUserPresent();
    }
}
