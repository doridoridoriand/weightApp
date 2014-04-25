package com.ayumi.app.weight.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayumi.app.weight.adapter.DBAdapter;
import com.doriwo.yaseyouze.app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 2014/04/18.
 */
public class InputActivity extends Activity implements DialogInterface.OnClickListener {

    static final String TAG = "WEIGHTAPP";
    static final int MENUITEM_ID_DELETE = 1;
    ListView itemListView;
    EditText weightEditText;
    Button saveButton;
    static DBAdapter dbAdapter;
    static WeightListAdapter listAdapter;
    static List<Weight> weightList = new ArrayList<Weight>();

    @Override
    public void onCreate(Bundle saveInstanseState) {
        super.onCreate(savedInstanseState);
        setContentViaew(R.layout.item_list);
        findViews();
        setListeners();

        dbAdapter = new DBAdapter(this);
        listAdapter = new WeightListAdapter();
        itemListView.setAdapter(listAdapter);

        loadweight();
    }

    protected void findViews() {
        itemListView = (ListView)findViewById(R.id.#);
        noteEditText = (EditText)findViewById(R.id.#);
        saveButton = (Button)findViewById(R.id.btn_submit);
    }

    protected void loadweight() {
        weightList.clear();

        dbAdapter.open();
        Cursor c = dbAdapter.getAllWeights();
    }

    if(c.moveToFirst()) {
        do {
            Weight weight = new weight(
                    c.getInt(c.getColuminIndex(DBAdapter.COL_ID)), c.getString(c.getColumnIndex(DBAdapter.COL_WEIGHT)), c.getStringColumnIndex(DBAdapter.COL_LASTUPDATE)
            );
            weightList.add(weight);
        } while(c.moveToNext());

        dbAdapter.close();
        listAdapter.notifyDataSetChanged();
    }

    protected void saveItem() {
        dbAdapter.open();
        dbAdapter.saveWEIGHT(noteEditText.getText().toInt());
        dbAdapter.close();
        weightEditText.setText("");
        loadweight();
    }

    protected void setListeners() {
        saveButton.setOnClickListener(this);

        itemListView.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(0, MENUITEM_ID_DELETE, 0, "Delete");
                    }
                });
    }

    @Override
    public boolean onContextItemselected(MenuItem item) {

        switch(item.getItemId()) {
            case MENUITEM_ID_DELETE;
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

                Weight weight = weightList.get(menuInfo.position);
                final int weightId = weight.getId();

                new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle("データーを削除してもよろしいですか？").setPositiveButton(
                        "YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbAdapter.open();
                                if(dbAdapter.deleteWeight(weightId)) {
                                    Toast.makeText(
                                            getBaseContext(),
                                            "正常に削除されました",
                                            Toast.LENGTH_SHORT);
                                    loadweight();
                                }
                                dbAdapter.close();
                            }
                        })
                        .setNavigationButton(
                                "キャンセル",null)
                        .show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_submit:
                saveItem();
                break;
        }
    }

    private class WeightListAdapter extends BaseAdapter {

        @Override
        public getCount() {
            return weightList.size();
        }

        @Override
        public Object getItem(int position) {
            return weightList.get(position);
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
            if(v==null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context,LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.#, null);
            }
            ContactsContract.CommonDataKinds.Note note = (ContactsContract.CommonDataKinds.Note)getItem(position);
            if(note != null) {
                weightTextView = (TextView)v.findViewById(R.id.#);
                lastupdateTextView = (TextView)v.findViewById(
                        R.id.

                )
            }

        }
    }
}