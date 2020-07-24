package com.example.buysell2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SplashScreenActivity extends BaseActivity {
    private LinearLayout llSplash;

    @Override
    public void initialize() {
        llSplash = (LinearLayout) inflater.inflate(R.layout.activity_splash_screen, null);//GODREJ
        llBody.addView(llSplash, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        btnMenu.setVisibility(View.GONE);
        moveToLogin();
    }

    private void moveToLogin() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    Intent i = new Intent(SplashScreenActivity.this, LoginAcivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                    finish();
                }
            }, 3000);
    }
}