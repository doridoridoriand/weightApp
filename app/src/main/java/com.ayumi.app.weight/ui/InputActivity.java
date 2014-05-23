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
import com.ayumi.app.weight.adapter.Weight;
import com.doriwo.yaseyouze.app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InputActivity extends Activity implements View.OnClickListener {

    static final String TAG = "WeightApp";
    static final int MENUITEM_ID_DELETE = 1;

    ListView itemListView;
    EditText weightEditText;
    Button saveButton;

    static DBAdapter sDBAdapter;
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

        loadWeight();

        //TextView dateText = (TextView)findViewById(R.id.input_activity_date);
        //Date date = new Date();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日' kk'時'");
        //dateText.setText(simpleDateFormat.format(date));
       // TextView dateText = (TextView)findViewById(R.id.input_activity_date);
       // Date date = new Date();
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
       // dateText.setText(simpleDateFormat.format(date));
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
}
