package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.CartAdaptor;
import com.example.buysell2.Adapters.SupplierListAdaptor;
import com.example.buysell2.R;
import com.example.buysell2.common.AppConstants;

public class SupplierPageActivity extends BaseActivity {
    LinearLayout llSupplierPage;
    RecyclerView rv_supplier_page;
    CartAdaptor cartAdaptor;
    Button btn_cart;
    @Override
    public void initialize() {
        llSupplierPage = (LinearLayout) inflater.inflate(R.layout.supplier_page, null);
        llBody.addView(llSupplierPage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText((getIntent().getStringExtra("from").equalsIgnoreCase(AppConstants.SELLPAGE)) ? View.GONE : View.VISIBLE);

        img_Menu.setImageResource(R.mipmap.back_arrow);
        img_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rv_supplier_page = llSupplierPage.findViewById(R.id.rv_supplier_page);
        btn_cart = llSupplierPage.findViewById(R.id.btn_cart);

        btn_cart.setVisibility((getIntent().getStringExtra("from").equalsIgnoreCase(AppConstants.SELLPAGE)) ? View.GONE : View.VISIBLE);

        cartAdaptor = new CartAdaptor(this);
        rv_supplier_page.setLayoutManager(new LinearLayoutManager(this));
        rv_supplier_page.setAdapter(cartAdaptor);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
