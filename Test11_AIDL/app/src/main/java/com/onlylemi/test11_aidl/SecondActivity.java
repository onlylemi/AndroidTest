package com.onlylemi.test11_aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onlylemi.test11_aidl.test2.Book;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    public static final String PACKAGE_NAME = "com.onlylemi.test11_aidl";
    public static final String SERVICE_NAME = "com.onlylemi.test11_aidl.test2.BookService";

    private Button bindBtn;
    private Button unbindBtn;
    private Button addBookBtn;
    private Button showBooksBtn;
    private TextView booksTv;

    private IBookManager mService = null;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
            mService = IBookManager.Stub.asInterface(iBinder);

            try {
                mService.addBook(new Book(mService.getBooksCount(), "Android"));
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
        setContentView(R.layout.activity_second);

        bindBtn = (Button) findViewById(R.id.bind_btn);
        unbindBtn = (Button) findViewById(R.id.unbind_btn);
        addBookBtn = (Button) findViewById(R.id.add_book_btn);
        showBooksBtn = (Button) findViewById(R.id.show_books_btn);
        booksTv = (TextView) findViewById(R.id.books_tv);

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 通过组件名，启动 service
                intent.setComponent(new ComponentName(PACKAGE_NAME, SERVICE_NAME));
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });
        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(conn);
            }
        });
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mService == null) {
                    Log.i(TAG, "onClick: mService is null");
                    return;
                }
                try {
                    int n = mService.getBooksCount();
                    mService.addBook(new Book(n, "Android " + n));
                    Log.i(TAG, "onClick: " + mService.getBook(n));
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        showBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> books = mService.getBooks();
                    Log.i(TAG, "onClick: " + books);
                    booksTv.setText(books.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null){
            unbindService(conn);
        }
    }
}
