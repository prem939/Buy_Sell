package com.example.buysell2.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.ProductsAdaptor;
import com.example.buysell2.Do.ProductDo_new;
import com.example.buysell2.R;

import java.util.ArrayList;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
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
//        llheader.setVisibility(View.GONE);
//        flMenu.setVisibility(View.GONE);

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
                Toast.makeText(getApplicationContext(),"first",Toast.LENGTH_SHORT).show();
            }
        });

//        FloatingActionButton actionB = new FloatingActionButton(getBaseContext());
        FloatingActionButton actionB = llProductScreen.findViewById(R.id.action_b);
        actionB.setTitle("second");
        actionB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"second",Toast.LENGTH_SHORT).show();
            }
        });
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
//        menuMultipleActions.addButton(actionA);
//        menuMultipleActions.addButton(actionB);

//        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
//        drawable.getPaint().setColor(getResources().getColor(R.color.white));

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
