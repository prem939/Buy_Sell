package com.example.buysell.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Do.ProductDo;
import com.example.buysell.R;

public class BookorderAdapter extends RecyclerView.Adapter<BookorderAdapter.ViewHolder> {

    Context context;
    List<ProductDo> list;

    public BookorderAdapter(Context context, List<ProductDo> list) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        final ProductDo productDo = list.get(position);
        holder.txt_comapany_name.setText("Paradise Hotel");
        holder.txt_price.setText("1500"+"₹");
//        holder.imageView.setImageResource(productDo.getImage());

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_comapany_name, txt_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_comapany_name = itemView.findViewById(R.id.txt_comapany_name);
            txt_price = itemView.findViewById(R.id.txt_price);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
