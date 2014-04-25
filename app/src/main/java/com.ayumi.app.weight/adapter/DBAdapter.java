package com.ayumi.app.weight.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Dorian on 2014/04/25.
 */
public class DBAdapter {
    private final String DATABASE_NAME = "weight.db";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "weights";
    public static final String COL_ID = "_id";
    public static final String COL_WEIGHT = "mass";
    public static final String COL_LASTUPDATE = "lastupdate";

    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new databaseHelper(this.context);
    }

    private static final databaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE" + TABLE NAME + "(" + COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COL_WEIGHT + "TEXT NOT NULL", + COL_LASTUPDATE + "TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(
                SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            onCreate(db);
        }

    }

    public DBAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean deleteALLWEIGHT() {
        return db.delete(TABLE_NAME, null, null) > 0;
    }

    punlic Cursor getALLWEIGHT() {
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveWEIGHT(String weight) {
            Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT, weight);
        values.put(TABLE_NAME, null, values);
    }
}
