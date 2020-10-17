package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.CartAdaptor;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;

public class CartActivity extends BaseActivity {
    LinearLayout llCart;
    RecyclerView rvcart;
    CartAdaptor cartAdaptor;

    @Override
    public void initialize() {
        llCart = (LinearLayout) inflater.inflate(R.layout.cart_screen, null);
        llBody.addView(llCart, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText((getIntent().getStringExtra("from").equalsIgnoreCase(AppConstants.CART)) ? "Cart" : "Favour List");

        rvcart = llCart.findViewById(R.id.rvCart);

        cartAdaptor = new CartAdaptor(this);
        rvcart.setLayoutManager(new LinearLayoutManager(this));
        rvcart.setAdapter(cartAdaptor);
    }
}
