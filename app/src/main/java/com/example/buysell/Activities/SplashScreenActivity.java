package com.example.buysell.Activities;

import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell.R;
import com.example.buysell.common.Preference;

public class SplashScreenActivity extends BaseActivity {
    private LinearLayout llSplash;
    public Preference preference;

    @Override
    public void initialize() {
        llSplash = (LinearLayout) inflater.inflate(R.layout.activity_splash_screen, null);
        llBody.addView(llSplash, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llheader.setVisibility(View.GONE);
        flMenu.setVisibility(View.GONE);
        initializeControlls();
        moveToLogin();
    }

    private void moveToLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                finish();
            }
        }, 3000);
    }

    public void initializeControlls() {
        preference = new Preference(getApplicationContext());
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        preference.saveIntInPreference(Preference.DEVICE_DISPLAY_WIDTH, displaymetrics.widthPixels);
        preference.saveIntInPreference(Preference.DEVICE_DISPLAY_HEIGHT, displaymetrics.heightPixels);
        preference.commitPreference();
    }
}