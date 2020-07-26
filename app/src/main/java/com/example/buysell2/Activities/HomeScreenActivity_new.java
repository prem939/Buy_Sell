package com.example.buysell2.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell2.R;

public class HomeScreenActivity_new extends BaseActivity {
    LinearLayout llhomeScreen;
    @Override
    public void initialize() {
        llhomeScreen = (LinearLayout) inflater.inflate(R.layout.home_screen, null);//GODREJ
        llBody.addView(llhomeScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        llheader.setVisibility(View.GONE);
//        flMenu.setVisibility(View.GONE);

    }
}
