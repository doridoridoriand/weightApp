package com.ayumi.app.weight.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;



public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_TIME = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new Handler().postDelayed(new Runnawpe() {
            @Override
        public void run() {
                InputActivity.startActivity(InputActivity.this);
                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}

