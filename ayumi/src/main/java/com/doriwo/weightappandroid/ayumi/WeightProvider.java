package com.doriwo.weightappandroid.ayumi;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.doriwo.weightappandroid.ayumi.db.DBAdapter;

import java.sql.SQLException;

/**
 * Created by rpd on 14/05/30.
 */
public class WeightProvider extends ContentProvider{

    private static final int WEIGHT = 1;
    private static final int WEIGHT_ID = 2;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI("com.doriwo.weightappandroid.ayumi.provider.weight", "weight", WEIGHT);
        URI_MATCHER.addURI("com.doriwo.weightappandroid.ayumi.provider.weight", "weight/#", WEIGHT_ID);
    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if (URI_MATCHER.match(uri) != WEIGHT) {
            throw new IllegalArgumentException("Unknown URL" + uri);
        }

        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        long rowID = db.replace("weight", "NULL", values);

        if (rowID > 0) {
            Uri newUri = ContentUris.withAppendedId("content://com.doriwoweightappandroid.ayumi.provider.weight/weight", rowID);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row int " + uri);
    }


    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int count;
        switch (URI_MATCHER.match(uri)) {
            case WEIGHT:
                count = db.delete(DBAdapter.TABLE_NAME, " " + DBAdapter.DB_ID + "like '%'", null);
            break;

            default:
                throw new IllegalArgumentException("Unknown URL" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {

        SQLiteDatabase db = mDatabaseHelper.getWritabledatabase();
        int count;
        switch (URI_MATCHER.match(uri)) {
            case WEIGHT:
                count = db.update(DBAdapter.TABLE_NAME, values, where, whereArgs);
                break;

            case WEIGHT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(DBAdapter.TABLE_NAME, values, DBAdapter.DB_ID + "=" + id +(!TextUtils.isEmpty(where) ? " AND (" + where + ')': ""), whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URL" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}
