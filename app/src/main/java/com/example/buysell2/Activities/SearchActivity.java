package com.example.buysell2.Activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.buysell2.R;

public class SearchActivity extends BaseActivity {
    LinearLayout ll_search;
    @Override
    public void initialize() {
        ll_search = (LinearLayout)  inflater.inflate(R.layout.search_screen,null);
        llBody.addView(ll_search, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        edtSearch_bar.setVisibility(View.VISIBLE);
        llheader2.setVisibility(View.GONE);

        edtSearch_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (view != null && getCurrentFocus() != null) {
//                    InputMethodManager im = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
//                    im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//                }
            }
        });
    }
}
