package com.onlylemi.test7_listview;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;

/**
 * AppUtils
 *
 * @author: onlylemi
 * @time: 2016-07-09 20:15
 */
public class AppUtils {

    private AppUtils() {

    }

    /**
     * 获取缓存目录
     *
     * @param context
     * @param folder
     * @return
     */
    public static File getDiskCacheDir(Context context, String folder) {
        String cacheDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !Environment.isExternalStorageRemovable()) {
            cacheDir = context.getExternalCacheDir().getPath();
        } else {
            cacheDir = context.getCacheDir().getPath();
        }
        return new File(cacheDir + File.separator + folder);
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName
                    (), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
