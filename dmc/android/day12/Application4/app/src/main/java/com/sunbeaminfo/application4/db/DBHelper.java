package com.sunbeaminfo.application4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String NAME = "mydb.sqlite";

    public DBHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    // gets called only
    // - when the db file does not exist
    // - once

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table car (id integer primary key autoincrement, model text, company text)");
        db.execSQL("create table mobile (id integer primary key autoincrement, model text, company text)");
        db.execSQL("create table user (id integer primary key autoincrement, name text, address text)");
        db.execSQL("create table player (id integer primary key autoincrement, name text, address text, team text)");
    }


    // gets called only
    // - when the db exists and
    // - old version is not same as new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ((oldVersion == 1) && (newVersion == 3)) {
            db.execSQL("create table user (id integer primary key autoincrement, name text, address text)");
            db.execSQL("create table player (id integer primary key autoincrement, name text, address text, team text)");
        } else if ((oldVersion == 2) && (newVersion == 3)) {
            db.execSQL("create table player (id integer primary key autoincrement, name text, address text, team text)");
        }
    }
    
}
