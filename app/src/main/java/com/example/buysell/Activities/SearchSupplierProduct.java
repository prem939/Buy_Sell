package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell.R;

public class SearchSupplierProduct extends BaseActivity {
    LinearLayout llSearchSupplierProduct;
    @Override
    public void initialize() {
        llSearchSupplierProduct = (LinearLayout) inflater.inflate(R.layout.serach_supplier_product, null);
        llBody.addView(llSearchSupplierProduct, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Search For Supp/Prod");
    }
}
