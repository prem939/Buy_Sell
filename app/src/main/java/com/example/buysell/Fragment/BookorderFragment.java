package com.example.buysell.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.BookorderAdapter;
import com.example.buysell.Do.ProductDo;
import com.example.buysell.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookorderFragment extends Fragment {

    List<ProductDo> list = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookorderAdapter homeAdapter;


    public BookorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
