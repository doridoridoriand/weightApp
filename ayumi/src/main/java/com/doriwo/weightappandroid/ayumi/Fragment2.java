package com.doriwo.weightappandroid.ayumi;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import com.doriwo.weightappandroid.ayumi.Weight;
/**
 * Created by Dorian on 14/05/30.
 */
public class Fragment2 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mSimpleCursorAdapter;
    List<Weight> mWeightList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_activity, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);

        String[] from = new String[]{
                DBAdapter.DB_LASTUPDATE};
        int[] to = new int[]{
                R.id.item_list_date};

        mSimpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_list, null, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        ListView listView = (ListView) getView().findViewById(R.id.item_list_view);
        listView.setAdapter(mSimpleCursorAdapter);

//     アイテムが押下されたときに対する反応を実装する予定は今はなし。そのうち論理削除を導入。
//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               replaceFragment(position);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLoaderManager().destroyLoader(0);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Cursor old = mSimpleCursorAdapter.swapCursor(cursor);
        if (old != null) {
            old.close();
        }
        if (cursor.getCount() == 0) {
            mWeightList = null;
            return;
        }

        mWeightList = new ArrayList<Weight>();
        if (cursor.moveToFirst()) {
            do {
                Weight weight = new Weight();
                weight.getId(cursor.getInt(0));
                weight.getWeight(cursor.getInt(1));
                weight.getLastupdate(cursor.getString(2));
                mWeightList.add(weight);
            } while (cursor.moveToNext());
        }
    }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        Cursor old = mSimpleCursorAdapter.swapCursor(null);
        if (old != null) {
            old.close();
        }
    }
}