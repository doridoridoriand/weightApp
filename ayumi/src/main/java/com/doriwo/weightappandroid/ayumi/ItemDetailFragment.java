package com.doriwo.weightappandroid.ayumi;

import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 2014/06/20.
 */
public class ItemDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private List<Weight> mWeights;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VIew view = inflater.inflate(R.layout.weight_item_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyVIew();
        getLoaderManager().destroyLoader(0);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        Bundle b = this.getArguments();
        Uri uri = Uri.parse(ParameterManager.Parameters.CONTENT_URI + "/" + b.getString("mass"));
        return new CursorLoader(this.getActivity(), Uri, null, null, null, null, null,);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.getCount() == 0) {
            mWeights = null;
            return;
        }
        mWeights = new ArrayList<Weight>();
        if (cursor.moveToFirst()) {
            do {
                Weight weight = new Weight();
                weight.set_id(cursor.getInt(0));
                weight.set_weightmass(cursor.getString(1));
                weight.set_lastupdate(cursor.getString(2));
                mWeights.add(weight);
            } while (cursor.moveToNext());
        }
         viewSet();
    }

    private void viewSet() {
        Weight weight = mWeights.get(0);

        TextView view = (TextView) getText().findViewById(R.id.weight_item_detail_id);
        view.setText("Data Detail\n\nid="+ weight.get_id());
    }
}
