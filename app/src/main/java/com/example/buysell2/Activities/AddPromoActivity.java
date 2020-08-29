package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell2.R;

public class AddPromoActivity extends BaseActivity {
    LinearLayout llCreatePromo;
    @Override
    public void initialize() {
        llCreatePromo = (LinearLayout) inflater.inflate(R.layout.add_promo, null);
        llBody.addView(llCreatePromo, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Add Promo");
    }
}
