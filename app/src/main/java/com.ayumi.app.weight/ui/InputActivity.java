import android.app.Activity;
import android.app.Notification;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ayumi.app.weight.adapter.DBAdapter;
import com.doriwo.yaseyouze.app.R;


public class InputActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    DBAdapter dbadapter = new DBAdapter(this);
    SQLiteDatabase db = dbadapter.getReadableDatabase();



}