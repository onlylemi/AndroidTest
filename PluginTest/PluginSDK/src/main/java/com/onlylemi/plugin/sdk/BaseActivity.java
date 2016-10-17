package com.onlylemi.plugin.sdk;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * BaseActivity
 *
 * @author qijianbin
 * @time 2016-10-17 17:43
 */
public class BaseActivity extends AppCompatActivity {

    protected Activity proxyActivity;

    public boolean isProxyMode = false;

    public void setActivity(Activity proxyActivity) {
        this.proxyActivity = proxyActivity;
        isProxyMode = true;
    }

    public Activity getActivity() {
        if (isProxyMode) {
            return proxyActivity;
        }
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!isProxyMode) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        if (!isProxyMode) {
            super.onStart();
        }
    }

    @Override
    protected void onRestart() {
        if (!isProxyMode) {
            super.onRestart();
        }
    }

    @Override
    protected void onResume() {
        if (!isProxyMode) {
            super.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!isProxyMode) {
            super.onPause();
        }
    }

    @Override
    protected void onStop() {
        if (!isProxyMode) {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        if (!isProxyMode) {
            super.onDestroy();
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (isProxyMode) {
            proxyActivity.addContentView(view, params);
        } else {
            super.addContentView(view, params);
        }
    }

    @Override
    public void closeContextMenu() {
        if (isProxyMode) {
            proxyActivity.closeContextMenu();
        } else {
            super.closeContextMenu();
        }
    }

    @Override
    public void closeOptionsMenu() {
        if (isProxyMode) {
            proxyActivity.closeOptionsMenu();
        } else {
            super.closeOptionsMenu();
        }
    }

    @Override
    public PendingIntent createPendingResult(int requestCode, Intent data,
                                             int flags) {
        if (isProxyMode) {
            return proxyActivity.createPendingResult(requestCode, data, flags);
        } else {
            return super.createPendingResult(requestCode, data, flags);
        }
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        if (isProxyMode) {
            return proxyActivity.dispatchGenericMotionEvent(ev);
        } else {
            return super.dispatchGenericMotionEvent(ev);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (isProxyMode) {
            return proxyActivity.dispatchKeyEvent(event);
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        if (isProxyMode) {
            return proxyActivity.dispatchKeyShortcutEvent(event);
        } else {
            return super.dispatchKeyShortcutEvent(event);
        }
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (isProxyMode) {
            return proxyActivity.dispatchPopulateAccessibilityEvent(event);
        } else {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isProxyMode) {
            return proxyActivity.dispatchTouchEvent(ev);
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        if (isProxyMode) {
            return proxyActivity.dispatchTrackballEvent(ev);
        } else {
            return super.dispatchTrackballEvent(ev);
        }
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer,
                     String[] args) {
        if (isProxyMode) {
            proxyActivity.dump(prefix, fd, writer, args);
        } else {
            super.dump(prefix, fd, writer, args);
        }
    }

    @Override
    public View findViewById(int id) {
        if (isProxyMode) {
            return proxyActivity.findViewById(id);
        } else {
            return super.findViewById(id);
        }
    }

    @Override
    public void finish() {
        if (isProxyMode) {
            proxyActivity.finish();
        } else {
            super.finish();
        }
    }

    @Override
    public void finishActivity(int requestCode) {
        if (isProxyMode) {
            proxyActivity.finishActivity(requestCode);
        } else {
            super.finishActivity(requestCode);
        }
    }

    @Override
    public void finishActivityFromChild(Activity child, int requestCode) {
        if (isProxyMode) {
            proxyActivity.finishActivityFromChild(child, requestCode);
        } else {
            super.finishActivityFromChild(child, requestCode);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void finishAffinity() {
        if (isProxyMode) {
            proxyActivity.finishAffinity();
        } else {
            super.finishAffinity();
        }
    }

    @Override
    public void finishFromChild(Activity child) {
        if (isProxyMode) {
            proxyActivity.finishFromChild(child);
        } else {
            super.finishFromChild(child);
        }
    }

    @Override
    public ActionBar getActionBar() {
        if (isProxyMode) {
            return proxyActivity.getActionBar();
        } else {
            return super.getActionBar();
        }
    }

    @Override
    public ComponentName getCallingActivity() {
        if (isProxyMode) {
            return proxyActivity.getCallingActivity();
        } else {
            return super.getCallingActivity();
        }
    }

    @Override
    public String getCallingPackage() {
        if (isProxyMode) {
            return proxyActivity.getCallingPackage();
        } else {
            return super.getCallingPackage();
        }
    }

    @Override
    public int getChangingConfigurations() {
        if (isProxyMode) {
            return proxyActivity.getChangingConfigurations();
        } else {
            return super.getChangingConfigurations();
        }
    }

    @Override
    public ComponentName getComponentName() {
        if (isProxyMode) {
            return proxyActivity.getComponentName();
        } else {
            return super.getComponentName();
        }
    }

    @Override
    public View getCurrentFocus() {
        if (isProxyMode) {
            return proxyActivity.getCurrentFocus();
        } else {
            return super.getCurrentFocus();
        }
    }

    @Override
    public FragmentManager getFragmentManager() {
        if (isProxyMode) {
            return proxyActivity.getFragmentManager();
        } else {
            return super.getFragmentManager();
        }
    }

    @Override
    public Intent getIntent() {
        if (isProxyMode) {
            return proxyActivity.getIntent();
        } else {
            return super.getIntent();
        }
    }

    @Override
    @Deprecated
    public Object getLastNonConfigurationInstance() {
        if (isProxyMode) {
            return proxyActivity.getLastNonConfigurationInstance();
        } else {
            return super.getLastNonConfigurationInstance();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public LayoutInflater getLayoutInflater() {
        if (isProxyMode) {
            return proxyActivity.getLayoutInflater();
        } else {
            return super.getLayoutInflater();
        }
    }

    @Override
    public LoaderManager getLoaderManager() {
        if (isProxyMode) {
            return proxyActivity.getLoaderManager();
        } else {
            return super.getLoaderManager();
        }
    }

    @Override
    public String getLocalClassName() {
        if (isProxyMode) {
            return proxyActivity.getLocalClassName();
        } else {
            return super.getLocalClassName();
        }
    }

    @Override
    public MenuInflater getMenuInflater() {
        if (isProxyMode) {
            return proxyActivity.getMenuInflater();
        } else {
            return super.getMenuInflater();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public Intent getParentActivityIntent() {
        if (isProxyMode) {
            return proxyActivity.getParentActivityIntent();
        } else {
            return super.getParentActivityIntent();
        }
    }

    @Override
    public SharedPreferences getPreferences(int mode) {
        if (isProxyMode) {
            return proxyActivity.getPreferences(mode);
        } else {
            return super.getPreferences(mode);
        }
    }

    @Override
    public int getRequestedOrientation() {
        if (isProxyMode) {
            return proxyActivity.getRequestedOrientation();
        } else {
            return super.getRequestedOrientation();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (isProxyMode) {
            return proxyActivity.getSystemService(name);
        } else {
            return super.getSystemService(name);
        }
    }

    @Override
    public int getTaskId() {
        if (isProxyMode) {
            return proxyActivity.getTaskId();
        } else {
            return super.getTaskId();
        }
    }

    @Override
    public Window getWindow() {
        if (isProxyMode) {
            return proxyActivity.getWindow();
        } else {
            return super.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (isProxyMode) {
            return proxyActivity.getWindowManager();
        } else {
            return super.getWindowManager();
        }
    }

    @Override
    public boolean hasWindowFocus() {
        if (isProxyMode) {
            return proxyActivity.hasWindowFocus();
        } else {
            return super.hasWindowFocus();
        }
    }

    @Override
    public void invalidateOptionsMenu() {
        if (isProxyMode) {
            proxyActivity.invalidateOptionsMenu();
        } else {
            super.invalidateOptionsMenu();
        }
    }

    @Override
    public boolean isChangingConfigurations() {
        if (isProxyMode) {
            return proxyActivity.isChangingConfigurations();
        } else {
            return super.isChangingConfigurations();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean isDestroyed() {
        if (isProxyMode) {
            return proxyActivity.isDestroyed();
        } else {
            return super.isDestroyed();
        }
    }

    @Override
    public boolean isFinishing() {
        if (isProxyMode) {
            return proxyActivity.isFinishing();
        } else {
            return super.isFinishing();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean isImmersive() {
        if (isProxyMode) {
            return proxyActivity.isImmersive();
        } else {
            return super.isImmersive();
        }
    }

    @Override
    public boolean isTaskRoot() {
        if (isProxyMode) {
            return proxyActivity.isTaskRoot();
        } else {
            return super.isTaskRoot();
        }
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        if (isProxyMode) {
            return proxyActivity.moveTaskToBack(nonRoot);
        } else {
            return super.moveTaskToBack(nonRoot);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean navigateUpTo(Intent upIntent) {
        if (isProxyMode) {
            return proxyActivity.navigateUpTo(upIntent);
        } else {
            return super.navigateUpTo(upIntent);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean navigateUpToFromChild(Activity child, Intent upIntent) {
        if (isProxyMode) {
            return proxyActivity.navigateUpToFromChild(child, upIntent);
        } else {
            return super.navigateUpToFromChild(child, upIntent);
        }
    }

    @Override
    public void openContextMenu(View view) {
        if (isProxyMode) {
            proxyActivity.openContextMenu(view);
        } else {
            super.openContextMenu(view);
        }
    }

    @Override
    public void openOptionsMenu() {
        if (isProxyMode) {
            proxyActivity.openOptionsMenu();
        } else {
            super.openOptionsMenu();
        }
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        if (isProxyMode) {
            proxyActivity.overridePendingTransition(enterAnim, exitAnim);
        } else {
            super.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    @Override
    public void recreate() {
        if (isProxyMode) {
            proxyActivity.recreate();
        } else {
            super.recreate();
        }
    }

    @Override
    public void registerForContextMenu(View view) {
        if (isProxyMode) {
            proxyActivity.registerForContextMenu(view);
        } else {
            super.registerForContextMenu(view);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void reportFullyDrawn() {
        if (isProxyMode) {
            proxyActivity.reportFullyDrawn();
        } else {
            super.reportFullyDrawn();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (isProxyMode) {
            proxyActivity.setContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (isProxyMode) {
            proxyActivity.setContentView(view, params);
        } else {
            super.setContentView(view, params);
        }
    }

    @Override
    public void setContentView(View view) {
        if (isProxyMode) {
            proxyActivity.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        if (isProxyMode) {
            proxyActivity.setFinishOnTouchOutside(finish);
        } else {
            super.setFinishOnTouchOutside(finish);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void setImmersive(boolean i) {
        if (isProxyMode) {
            proxyActivity.setImmersive(i);
        } else {
            super.setImmersive(i);
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        if (isProxyMode) {
            proxyActivity.setIntent(newIntent);
        } else {
            super.setIntent(newIntent);
        }
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (isProxyMode) {
            proxyActivity.setRequestedOrientation(requestedOrientation);
        } else {
            super.setRequestedOrientation(requestedOrientation);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (isProxyMode) {
            proxyActivity.setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (isProxyMode) {
            proxyActivity.setTitle(titleId);
        } else {
            super.setTitle(titleId);
        }
    }

    @Override
    public void setTitleColor(int textColor) {
        if (isProxyMode) {
            proxyActivity.setTitleColor(textColor);
        } else {
            super.setTitleColor(textColor);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (isProxyMode) {
            proxyActivity.setVisible(visible);
        } else {
            super.setVisible(visible);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean shouldUpRecreateTask(Intent targetIntent) {
        if (isProxyMode) {
            return proxyActivity.shouldUpRecreateTask(targetIntent);
        } else {
            return super.shouldUpRecreateTask(targetIntent);
        }
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (isProxyMode) {
            return proxyActivity.startActionMode(callback);
        } else {
            return super.startActionMode(callback);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        if (isProxyMode) {
            proxyActivity.startActivities(intents, options);
        } else {
            super.startActivities(intents, options);
        }
    }

    @Override
    public void startActivities(Intent[] intents) {
        if (isProxyMode) {
            proxyActivity.startActivities(intents);
        } else {
            super.startActivities(intents);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startActivity(Intent intent, Bundle options) {
        if (isProxyMode) {
            proxyActivity.startActivity(intent, options);
        } else {
            super.startActivity(intent, options);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (isProxyMode) {
            proxyActivity.startActivity(intent);
        } else {
            super.startActivity(intent);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode,
                                       Bundle options) {
        if (isProxyMode) {
            proxyActivity.startActivityForResult(intent, requestCode, options);
        } else {
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (isProxyMode) {
            proxyActivity.startActivityForResult(intent, requestCode);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startActivityFromChild(Activity child, Intent intent,
                                       int requestCode, Bundle options) {
        if (isProxyMode) {
            proxyActivity.startActivityFromChild(child, intent, requestCode, options);
        } else {
            super.startActivityFromChild(child, intent, requestCode, options);
        }
    }

    @Override
    public void startActivityFromChild(Activity child, Intent intent,
                                       int requestCode) {
        if (isProxyMode) {
            proxyActivity.startActivityFromChild(child, intent, requestCode);
        } else {
            super.startActivityFromChild(child, intent, requestCode);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent,
                                          int requestCode, Bundle options) {
        if (isProxyMode) {
            proxyActivity.startActivityFromFragment(fragment, intent, requestCode, options);
        } else {
            super.startActivityFromFragment(fragment, intent, requestCode, options);
        }
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent,
                                          int requestCode) {
        if (isProxyMode) {
            proxyActivity.startActivityFromFragment(fragment, intent, requestCode);
        } else {
            super.startActivityFromFragment(fragment, intent, requestCode);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean startActivityIfNeeded(Intent intent, int requestCode,
                                         Bundle options) {
        if (isProxyMode) {
            return proxyActivity.startActivityIfNeeded(intent, requestCode, options);
        } else {
            return super.startActivityIfNeeded(intent, requestCode, options);
        }
    }

    @Override
    public boolean startActivityIfNeeded(Intent intent, int requestCode) {
        if (isProxyMode) {
            return proxyActivity.startActivityIfNeeded(intent, requestCode);
        } else {
            return super.startActivityIfNeeded(intent, requestCode);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent,
                                  int flagsMask, int flagsValues, int extraFlags, Bundle options)
            throws IntentSender.SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                    extraFlags, options);
        } else {
            super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                    extraFlags, options);
        }
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent,
                                  int flagsMask, int flagsValues, int extraFlags)
            throws IntentSender.SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                    extraFlags);
        } else {
            super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                    extraFlags);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startIntentSenderForResult(IntentSender intent,
                                           int requestCode, Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags, Bundle options)
            throws IntentSender.SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSenderForResult(intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags, options);
        } else {
            super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
                    flagsValues, extraFlags, options);
        }
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent,
                                           int requestCode, Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags) throws IntentSender
            .SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSenderForResult(intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags);
        } else {
            super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
                    flagsValues, extraFlags);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent,
                                           int requestCode, Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags, Bundle options)
            throws IntentSender.SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags, options);
        } else {
            super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags, options);
        }
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent,
                                           int requestCode, Intent fillInIntent, int flagsMask,
                                           int flagsValues, int extraFlags) throws IntentSender
            .SendIntentException {
        if (isProxyMode) {
            proxyActivity.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags);
        } else {
            super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
                    flagsMask, flagsValues, extraFlags);
        }
    }

    @Override
    @Deprecated
    public void startManagingCursor(Cursor c) {
        if (isProxyMode) {
            proxyActivity.startManagingCursor(c);
        } else {
            super.startManagingCursor(c);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean startNextMatchingActivity(Intent intent, Bundle options) {
        if (isProxyMode) {
            return proxyActivity.startNextMatchingActivity(intent, options);
        } else {
            return super.startNextMatchingActivity(intent, options);
        }
    }

    @Override
    public boolean startNextMatchingActivity(Intent intent) {
        if (isProxyMode) {
            return proxyActivity.startNextMatchingActivity(intent);
        } else {
            return super.startNextMatchingActivity(intent);
        }
    }

    @Override
    public void startSearch(String initialQuery, boolean selectInitialQuery,
                            Bundle appSearchData, boolean globalSearch) {
        if (isProxyMode) {
            proxyActivity.startSearch(initialQuery, selectInitialQuery, appSearchData,
                    globalSearch);
        } else {
            super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
        }
    }

    @Override
    @Deprecated
    public void stopManagingCursor(Cursor c) {
        if (isProxyMode) {
            proxyActivity.stopManagingCursor(c);
        } else {
            super.stopManagingCursor(c);
        }
    }

    @Override
    public void takeKeyEvents(boolean get) {
        if (isProxyMode) {
            proxyActivity.takeKeyEvents(get);
        } else {
            super.takeKeyEvents(get);
        }
    }

    @Override
    public void triggerSearch(String query, Bundle appSearchData) {
        if (isProxyMode) {
            proxyActivity.triggerSearch(query, appSearchData);
        } else {
            super.triggerSearch(query, appSearchData);
        }
    }

    @Override
    public void unregisterForContextMenu(View view) {
        if (isProxyMode) {
            proxyActivity.unregisterForContextMenu(view);
        } else {
            super.unregisterForContextMenu(view);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (isProxyMode) {
            proxyActivity.applyOverrideConfiguration(overrideConfiguration);
        } else {
            super.applyOverrideConfiguration(overrideConfiguration);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (isProxyMode) {
            try {
                Method method = proxyActivity.getClass().getMethod("attachBaseContext", Context
                        .class);
                method.setAccessible(true);
                method.invoke(proxyActivity, newBase);
            } catch (Exception e) {
                Log.e("郑海鹏", "BaseActivityResult# attachBaseContext(): 异常！");
            }
        } else {
            super.attachBaseContext(newBase);
        }
    }

    @Override
    public Resources getResources() {
        if (isProxyMode) {
            return proxyActivity.getResources();
        } else {
            return super.getResources();
        }
    }

    @Override
    public Resources.Theme getTheme() {
        if (isProxyMode) {
            return proxyActivity.getTheme();
        } else {
            return super.getTheme();
        }
    }

    @Override
    public void setTheme(int resid) {
        if (isProxyMode) {
            proxyActivity.setTheme(resid);
        } else {
            super.setTheme(resid);
        }
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        if (isProxyMode) {
            return proxyActivity.bindService(service, conn, flags);
        } else {
            return super.bindService(service, conn, flags);
        }
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        if (isProxyMode) {
            return proxyActivity.checkCallingOrSelfPermission(permission);
        } else {
            return super.checkCallingOrSelfPermission(permission);
        }
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        if (isProxyMode) {
            return proxyActivity.checkCallingOrSelfUriPermission(uri, modeFlags);
        } else {
            return super.checkCallingOrSelfUriPermission(uri, modeFlags);
        }
    }

    @Override
    public int checkCallingPermission(String permission) {
        if (isProxyMode) {
            return proxyActivity.checkCallingPermission(permission);
        } else {
            return super.checkCallingPermission(permission);
        }
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        if (isProxyMode) {
            return proxyActivity.checkCallingUriPermission(uri, modeFlags);
        } else {
            return super.checkCallingUriPermission(uri, modeFlags);
        }
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        if (isProxyMode) {
            return proxyActivity.checkPermission(permission, pid, uid);
        } else {
            return super.checkPermission(permission, pid, uid);
        }
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        if (isProxyMode) {
            return proxyActivity.checkUriPermission(uri, pid, uid, modeFlags);
        } else {
            return super.checkUriPermission(uri, pid, uid, modeFlags);
        }
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission,
                                  String writePermission, int pid, int uid, int modeFlags) {
        if (isProxyMode) {
            return proxyActivity.checkUriPermission(uri, readPermission, writePermission, pid,
                    uid, modeFlags);
        } else {
            return super.checkUriPermission(uri, readPermission, writePermission, pid, uid,
                    modeFlags);
        }
    }

    @Override
    public void clearWallpaper() throws IOException {
        if (isProxyMode) {
            proxyActivity.clearWallpaper();
        } else {
            super.clearWallpaper();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public Context createConfigurationContext(
            Configuration overrideConfiguration) {
        if (isProxyMode) {
            return proxyActivity.createConfigurationContext(overrideConfiguration);
        } else {
            return super.createConfigurationContext(overrideConfiguration);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public Context createDisplayContext(Display display) {
        if (isProxyMode) {
            return proxyActivity.createDisplayContext(display);
        } else {
            return super.createDisplayContext(display);
        }
    }

    @Override
    public Context createPackageContext(String packageName, int flags)
            throws PackageManager.NameNotFoundException {
        if (isProxyMode) {
            return proxyActivity.createPackageContext(packageName, flags);
        } else {
            return super.createPackageContext(packageName, flags);
        }
    }

    @Override
    public String[] databaseList() {
        if (isProxyMode) {
            return proxyActivity.databaseList();
        } else {
            return super.databaseList();
        }
    }

    @Override
    public boolean deleteDatabase(String name) {
        if (isProxyMode) {
            return proxyActivity.deleteDatabase(name);
        } else {
            return super.deleteDatabase(name);
        }
    }

    @Override
    public boolean deleteFile(String name) {
        if (isProxyMode) {
            return proxyActivity.deleteFile(name);
        } else {
            return super.deleteFile(name);
        }
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        if (isProxyMode) {
            proxyActivity.enforceCallingOrSelfPermission(permission, message);
        } else {
            super.enforceCallingOrSelfPermission(permission, message);
        }
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags,
                                                  String message) {
        if (isProxyMode) {
            proxyActivity.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
        } else {
            super.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
        }
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        if (isProxyMode) {
            proxyActivity.enforceCallingPermission(permission, message);
        } else {
            super.enforceCallingPermission(permission, message);
        }
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags,
                                            String message) {
        if (isProxyMode) {
            proxyActivity.enforceCallingUriPermission(uri, modeFlags, message);
        } else {
            super.enforceCallingUriPermission(uri, modeFlags, message);
        }
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid,
                                  String message) {
        if (isProxyMode) {
            proxyActivity.enforcePermission(permission, pid, uid, message);
        } else {
            super.enforcePermission(permission, pid, uid, message);
        }
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags,
                                     String message) {
        if (isProxyMode) {
            proxyActivity.enforceUriPermission(uri, pid, uid, modeFlags, message);
        } else {
            super.enforceUriPermission(uri, pid, uid, modeFlags, message);
        }
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission,
                                     String writePermission, int pid, int uid, int modeFlags,
                                     String message) {
        if (isProxyMode) {
            proxyActivity.enforceUriPermission(uri, readPermission, writePermission, pid, uid,
                    modeFlags, message);
        } else {
            super.enforceUriPermission(uri, readPermission, writePermission, pid, uid,
                    modeFlags, message);
        }
    }

    @Override
    public String[] fileList() {
        if (isProxyMode) {
            return proxyActivity.fileList();
        } else {
            return super.fileList();
        }
    }

    @Override
    public Context getApplicationContext() {
        if (isProxyMode) {
            return proxyActivity.getApplicationContext();
        } else {
            return super.getApplicationContext();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (isProxyMode) {
            return proxyActivity.getApplicationInfo();
        } else {
            return super.getApplicationInfo();
        }
    }

    @Override
    public AssetManager getAssets() {
        if (isProxyMode) {
            return proxyActivity.getAssets();
        } else {
            return super.getAssets();
        }
    }

    @Override
    public Context getBaseContext() {
        if (isProxyMode) {
            return proxyActivity.getBaseContext();
        } else {
            return super.getBaseContext();
        }
    }

    @Override
    public File getCacheDir() {
        if (isProxyMode) {
            return proxyActivity.getCacheDir();
        } else {
            return super.getCacheDir();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (isProxyMode) {
            return proxyActivity.getClassLoader();
        } else {
            return super.getClassLoader();
        }
    }

    @Override
    public ContentResolver getContentResolver() {
        if (isProxyMode) {
            return proxyActivity.getContentResolver();
        } else {
            return super.getContentResolver();
        }
    }

    @Override
    public File getDatabasePath(String name) {
        if (isProxyMode) {
            return proxyActivity.getDatabasePath(name);
        } else {
            return super.getDatabasePath(name);
        }
    }

    @Override
    public File getDir(String name, int mode) {
        if (isProxyMode) {
            return proxyActivity.getDir(name, mode);
        } else {
            return super.getDir(name, mode);
        }
    }

    @Override
    public File getExternalCacheDir() {
        if (isProxyMode) {
            return proxyActivity.getExternalCacheDir();
        } else {
            return super.getExternalCacheDir();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public File[] getExternalCacheDirs() {
        if (isProxyMode) {
            return proxyActivity.getExternalCacheDirs();
        } else {
            return super.getExternalCacheDirs();
        }
    }

    @Override
    public File getExternalFilesDir(String type) {
        if (isProxyMode) {
            return proxyActivity.getExternalFilesDir(type);
        } else {
            return super.getExternalFilesDir(type);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public File[] getExternalFilesDirs(String type) {
        if (isProxyMode) {
            return proxyActivity.getExternalFilesDirs(type);
        } else {
            return super.getExternalFilesDirs(type);
        }
    }

    @Override
    public File getFileStreamPath(String name) {
        if (isProxyMode) {
            return proxyActivity.getFileStreamPath(name);
        } else {
            return super.getFileStreamPath(name);
        }
    }

    @Override
    public File getFilesDir() {
        if (isProxyMode) {
            return proxyActivity.getFilesDir();
        } else {
            return super.getFilesDir();
        }
    }

    @Override
    public Looper getMainLooper() {
        if (isProxyMode) {
            return proxyActivity.getMainLooper();
        } else {
            return super.getMainLooper();
        }
    }

    @Override
    public File getObbDir() {
        if (isProxyMode) {
            return proxyActivity.getObbDir();
        } else {
            return super.getObbDir();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public File[] getObbDirs() {
        if (isProxyMode) {
            return proxyActivity.getObbDirs();
        } else {
            return super.getObbDirs();
        }
    }

    @Override
    public String getPackageCodePath() {
        if (isProxyMode) {
            return proxyActivity.getPackageCodePath();
        } else {
            return super.getPackageCodePath();
        }
    }

    @Override
    public PackageManager getPackageManager() {
        if (isProxyMode) {
            return proxyActivity.getPackageManager();
        } else {
            return super.getPackageManager();
        }
    }

    @Override
    public String getPackageName() {
        if (isProxyMode) {
            return proxyActivity.getPackageName();
        } else {
            return super.getPackageName();
        }
    }

    @Override
    public String getPackageResourcePath() {
        if (isProxyMode) {
            return proxyActivity.getPackageResourcePath();
        } else {
            return super.getPackageResourcePath();
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (isProxyMode) {
            return proxyActivity.getSharedPreferences(name, mode);
        } else {
            return super.getSharedPreferences(name, mode);
        }
    }

    @Override
    public Drawable getWallpaper() {
        if (isProxyMode) {
            return proxyActivity.getWallpaper();
        } else {
            return super.getWallpaper();
        }
    }

    @Override
    public int getWallpaperDesiredMinimumHeight() {
        if (isProxyMode) {
            return proxyActivity.getWallpaperDesiredMinimumHeight();
        } else {
            return super.getWallpaperDesiredMinimumHeight();
        }
    }

    @Override
    public int getWallpaperDesiredMinimumWidth() {
        if (isProxyMode) {
            return proxyActivity.getWallpaperDesiredMinimumWidth();
        } else {
            return super.getWallpaperDesiredMinimumWidth();
        }
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        if (isProxyMode) {
            proxyActivity.grantUriPermission(toPackage, uri, modeFlags);
        } else {
            super.grantUriPermission(toPackage, uri, modeFlags);
        }
    }

    @Override
    public boolean isRestricted() {
        if (isProxyMode) {
            return proxyActivity.isRestricted();
        } else {
            return super.isRestricted();
        }
    }

    @Override
    public FileInputStream openFileInput(String name)
            throws FileNotFoundException {
        if (isProxyMode) {
            return proxyActivity.openFileInput(name);
        } else {
            return super.openFileInput(name);
        }
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode)
            throws FileNotFoundException {
        if (isProxyMode) {
            return proxyActivity.openFileOutput(name, mode);
        } else {
            return super.openFileOutput(name, mode);
        }
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        if (isProxyMode) {
            return proxyActivity.openOrCreateDatabase(name, mode, factory, errorHandler);
        } else {
            return super.openOrCreateDatabase(name, mode, factory, errorHandler);
        }
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        if (isProxyMode) {
            return proxyActivity.openOrCreateDatabase(name, mode, factory);
        } else {
            return super.openOrCreateDatabase(name, mode, factory);
        }
    }

    @Override
    public Drawable peekWallpaper() {
        if (isProxyMode) {
            return proxyActivity.peekWallpaper();
        } else {
            return super.peekWallpaper();
        }
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
                                   IntentFilter filter, String broadcastPermission, Handler
                                               scheduler) {
        if (isProxyMode) {
            return proxyActivity.registerReceiver(receiver, filter, broadcastPermission, scheduler);
        } else {
            return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
        }
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
                                   IntentFilter filter) {
        if (isProxyMode) {
            return proxyActivity.registerReceiver(receiver, filter);
        } else {
            return super.registerReceiver(receiver, filter);
        }
    }

    @Override
    public void removeStickyBroadcast(Intent intent) {
        if (isProxyMode) {
            proxyActivity.removeStickyBroadcast(intent);
        } else {
            super.removeStickyBroadcast(intent);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        if (isProxyMode) {
            proxyActivity.removeStickyBroadcastAsUser(intent, user);
        } else {
            super.removeStickyBroadcastAsUser(intent, user);
        }
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        if (isProxyMode) {
            proxyActivity.revokeUriPermission(uri, modeFlags);
        } else {
            super.revokeUriPermission(uri, modeFlags);
        }
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        if (isProxyMode) {
            proxyActivity.sendBroadcast(intent, receiverPermission);
        } else {
            super.sendBroadcast(intent, receiverPermission);
        }
    }

    @Override
    public void sendBroadcast(Intent intent) {
        if (isProxyMode) {
            proxyActivity.sendBroadcast(intent);
        } else {
            super.sendBroadcast(intent);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user,
                                    String receiverPermission) {
        if (isProxyMode) {
            proxyActivity.sendBroadcastAsUser(intent, user, receiverPermission);
        } else {
            super.sendBroadcastAsUser(intent, user, receiverPermission);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        if (isProxyMode) {
            proxyActivity.sendBroadcastAsUser(intent, user);
        } else {
            super.sendBroadcastAsUser(intent, user);
        }
    }


    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission,
                                     BroadcastReceiver resultReceiver, Handler scheduler,
                                     int initialCode, String initialData, Bundle initialExtras) {
        if (isProxyMode) {
            proxyActivity.sendOrderedBroadcast(intent, receiverPermission, resultReceiver,
                    scheduler, initialCode, initialData, initialExtras);
        } else {
            super.sendOrderedBroadcast(intent, receiverPermission, resultReceiver,
                    scheduler, initialCode, initialData, initialExtras);
        }
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        if (isProxyMode) {
            proxyActivity.sendOrderedBroadcast(intent, receiverPermission);
        } else {
            super.sendOrderedBroadcast(intent, receiverPermission);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user,
                                           String receiverPermission, BroadcastReceiver
                                                       resultReceiver,
                                           Handler scheduler, int initialCode, String initialData,
                                           Bundle initialExtras) {
        if (isProxyMode) {
            proxyActivity.sendOrderedBroadcastAsUser(intent, user, receiverPermission,
                    resultReceiver, scheduler, initialCode, initialData, initialExtras);
        } else {
            super.sendOrderedBroadcastAsUser(intent, user, receiverPermission,
                    resultReceiver, scheduler, initialCode, initialData, initialExtras);
        }
    }

    @Override
    public void sendStickyBroadcast(Intent intent) {
        if (isProxyMode) {
            proxyActivity.sendStickyBroadcast(intent);
        } else {
            super.sendStickyBroadcast(intent);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        if (isProxyMode) {
            proxyActivity.sendStickyBroadcastAsUser(intent, user);
        } else {
            super.sendStickyBroadcastAsUser(intent, user);
        }
    }

    @Override
    public void sendStickyOrderedBroadcast(Intent intent,
                                           BroadcastReceiver resultReceiver, Handler scheduler,
                                           int initialCode, String initialData, Bundle
                                                       initialExtras) {
        if (isProxyMode) {
            proxyActivity.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler,
                    initialCode, initialData, initialExtras);
            ;
        } else {
            super.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler,
                    initialCode, initialData, initialExtras);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void sendStickyOrderedBroadcastAsUser(Intent intent,
                                                 UserHandle user, BroadcastReceiver resultReceiver,
                                                 Handler scheduler, int initialCode,
                                                 String initialData,
                                                 Bundle initialExtras) {
        if (isProxyMode) {
            proxyActivity.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver,
                    scheduler, initialCode, initialData, initialExtras);
        } else {
            super.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler,
                    initialCode, initialData, initialExtras);
        }
    }

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException {
        if (isProxyMode) {
            proxyActivity.setWallpaper(bitmap);
        } else {
            super.setWallpaper(bitmap);
        }
    }

    @Override
    public void setWallpaper(InputStream data) throws IOException {
        if (isProxyMode) {
            proxyActivity.setWallpaper(data);
        } else {
            super.setWallpaper(data);
        }
    }

    @Override
    public boolean startInstrumentation(ComponentName className,
                                        String profileFile, Bundle arguments) {
        if (isProxyMode) {
            return proxyActivity.startInstrumentation(className, profileFile, arguments);
        } else {
            return super.startInstrumentation(className, profileFile, arguments);
        }
    }

    @Override
    public ComponentName startService(Intent service) {
        if (isProxyMode) {
            return proxyActivity.startService(service);
        } else {
            return super.startService(service);
        }
    }

    @Override
    public boolean stopService(Intent name) {
        if (isProxyMode) {
            return proxyActivity.stopService(name);
        } else {
            return super.stopService(name);
        }
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        if (isProxyMode) {
            proxyActivity.unbindService(conn);
        } else {
            super.unbindService(conn);
        }
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (isProxyMode) {
            proxyActivity.unregisterReceiver(receiver);
        } else {
            super.unregisterReceiver(receiver);
        }
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        if (isProxyMode) {
            proxyActivity.registerComponentCallbacks(callback);
        } else {
            super.registerComponentCallbacks(callback);
        }
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        if (isProxyMode) {
            proxyActivity.unregisterComponentCallbacks(callback);
        } else {
            super.unregisterComponentCallbacks(callback);
        }
    }
}
