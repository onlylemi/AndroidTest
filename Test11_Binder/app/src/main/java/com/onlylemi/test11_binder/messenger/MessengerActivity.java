package com.onlylemi.test11_binder.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onlylemi.test11_binder.R;

public class MessengerActivity extends AppCompatActivity {

    private Messenger client = new Messenger(new ClientHandler());

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain(null,
                    MessengerService.ServerHandler.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello, server");
            message.setData(bundle);
            message.replyTo = client;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    static class ClientHandler extends Handler {

        private static final String TAG = ClientHandler.class.getSimpleName();

        public static final int MSG_FROM_SERVER = 1;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_SERVER:
                    Log.i(TAG, "MSG_FROM_SERVER: " + msg.getData().getString("msg"));

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
