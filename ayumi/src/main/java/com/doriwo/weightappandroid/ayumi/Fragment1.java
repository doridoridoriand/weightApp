package com.doriwo.weightappandroid.ayumi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rpd on 14/05/30.
 */
public class Fragment1 extends Fragment {

//    private List<Weight> mWeightList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_activity, container, false);
    }
}
