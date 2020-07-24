package com.example.buysell2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MarketAdpter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] SportName;
    int[] SportImage;

    public MarketAdpter(Context context, String[] sportName, int[] sportImage) {

        this.context=context;
        this.SportName=sportName;
        this.SportImage=sportImage;

        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return SportName.length;
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

        convertView =inflater.inflate(R.layout.sports_item_list,null);
        TextView textName=convertView.findViewById(R.id.sportName);
        ImageView imageview =convertView.findViewById(R.id.sportImage);

        textName.setText(SportName[position]);
        imageview.setImageResource(SportImage[position]);

        return convertView;
    }
}
