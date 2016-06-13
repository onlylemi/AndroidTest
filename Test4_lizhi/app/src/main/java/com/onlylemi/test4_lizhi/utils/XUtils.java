package com.onlylemi.test4_lizhi.utils;

import java.text.SimpleDateFormat;

/**
 * XUtils
 *
 * @author: onlylemi
 * @time: 2016-06-13 8:22
 */
public class XUtils {

    private XUtils() {

    }

    public static String formatTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }
}
