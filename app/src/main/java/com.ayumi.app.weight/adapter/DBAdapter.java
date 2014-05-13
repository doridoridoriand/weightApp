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

    static final String DATABASE_NAME = "weight.db";
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
        dbHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlstring = null;
            StringBuilder sqlset = new StringBuilder();

            sqlset.append("CREATE TABLE ");
            sqlset.append(TABLE_NAME);
            sqlset.append(" ( ");
            sqlset.append(COL_ID);
            sqlset.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlset.append(COL_WEIGHT);
            sqlset.append(" NOT NULL, ");
            sqlset.append(COL_LASTUPDATE);
            sqlset.append(" TEXT NOT NULL )");

            sqlstring = sqlset.toString();
            db.execSQL(sqlstring);
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

    public Cursor getALLWEIGHT() {
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveWEIGHT(String weight) {
            Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT, weight);
        values.put(COL_LASTUPDATE, date.toLocaleString());
        db.insert(TABLE_NAME, null, values);
    }
}
