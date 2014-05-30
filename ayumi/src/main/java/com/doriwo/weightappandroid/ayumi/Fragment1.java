package com.doriwo.weightappandroid.ayumi;

import android.app.Fragment;
import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doriwo.weightappandroid.ayumi.db.Weight;

import java.util.List;

/**
 * Created by rpd on 14/05/30.
 */
public class Fragment1 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private List<Weight> mWeightList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_activity, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLoaderManager().destroyLoader(0);
    }

    @Override
    public LOader<Cursor> onCreateLoader(int id, Bundle bundle) {

    }

}
