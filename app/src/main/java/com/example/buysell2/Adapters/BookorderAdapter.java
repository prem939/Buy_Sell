package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Do.ProductDo;
import com.example.buysell2.R;

public class BookorderAdapter extends RecyclerView.Adapter<BookorderAdapter.ViewHolder> {

    Context context;
    List<ProductDo>list;

    public BookorderAdapter(Context context, List<ProductDo> list) {

        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater.from(context));
        View view=inflater.inflate(R.layout.item_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final ProductDo productDo =list.get(position);
        holder.textView.setText(productDo.getName());
        holder.imageView.setImageResource(productDo.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textName);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
