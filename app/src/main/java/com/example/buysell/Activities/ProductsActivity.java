package com.example.buysell.Activities;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.ProductsAdaptor;
import com.example.buysell.Do.ProductDo_new;
import com.example.buysell.R;

import java.util.ArrayList;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ProductsActivity extends BaseActivity {
    private LinearLayout llProductScreen;
    private RecyclerView rv_products;
    ProductsAdaptor productsAdaptor;
    @Override
    public void initialize() {
        llProductScreen = (LinearLayout) inflater.inflate(R.layout.product_activity, null);//GODREJ
        llBody.addView(llProductScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Products");

        img_back.setVisibility(View.VISIBLE);
        img_cart.setVisibility(View.GONE);

        rv_products = llProductScreen.findViewById(R.id.rv_products);
        productsAdaptor = new ProductsAdaptor(this,getDummyData());
        rv_products.setLayoutManager(new LinearLayoutManager(this));
        rv_products.setAdapter(productsAdaptor);



//        FloatingActionButton actionA = new FloatingActionButton(getBaseContext());
        FloatingActionButton actionA = llProductScreen.findViewById(R.id.action_a);
        actionA.setTitle("first");
        actionA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, CreateItemActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton actionB = llProductScreen.findViewById(R.id.action_b);
        actionB.setTitle("second");
        actionB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"second",Toast.LENGTH_SHORT).show();
            }
        });
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

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
