package com.onlylemi.test5_storage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.onlylemi.test5_storage.entity.UserInfo;

/**
 * SQLManager
 *
 * @author: onlylemi
 * @time: 2016-06-13 18:14
 */
public class SQLManager {

    private SQLHelper helper;
    private SQLiteDatabase database;

    public SQLManager(Context context) {
        helper = new SQLHelper(context);
    }

    public boolean insertUser(UserInfo userInfo) {
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", userInfo.getUsername());
        values.put("password", userInfo.getPassword());
        long row_id = database.insert("user_info", null, values);

        return row_id != -1;
    }


}
