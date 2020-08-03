package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.R;

public class SupplierListAdaptor extends RecyclerView.Adapter<SupplierListAdaptor.ViewHolder> {
    Context mcontext;

    public SupplierListAdaptor(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.rv_supplier_list, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_supplier_name.setText("Supplier for Milk");
        holder.txt_to_pay.setText("400"+" ₹");
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_supplier_name,txt_to_pay,txt_order_list,txt_notifs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_supplier_name = itemView.findViewById(R.id.txt_supplier_name);
            txt_to_pay = itemView.findViewById(R.id.txt_to_pay);
//            txt_order_list = itemView.findViewById(R.id.txt_order_list);
//            txt_order_list = itemView.findViewById(R.id.txt_order_list);
        }
    }
}
