package com.sunbeaminfo.application2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    // context:
    // name: file name (database name)
    // factory: cursor factory => null
    // version: database version
    public DBHelper(@Nullable Context context) {
        super(context, "contacts_db.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // used to initialize the db schema [collection of tables]
        String statement = "create table country (id integer primary key autoincrement, name text);";

        // execute the statement
        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
