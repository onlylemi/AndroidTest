package com.onlylemi.plugin.host;

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

        findViewById(R.id.open_plugin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlugin();
            }
        });

        findViewById(R.id.open_plugin_activity_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPluginActivity();
            }
        });
    }

    /**
     * 初始化 DexClassLoader
     */
    private void initClassLoader(){
        String apkPath = Environment.getExternalStorageDirectory() + File.separator + "plugin.apk";

        if (!new File(apkPath).exists()){
            Log.i(TAG, "openPlugin: 插件文件不存在！");
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
    private void openPluginActivity2() {
        ClassInject inject = new ClassInject();
        inject.inject((PathClassLoader) getClassLoader(), dexLoader);
    }

    public void loadProxyActivity() {
        String className = getIntent().getStringExtra("com.onlylemi.plugin.first.Entrace");

        Log.i(TAG, "loadProxyActivity: 传入的类名为：" + className);

        try {
            Class<?> clazz = dexLoader.loadClass(className);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
