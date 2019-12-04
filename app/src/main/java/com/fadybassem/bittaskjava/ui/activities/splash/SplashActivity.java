package com.fadybassem.bittaskjava.ui.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fadybassem.bittaskjava.R;
import com.fadybassem.bittaskjava.ui.activities.base.ActivityBase;
import com.fadybassem.bittaskjava.ui.activities.main.MainActivity;

public class SplashActivity extends ActivityBase {

    private Context context = this;

    //private static int splash_time = 3000;
    private static int splash_time = 1000;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();
        }, splash_time);

    }
}
