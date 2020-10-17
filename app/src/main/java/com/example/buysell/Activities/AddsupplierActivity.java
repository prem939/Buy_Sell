package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell.R;

public class AddsupplierActivity extends BaseActivity {
    LinearLayout lladdsupplier;

    @Override
    public void initialize() {
        lladdsupplier = (LinearLayout) inflater.inflate(R.layout.activity_addsupplier, null);
        llBody.addView(lladdsupplier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
