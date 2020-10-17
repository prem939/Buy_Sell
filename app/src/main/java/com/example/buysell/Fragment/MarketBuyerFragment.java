package com.example.buysell.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buysell.Adapters.BuyerAdapter;
import com.example.buysell.R;

public class MarketBuyerFragment extends Fragment {
    RecyclerView rvBuyer;
    BuyerAdapter buyerAdapter;
//    Context context;

    public MarketBuyerFragment() {
//        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buyers_screen, container, false);
        rvBuyer = view.findViewById(R.id.rv_market_buyer);

        buyerAdapter = new BuyerAdapter(getActivity());
        rvBuyer.setHasFixedSize(true);
        rvBuyer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBuyer.setAdapter(buyerAdapter);
        return view;
    }
}
