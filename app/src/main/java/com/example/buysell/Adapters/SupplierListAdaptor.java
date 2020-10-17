package com.example.buysell.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.R;

import java.util.List;

public class SupplierListAdaptor extends RecyclerView.Adapter<SupplierListAdaptor.ViewHolder> {
    private Context mcontext;
    private List<SupplierItemMasterDo> SupplierListItems;

    public SupplierListAdaptor(Context mcontext, List<SupplierItemMasterDo> supplierListItems) {
        this.mcontext = mcontext;
        SupplierListItems = supplierListItems;
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
        final SupplierItemMasterDo supplierItemMasterDo = SupplierListItems.get(position);
        if(supplierItemMasterDo!=null){
            holder.txt_supplier_name.setText(supplierItemMasterDo.SIM_IT_Name);
            holder.txt_to_pay.setText("Rs: "+supplierItemMasterDo.SIM_IT_Price);
        }
    }

    @Override
    public int getItemCount() {
        return SupplierListItems.size();
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
