package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.SupplierListAdaptor;
import com.example.buysell2.R;
import com.example.buysell2.common.AppConstants;

public class SupplierListActivity extends BaseActivity {
    LinearLayout llSupplierList;
    RecyclerView rv_supplier_list;
    SupplierListAdaptor supplierListAdaptor;
    @Override
    public void initialize() {
        llSupplierList = (LinearLayout) inflater.inflate(R.layout.supplier_list, null);
        llBody.addView(llSupplierList, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        txt_head.setText((getIntent().getStringExtra("from").equalsIgnoreCase(AppConstants.CUSTOMERLIST)) ? "Customer List" : "Supplier List");

        rv_supplier_list =  llSupplierList.findViewById(R.id.rv_supplier_list);
        supplierListAdaptor = new SupplierListAdaptor(this);
        rv_supplier_list.setLayoutManager(new LinearLayoutManager(this));
        rv_supplier_list.setAdapter(supplierListAdaptor);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
