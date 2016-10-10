package com.onlylemi.plugin.sdk;

import android.app.Activity;

/**
 * IPlugin
 *
 * @author qijianbin
 * @time 2016-10-9 10:36
 */
public interface IPlugin {

    void execute();

    void execute(Activity activity);
}
