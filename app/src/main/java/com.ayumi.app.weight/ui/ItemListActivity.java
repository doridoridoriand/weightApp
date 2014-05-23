package com.ayumi.app.weight.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ayumi.app.weight.adapter.DBAdapter;
import com.ayumi.app.weight.adapter.Weight;
import com.doriwo.yaseyouze.app.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rpd on 14/05/09.
 */
public class ItemListActivity extends Activity implements View.OnClickListener {

    static final String TAG = "WeightApp";
    static final int MENUITEM_ID_DELETE = 1;

    ListView itemListView;

    static WeightListAdapter sWeightListAdapter;
    static DBAdapter sDBAdapter;
    static List<Weight> sWeightList = new ArrayList<Weight>();

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ItemListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setListeners();

        sDBAdapter = new DBAdapter(this);
        sWeightListAdapter = new WeightListAdapter();
        itemListView.setAdapter(sWeightListAdapter);

        loadWeight();
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
        }

    protected void setListeners() {
        itemListView.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, MENUITEM_ID_DELETE, 0, "Delete");
                    }
                });
    }

    private class WeightListAdapter extends BaseAdapter {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView weightTextView;
            TextView lastupdateTextView;
            View v = convertView;

            if(v == null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.item_list, null);
            }

            Weight weight = (Weight)getItem(position);
            if(weight != null) {
                weightTextView = (TextView)v.findViewById(R.id.item_weight_text_view);
                lastupdateTextView = (TextView)v.findViewById(R.id.item_date_text_view);
                weightTextView.setText(weight.getweight());
                lastupdateTextView.setText(weight.getLastupdate());
                v.setTag(R.id.item_weight_text_view, weight);
            }
        }

    }
}