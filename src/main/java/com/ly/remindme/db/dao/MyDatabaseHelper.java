package com.ly.remindme.db.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lo__oY on 2016/7/8.
 *生成数据库的帮助类
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{

    public MyDatabaseHelper(Context context, String name) {
        super(context, name, null, 1);

    }


    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table note(id integer PRIMARY KEY autoincrement,title String,content String," +
                "time long)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
