package com.onlylemi.test5_storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLHelper
 *
 * @author: onlylemi
 * @time: 2016-06-13 18:02
 */
public class SQLHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1; // 数据库的版本
    private static final String DB_NAME = "mydb.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user_info(" +
                "user_id integer not null primary key autoincrement," +
                "username varchar(64)," +
                "password varchar(64)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
