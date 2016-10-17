package com.onlylemi.plugin.host;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.onlylemi.plugin.sdk.IPlugin;

import java.io.File;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DexClassLoader dexLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initClassLoader();
    }

    public void onClick1(View view) {
        openPlugin();
    }

    public void onClick2(View view) {
        openPluginActivity();
    }

    public void onClick3(View view) {
        openPluginActivity21();
    }

    public void onClick4(View view) {
        openPluginActivity22();
    }

    /**
     * 初始化 DexClassLoader
     */
    private void initClassLoader(){
        String apkPath = Environment.getExternalStorageDirectory() + File.separator + "plugin.apk";

        if (!new File(apkPath).exists()){
            Log.i(TAG, "openPlugin: 插件文件 plugin.apk s不存在！");
            return;
        }

        // dex文件的释放目录
        File releasePath = getDir("dexs", 0);

        // 类加载
        dexLoader = new DexClassLoader(apkPath, releasePath.getAbsolutePath(), null, getClassLoader());
    }

    /**
     * 打开插件
     */
    private void openPlugin() {
        try {
            // 插件中的类
            Class<?> pluginClass = dexLoader.loadClass("com.onlylemi.plugin.first.Entrace");
            IPlugin plugin = (IPlugin) pluginClass.newInstance();
            plugin.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开插件中的 Activity (预注册的方式)
     * 1、manifests 中注册插件中的 Activity
     * 2、将 DexClassLoader 的 DexPathList 合并到系统默认的加载器 PathClassLoader 的 DexPathList 中
     * 3、插件中的 activity 无法加载插件中的 res 中的文件
     *
     */
    private void openPluginActivity() {
        ClassInject inject = new ClassInject();
        inject.inject((PathClassLoader) getClassLoader(), dexLoader);

        try {
            // 插件中的类
            Class<?> pluginClass = dexLoader.loadClass("com.onlylemi.plugin.first.Entrace");
            IPlugin plugin = (IPlugin) pluginClass.newInstance();
            plugin.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开插件中的 Activity （代理方式）
     */
    private void openPluginActivity21() {
        String apkPath = Environment.getExternalStorageDirectory() + File.separator + "plugin.apk";
        if (!new File(apkPath).exists()){
            Log.i(TAG, "openPlugin: 插件文件 plugin.apk 不存在！");
            return;
        }

        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra(ProxyActivity.PROXIED_CLASS_NAME, "com.xiaomi.first.MainActivity");
        intent.putExtra(ProxyActivity.EXTRA_APK_PATH, apkPath);
        startActivity(intent);
    }
    private void openPluginActivity22() {
        String apkPath = Environment.getExternalStorageDirectory() + File.separator + "plugin.apk";
        if (!new File(apkPath).exists()){
            Log.i(TAG, "openPlugin: 插件文件 plugin.apk 不存在！");
            return;
        }

        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra(ProxyActivity.PROXIED_CLASS_NAME, "com.xiaomi.first.SecondActivity");
        intent.putExtra(ProxyActivity.EXTRA_APK_PATH, apkPath);
        startActivity(intent);
    }
}
