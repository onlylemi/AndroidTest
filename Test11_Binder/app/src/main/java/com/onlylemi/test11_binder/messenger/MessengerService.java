package com.onlylemi.test11_binder.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * MessengerService
 *
 * @author: onlylemi
 * @time: 2016-07-21 18:57
 */
public class MessengerService extends Service {

    private final Messenger server = new Messenger(new ServerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return server.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    static class ServerHandler extends Handler {

        private static final String TAG = ServerHandler.class.getSimpleName();

        public static final int MSG_FROM_CLIENT = 0;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.i(TAG, "MSG_FROM_CLIENT: " + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message message = Message.obtain(null,
                            MessengerActivity.ClientHandler.MSG_FROM_SERVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "nice to meet you, client!");
                    message.replyTo = client;
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
