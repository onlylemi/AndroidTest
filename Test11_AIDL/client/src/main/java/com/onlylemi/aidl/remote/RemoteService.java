package com.onlylemi.aidl.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.onlylemi.aidl.client.IRemoteService;

/**
 * RemoteService
 *
 * @author qijianbin
 * @time 2016-9-23 10:44
 */
public class RemoteService extends Service {

    private static final String TAG = RemoteService.class.getSimpleName();

    private IRemoteService.Stub mBinder = new IRemoteService.Stub() {

        private String msg = "pushService";

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public String getMsg() throws RemoteException {
            return msg;
        }

        @Override
        public void setMsg(String msg) throws RemoteException {
            Log.i(TAG, "setMsg: " + msg);
            this.msg = msg;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return mBinder;
    }
}
