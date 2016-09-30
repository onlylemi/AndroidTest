package com.onlylemi.test11_aidl.test2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.onlylemi.test11_aidl.IBookManager;

import java.util.List;

/**
 * BookService
 *
 * @author qijianbin
 * @time 2016-9-26 09:07
 */
public class BookService extends Service {

    private IBinder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            return null;
        }

        @Override
        public Book getBook() throws RemoteException {
            return null;
        }

        @Override
        public int getBooksCount() throws RemoteException {
            return 0;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
