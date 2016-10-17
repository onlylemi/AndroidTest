package com.onlylemi.plugin.host;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class ProxyActivity extends AppCompatActivity {

    private static final String TAG = ProxyActivity.class.getSimpleName();

    public static final String PROXIED_CLASS_NAME = "PROXIED_CLASS_NAME";
    public static final String EXTRA_APK_PATH = "EXTRA_APK_PATH";
    Object proxiedActivity = null;
    DexClassLoader dexLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initClassLoader();

        loadProxiedActivity();

//        setContentView(R.layout.activity_proxy);

        if (proxiedActivity != null) {
            Log.i(TAG, "onCreate: 伪周期开始！");

            try {
                Method method = proxiedActivity.getClass().getMethod("onCreate", Bundle.class);
                method.setAccessible(true);
                method.invoke(proxiedActivity, savedInstanceState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "onCreate: 被代理的类为 null");
        }
    }

    @Override
    protected void onStart() {
        if (proxiedActivity != null) {
            try {
                Method method = proxiedActivity.getClass().getMethod("onStart", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onStart();
    }

    @Override
    protected void onRestart() {
        if (proxiedActivity != null) {
            try {
                Method method = proxiedActivity.getClass().getMethod("onRestart", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onRestart();
    }

    @Override
    protected void onResume() {
        if (proxiedActivity != null){
            try {
                Method method = proxiedActivity.getClass().getMethod("onResume", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (proxiedActivity != null) {
            try {
                Method method = proxiedActivity.getClass().getMethod("onPause", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (proxiedActivity != null) {
            try {
                Method method = proxiedActivity.getClass().getMethod("onStop", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (proxiedActivity != null) {
            Log.i(TAG, "onDestroy: 为周期开始");
            try {
                Method method = proxiedActivity.getClass().getMethod("OnDestroy", new Class[]{});
                method.setAccessible(true);
                method.invoke(proxiedActivity, new Object[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    /**
     * 加载被代理 Activity 的信息
     */
    public void loadProxiedActivity() {
        String className = getIntent().getStringExtra(PROXIED_CLASS_NAME);

        Log.i(TAG, "loadProxiedActivity: 传入的类名：" + className);

        try {
            Class<?> clazz = dexLoader.loadClass(className);
            proxiedActivity = clazz.newInstance();

            // 使得插件 Activity 持有宿主 Activity 的引用
            Method method = proxiedActivity.getClass().getMethod("setProxy", AppCompatActivity.class);
            method.setAccessible(true);
            method.invoke(proxiedActivity, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化 ClassLoader
     */
    private void initClassLoader() {
        String apkPath = getIntent().getStringExtra(EXTRA_APK_PATH);
        File file = new File(apkPath);
        if (!file.exists()) {
            Log.i(TAG, "initClassLoader: 插件文件不存在！");
            return;
        }

        // dex 文件释放目录
        File releasePath = getDir("dexs", 0);
        // 类加载器
        dexLoader = new DexClassLoader(apkPath, releasePath.getAbsolutePath(), null, getClassLoader());

        // 注入到原生的 ClassLoader 中
        ClassInject inject = new ClassInject();
        inject.inject((PathClassLoader) getClassLoader(), dexLoader);
    }
}
