package com.example.buysell2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookorderFragment extends Fragment {

    List<Product> list=new ArrayList<>();

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
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter=new BookorderAdapter(getContext(),list);
        recyclerView.setAdapter(homeAdapter);

        getData();


        return view;
    }

    private void getData() {

      Product product=new Product("ThumsUp",R.drawable.thumsup);
      list.add(product);
        product=new Product("Sprite",R.drawable.sprite);
        list.add(product);
        product=new Product("Pepsi",R.drawable.pepsi);
        list.add(product);
        product=new Product("Seven",R.drawable.sevenup);
        list.add(product);
    }
}
