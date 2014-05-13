package com.ayumi.app.weight.ui;


import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_TIME = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);

        new Handler().postDelayed(new Runnable() {
            @Override
        public void run() {
                InputActivity.startActivity(SplashActivity.this);
                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}

