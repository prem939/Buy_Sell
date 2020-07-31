package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.CartAdaptor;
import com.example.buysell2.R;

public class CartActivity extends BaseActivity {
    LinearLayout llCart;
    RecyclerView rvcart;
    CartAdaptor cartAdaptor;

    @Override
    public void initialize() {
        llCart = (LinearLayout) inflater.inflate(R.layout.cart_screen, null);
        llBody.addView(llCart, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Cart");

        img_cart.setVisibility(View.GONE);
        rvcart = llCart.findViewById(R.id.rvCart);

        cartAdaptor = new CartAdaptor(this);
        rvcart.setLayoutManager(new LinearLayoutManager(this));
        rvcart.setAdapter(cartAdaptor);
    }
}
