package com.example.buysell2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buysell2.R;

public class OrderListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] MovieName;
    int[] MovieImage;

    public OrderListAdapter(Context context, String[] movieName, int[] movieImage) {

        this.context=context;
        this.MovieImage=movieImage;
        this.MovieName=movieName;

        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return MovieName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView =inflater.inflate(R.layout.movies_item_list,null);
        TextView textMovieName=convertView.findViewById(R.id.movieName);
        ImageView imagewMovie =convertView.findViewById(R.id.movieImage);

        textMovieName.setText(MovieName[position]);
        imagewMovie.setImageResource(MovieImage[position]);

        return convertView;
    }
}
