package com.ayumi.app.weight.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ayumi.app.weight.adapter.DBAdapter;

/**
 * Created by rpd on 14/05/09.
 */
public class ItemListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);
        findViews();
        setListeners();
    }

    DBAdapter mDBAdapter = new DBAdapter(this);
    SQLiteDatabase db = mDBAdapter.getReadableDatabase();

    private void readDB(SQLiteDatabase db, Cursor cursor) {
        String str = new String();
        cursor.moveToFirst();
    }


}
