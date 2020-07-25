package com.example.buysell2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.BookorderAdapter;
import com.example.buysell2.Do.ProductDo;
import com.example.buysell2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookorderFragment extends Fragment {

    List<ProductDo> list=new ArrayList<>();

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

      ProductDo productDo =new ProductDo("ThumsUp",R.drawable.thumsup);
      list.add(productDo);
        productDo =new ProductDo("Sprite",R.drawable.sprite);
        list.add(productDo);
        productDo =new ProductDo("Pepsi",R.drawable.pepsi);
        list.add(productDo);
        productDo =new ProductDo("Seven",R.drawable.sevenup);
        list.add(productDo);
    }
}
