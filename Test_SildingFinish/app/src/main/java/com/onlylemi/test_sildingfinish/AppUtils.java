package com.onlylemi.test_sildingfinish;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * AppUtils
 *
 * @author: onlylemi
 * @time: 2016-07-22 18:40
 */
public class AppUtils {

    private AppUtils() {

    }

    /**
     * 屏幕的width
     *
     * @param activity
     * @return
     */
    public static int screeWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 屏幕的高
     *
     * @param activity
     * @return
     */
    public static int screeHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
