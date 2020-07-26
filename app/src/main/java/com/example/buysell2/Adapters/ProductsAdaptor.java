package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Do.ProductDo_new;
import com.example.buysell2.R;

import java.util.ArrayList;

public class ProductsAdaptor extends RecyclerView.Adapter<ProductsAdaptor.ViewHolder>{
    Context mcontext;
    ArrayList<ProductDo_new> list_product;

    public ProductsAdaptor(Context mcontext, ArrayList<ProductDo_new> list_product) {
        this.mcontext = mcontext;
        this.list_product = list_product;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater.from(mcontext));
        View view=inflater.inflate(R.layout.rv_products,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductDo_new product_details = list_product.get(0);
//        holder.img_productImage.setImageResource();
        holder.txt_productName.setText(""+product_details.getProductName());
        holder.txt_productPrice.setText(""+product_details.getProductPrice());
        holder.txt_productQantity.setText(""+product_details.getProductImage());
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_productImage;
        TextView txt_productName,txt_productPrice,txt_productQantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_productImage=itemView.findViewById(R.id.img_product);
            txt_productName = itemView.findViewById(R.id.txt_productName);
            txt_productPrice = itemView.findViewById(R.id.txt_productPrice);
            txt_productQantity = itemView.findViewById(R.id.txt_productQuantity);
        }
    }
}
