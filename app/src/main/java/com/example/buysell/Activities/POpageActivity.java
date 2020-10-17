package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.POpageAdaptor;
import com.example.buysell.R;

public class POpageActivity extends BaseActivity{
    LinearLayout llPoPage;
    RecyclerView rv_po_page;
    POpageAdaptor poPageAdaptor;
    @Override
    public void initialize() {
        llPoPage = (LinearLayout) inflater.inflate(R.layout.po_page, null);
        llBody.addView(llPoPage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("PO Page");

        rv_po_page = llPoPage.findViewById(R.id.rv_po_page);
        poPageAdaptor = new POpageAdaptor(this);
        rv_po_page.setLayoutManager(new LinearLayoutManager(this));
        rv_po_page.setAdapter(poPageAdaptor);
    }
}
