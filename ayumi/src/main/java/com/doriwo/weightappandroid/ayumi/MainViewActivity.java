package com.doriwo.weightappandroid.ayumi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar mActionBar = getActionBar();

        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.setDisplayShowTitleEnabled(false);

        Fragment mFragment1 = new Fragment1();
        ActionBar.Tab tab1 = mActionBar.newTab();
        tab1.setText("体重入力");
        tab1.setTabListener(new WeightTabListener(mFragment1));
        mActionBar.addTab(tab1);

        Fragment mFragment2 = new Fragment2();
        ActionBar.Tab tab2 = mActionBar.newTab();
        tab2.setText("結果リスト表示");
        tab2.setTabListener(new WeightTabListener(mFragment2));
        mActionBar.addTab(tab2);

        Fragment mFragment3 = new Fragment3();
        ActionBar.Tab tab3 = mActionBar.newTab();
        tab3.setText("グラフ表示");
        tab3.setTabListener(new WeightTabListener(mFragment3));
        mActionBar.addTab(tab3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_activity, container, false);
    }
}

class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_item_list_activity, container, false);
    }
}

class Fragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph_view_activity, container, false);
    }
}