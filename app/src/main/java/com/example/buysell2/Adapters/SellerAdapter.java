package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.R;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ViewHolder> {
    Context mcontext;

    public SellerAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.rv_market_seller, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txt_supplier.setText("Ram Sai");
            holder.txt_item.setText("He Knows");
            holder.txt_mktPrice.setText("50");
            holder.txt_disc.setText(" ");
            holder.txt_totalAmt.setText("50");
            holder.txt_location.setText("Ram Krishna Mattam");
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_supplier,txt_item,txt_mktPrice,txt_disc,txt_totalAmt,txt_select,txt_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_supplier = itemView.findViewById(R.id.txt_supplier);
            txt_item = itemView.findViewById(R.id.txt_item);
            txt_mktPrice = itemView.findViewById(R.id.txt_mktPrice);
            txt_disc = itemView.findViewById(R.id.txt_disc);
            txt_totalAmt = itemView.findViewById(R.id.txt_totalAmt);
            txt_select = itemView.findViewById(R.id.txt_select);
            txt_location = itemView.findViewById(R.id.txt_location);
        }
    }
}
