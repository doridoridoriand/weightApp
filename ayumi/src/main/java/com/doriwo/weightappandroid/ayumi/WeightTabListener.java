package com.doriwo.weightappandroid.ayumi;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;


/**
 * Created by Dorian on 2014/05/29.
 */
public class WeightTabListener implements ActionBar.TabListener {
    private Fragment mFragment;


    public WeightTabListener(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.add(R.id.fragment_content, mFragment, null);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //NO PROCESS METHOD
    }
}