package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.R;

public class BuyerAdapter extends RecyclerView.Adapter<BuyerAdapter.ViewHolder> {
    Context mcontext;

    public BuyerAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public BuyerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.rv_market_buyer, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerAdapter.ViewHolder holder, int position) {
            holder.txt_buyerName.setText("Ram Ram");
            holder.txt_itemName.setText("Agarbathi");
            holder.txt_qty.setText("100");
            holder.txt_quote.setText("Devotion Makes You Perfection");
            holder.txt_total.setText("100");
            holder.txt_shipto.setText("Sai baba Temple");
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_buyerName,txt_itemName,txt_qty,txt_quote,txt_total,txt_shipto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_buyerName = itemView.findViewById(R.id.txt_buyerName);
            txt_itemName = itemView.findViewById(R.id.txt_itemName);
            txt_qty = itemView.findViewById(R.id.txt_qty);
            txt_quote = itemView.findViewById(R.id.txt_quote);
            txt_total = itemView.findViewById(R.id.txt_total);
            txt_shipto = itemView.findViewById(R.id.txt_shipto);
        }
    }
}
