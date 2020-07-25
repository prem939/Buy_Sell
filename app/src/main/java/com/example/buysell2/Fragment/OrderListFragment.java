package com.example.buysell2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.buysell2.Adapters.OrderListAdapter;
import com.example.buysell2.R;


public class OrderListFragment extends Fragment {

    GridView gridView;
    String[] MovieName={"Alita","season","Empire"};
    int[] MovieImage={R.drawable.alita,R.drawable.season_of_the_witch,R.drawable.empire};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_movie, container, false);

        gridView=view.findViewById(R.id.movieGrid);
        OrderListAdapter movieAdapter=new OrderListAdapter(getContext(),MovieName,MovieImage);
        gridView.setAdapter(movieAdapter);

        return view;
    }
}
