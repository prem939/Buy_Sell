package com.example.buysell2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell2.Adapters.BuyerAdapter;
import com.example.buysell2.Adapters.SellerAdapter;
import com.example.buysell2.R;

public class MarketSellerFragment extends Fragment {
    RecyclerView rvseller;
    SellerAdapter sellerAdapter;
//    Context context;

    public MarketSellerFragment() {
//        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seller_screen, container, false);
        rvseller = view.findViewById(R.id.rv_market_seller);
        sellerAdapter =  new SellerAdapter(getActivity());
        rvseller.setHasFixedSize(true);
        rvseller.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvseller.setAdapter(sellerAdapter);
        return view;
    }
}
