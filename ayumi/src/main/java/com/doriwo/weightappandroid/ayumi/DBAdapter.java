package com.doriwo.weightappandroid.ayumi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Dorian on 2014/05/29.
 */
public class DBAdapter {

    static final String DATABASE_NAME = "weight.db";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "weights";
    public static final String DB_ID = "_id";
    public static final String DB_WEIGHT = "mass";
    public static final String DB_LASTUPDATE = "lastupdate";

    protected final Context mContext;
    protected DatabaseHelper mDatabaseHelper;
    protected SQLiteDatabase mSQLiteDatabase;

    public DBAdapter(Context context) {
        this.mContext = context;
        mDatabaseHelper = new DatabaseHelper(this.mContext);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public synchronized void onCreate(SQLiteDatabase db) {
            String sqlstring = null;
            StringBuilder sqlset = new StringBuilder();

            sqlset.append("CREATE TABLE ");
            sqlset.append(TABLE_NAME);
            sqlset.append(" ( ");
            sqlset.append(DB_ID);
            sqlset.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlset.append(DB_WEIGHT);
            sqlset.append(" INTEGER NOT NULL, ");
            sqlset.append(DB_LASTUPDATE);
            sqlset.append(" TEXT NOT NULL )");

            sqlstring = sqlset.toString();
            db.execSQL(sqlstring);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            onCreate(db);

        }
    }

    public DBAdapter open() {
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDatabaseHelper.close();
    }

    public boolean deleteALLWEIGHT() {
        return mSQLiteDatabase.delete(TABLE_NAME, null, null) > 0;
    }

    public Cursor getALLWEIGHT() {
        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveWEIGHT(String weight) {
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(DB_WEIGHT, weight);
        values.put(DB_LASTUPDATE, date.toString());
        mSQLiteDatabase.insert(TABLE_NAME, null, values);
    }
}
