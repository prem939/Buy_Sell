package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell.R;

public class CreateItemActivity extends BaseActivity {
    LinearLayout llcreate_item;
    @Override
    public void initialize() {
        llcreate_item = (LinearLayout) inflater.inflate(R.layout.activity_create_item, null);
        llBody.addView(llcreate_item, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Create Item");
    }
}
