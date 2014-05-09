package com.ayumi.app.weight.ui;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ayumi.app.weight.adapter.DBAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpd on 14/04/25.
 */
public class LoadWeight extends Activity implements View.OnClickListener {

    static final String TAG = "WEIGHT";
    static final int MENUITEM_ID_DELETE = "1";
    ListView itemListView;
    EditText itemEditText;
    Button saveButton;

    static DBAdapter sDBAdapter;
    static WeightListAdapter sWeightListAdapter;
    static List<Weight> sWeightList = new ArrayList<Weight>();

    @Override
    public void onCreate(Bundle savedINstanceState) {
        super.onCreate(savedINstanceState);
        setContentView(android.R.layout.activity_list_item);
        findViews();
        setListeners();

        sDBAdapter = new DBAdapter(this);
        sWeightListAdapter = new WeightListAdapter;
        itemListView.setAdapter(sWeightListAdapter);

        loadWeight();
    }

    protected void findViews() {
        itemListView = (ListView)findViewById(android.R.layout.);
        itemEditText = (EditText)findViewById(android.R.)
    }
}