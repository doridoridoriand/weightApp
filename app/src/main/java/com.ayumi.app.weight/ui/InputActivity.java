package com.ayumi.app.weight.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ayumi.app.weight.adapter.DBAdapter;
import com.ayumi.app.weight.util.Weight;
import com.doriwo.yaseyouze.app.R;

import java.util.ArrayList;
import java.util.List;


public class InputActivity extends Activity implements View.OnClickListener {

    static final String TAG = "WeightApp";
    static final int MENUITEM_ID_DELETE = 1;

    ListView itemListView;
    EditText weightEditText;
    Button saveButton;

    static DBAdapter sDBAdapter;
    //static WeightListAdapter sWeightListAdapter;
    static List<Weight> sWeightList = new ArrayList<Weight>();

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, InputActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        findViews();
        setListeners();

        sDBAdapter = new DBAdapter(this);
        //sWeightListAdapter = new WeightListAdapter();
        //itemListView.setAdapter(sWeightListAdapter);

        loadWeight();
    }

    protected void findViews() {
        weightEditText = (EditText)findViewById(R.id.weight_input);
        saveButton = (Button)findViewById(R.id.btn_submit);
    }

    protected void loadWeight() {
        sWeightList.clear();
        sDBAdapter.open();

        Cursor c = sDBAdapter.getALLWEIGHT();

        startManagingCursor(c);

        if(c.moveToFirst()) {
            do {
                Weight weight = new Weight(
                        c.getInt(c.getColumnIndex(DBAdapter.COL_ID)),
                        c.getString(c.getColumnIndex(DBAdapter.COL_WEIGHT)),
                        c.getString(c.getColumnIndex(DBAdapter.COL_LASTUPDATE)));
                sWeightList.add(weight);
            } while (c.moveToNext());
        }

        stopManagingCursor(c);
        sDBAdapter.close();

        //sWeightListAdapter.notifyDataSetChanged();
    }

    protected void saveItem() {
        sDBAdapter.open();
        sDBAdapter.saveWEIGHT(weightEditText.getText().toString());
        sDBAdapter.close();
        weightEditText.setText("");
        loadWeight();
    }

    protected void setListeners() {
        saveButton.setOnClickListener(this);

        itemListView.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, MENUITEM_ID_DELETE, 0, "Delete");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_submit:
                saveItem();
                break;
        }
    }

    /*private class WeightListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sWeightList.size();
        }

        @Override
        public Object getItem(int position) {
            return sWeightList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, convertView, ViewGroup parent) {
            TextView weightTextView;
            TextView lastupdateTextView;
             View v = convertView;
        }
    } */

}