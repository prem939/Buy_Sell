package com.example.buysell2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.buysell2.Adapters.MarketAdpter;
import com.example.buysell2.R;


public class MarketFragment extends Fragment {

    String[] SportName={"Vollyball","Football","Cricket","Hockey"};
    int[] SportImage={R.drawable.vollyboll,R.drawable.football,R.drawable.cricket,R.drawable.hockey};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_sports, container, false);

        listView=view.findViewById(R.id.sportsListview);
        MarketAdpter sportAdpter=new MarketAdpter(getContext(),SportName,SportImage);
        listView.setAdapter(sportAdpter);
        return view;
    }
}
