package com.example.buysell2.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.buysell2.Fragment.BookorderFragment;
import com.example.buysell2.Fragment.MarketBuyerFragment;
import com.example.buysell2.Fragment.MarketSellerFragment;
import com.example.buysell2.Fragment.OrderListFragment;

public class MarketTableAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public MarketTableAdapter(@NonNull FragmentManager fm, Context myContext, int totalTabs) {
        super(fm);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MarketBuyerFragment marketBuyerFragment = new MarketBuyerFragment();
                return marketBuyerFragment;
            case 1:
                MarketSellerFragment marketSellerFragment = new MarketSellerFragment();
                return marketSellerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
