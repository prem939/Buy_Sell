package com.example.buysell2.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.ProductsAdaptor;
import com.example.buysell2.Do.ProductDo_new;
import com.example.buysell2.R;

import java.util.ArrayList;

public class ProductsActivity extends BaseActivity {
    private LinearLayout llProductScreen;
    private RecyclerView rv_products;
    ProductsAdaptor productsAdaptor;
    @Override
    public void initialize() {
        llProductScreen = (LinearLayout) inflater.inflate(R.layout.product_activity, null);//GODREJ
        llBody.addView(llProductScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Products");
//        llheader.setVisibility(View.GONE);
//        flMenu.setVisibility(View.GONE);

        rv_products = llProductScreen.findViewById(R.id.rv_products);
        productsAdaptor = new ProductsAdaptor(this,getDummyData());
        rv_products.setLayoutManager(new LinearLayoutManager(this));
        rv_products.setAdapter(productsAdaptor);
    }
    public ArrayList<ProductDo_new> getDummyData(){
        ArrayList<ProductDo_new> list_products = new ArrayList<>();
        ProductDo_new productDetials = new ProductDo_new();
        productDetials.setProductName("Product Name");
        productDetials.setProductPrice("15000");
        productDetials.setProductQuantity("15");
        list_products.add(productDetials);
        return list_products;
    }
}
