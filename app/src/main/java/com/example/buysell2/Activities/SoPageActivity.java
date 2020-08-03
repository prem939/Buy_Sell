package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.POpageAdaptor;
import com.example.buysell2.R;

public class SoPageActivity extends BaseActivity {
    LinearLayout llPoPage;
    RecyclerView rv_po_page;
    POpageAdaptor poPageAdaptor;
    @Override
    public void initialize() {
        llPoPage = (LinearLayout) inflater.inflate(R.layout.po_page, null);
        llBody.addView(llPoPage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("So Page");
        img_Menu.setImageResource(R.mipmap.back_arrow);
        img_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rv_po_page = llPoPage.findViewById(R.id.rv_po_page);
        poPageAdaptor = new POpageAdaptor(this);
        rv_po_page.setLayoutManager(new LinearLayoutManager(this));
        rv_po_page.setAdapter(poPageAdaptor);
    }
}
