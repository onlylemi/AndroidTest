package com.onlylemi.test11_aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onlylemi.test11_aidl.test1.RemoteService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button bindBtn;
    private Button unbindBtn;
    private Button setMsgBtn;

    private IRemoteService mService = null;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");

            mService = IRemoteService.Stub.asInterface(iBinder);

            Log.i(TAG, "onServiceConnected: " + Process.myPid());

            try {
                Log.i(TAG, "onServiceConnected: msg=" + mService.getMsg() + " pid=" + mService.getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindBtn = (Button) findViewById(R.id.bind_btn);
        unbindBtn = (Button) findViewById(R.id.unbind_btn);
        setMsgBtn = (Button) findViewById(R.id.setmsg_btn);

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RemoteService.class);
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });
        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(conn);
            }
        });
        setMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mService == null) {
                    return;
                }

                try {
                    mService.setMsg("from client");
                    Log.i(TAG, "onClick: " + mService.getMsg());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
