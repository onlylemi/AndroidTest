package com.onlylemi.test7_listview;

import android.app.Application;
import android.content.Context;

/**
 * App
 *
 * @author: onlylemi
 * @time: 2016-07-09 20:17
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
