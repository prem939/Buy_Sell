package com.example.buysell2.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell2.R;

public class AddsupplierActivity extends BaseActivity {
    LinearLayout lladdsupplier;

    @Override
    public void initialize() {
        lladdsupplier = (LinearLayout) inflater.inflate(R.layout.activity_addsupplier, null);//GODREJ
        llBody.addView(lladdsupplier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
