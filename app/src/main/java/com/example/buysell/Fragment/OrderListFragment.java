package com.example.buysell.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.BookorderAdapter;
import com.example.buysell.Do.ProductDo;
import com.example.buysell.R;

import java.util.ArrayList;
import java.util.List;


public class OrderListFragment extends Fragment {

    //    GridView gridView;
//    String[] MovieName={"Alita","season","Empire"};
//    int[] MovieImage={R.drawable.alita,R.drawable.season_of_the_witch,R.drawable.empire};
    List<ProductDo> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookorderAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_orders, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new BookorderAdapter(getContext(), list);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }
}
