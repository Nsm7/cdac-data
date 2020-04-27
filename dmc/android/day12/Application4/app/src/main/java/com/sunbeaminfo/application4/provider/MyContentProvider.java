package com.sunbeaminfo.application4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunbeaminfo.application4.constants.Constants;
import com.sunbeaminfo.application4.db.DBHelper;

import java.util.List;

public class MyContentProvider extends ContentProvider {

    SQLiteDatabase db;

    static UriMatcher uriMatcher;

    private static final int CODE_MOBILE = 1;
    private static final int CODE_CAR = 2;
    private static final int CODE_USER = 3;
    private static final int CODE_CAR_ID = 4;
    private static final int CODE_MOBILE_ID = 5;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // content://com.sunbeaminfo.application4/car
        uriMatcher.addURI(Constants.AUTHORITY, Constants.PATH_CAR, CODE_CAR);

        // content://com.sunbeaminfo.application4/car/1
        uriMatcher.addURI(Constants.AUTHORITY, Constants.PATH_CAR + "/#", CODE_CAR_ID);

        // content://com.sunbeaminfo.application4/mobile
        uriMatcher.addURI(Constants.AUTHORITY, Constants.PATH_MOBILE, CODE_MOBILE);

        // content://com.sunbeaminfo.application4/mobile/2
        uriMatcher.addURI(Constants.AUTHORITY, Constants.PATH_MOBILE + "/#", CODE_MOBILE_ID);

        // content://com.sunbeaminfo.application4/user
        uriMatcher.addURI(Constants.AUTHORITY, Constants.PATH_USER, CODE_USER);
    }

    @Override
    public boolean onCreate() {
        DBHelper helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        int code = uriMatcher.match(uri);

        if (code == CODE_CAR) {
            cursor = db.query("car", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (code == CODE_MOBILE) {
            cursor = db.query("mobile", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (code == CODE_USER) {
            cursor = db.query("user", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (code == CODE_CAR_ID) {
            // ID
            // where id = <ID>

            // content://com.sunbeaminfo.application4/car/1
            // 0: car
            // 1: 1 <id>
            List<String> segments = uri.getPathSegments();
            String id = segments.get(1);

            cursor = db.query("car", projection, "id=?", new String[] {id}, null, null, sortOrder);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code = uriMatcher.match(uri);
        if (code == CODE_MOBILE) {
            db.insert("mobile", null, values);
        } else if (code == CODE_CAR) {
            db.insert("car", null, values);
        } else if (code == CODE_USER) {
            db.insert("user", null, values);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
