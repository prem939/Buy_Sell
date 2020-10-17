package com.example.buysell.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.buysell.Fragment.BookorderFragment;
import com.example.buysell.Fragment.MarketFragment;
import com.example.buysell.Fragment.OrderListFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    FragmentManager fm ;
    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext=context;
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BookorderFragment bookorderFragment = new BookorderFragment();
                return bookorderFragment;
            case 1:
                OrderListFragment movieFragment = new OrderListFragment();
                return movieFragment;
            case 2:
                MarketFragment sportFragment = new MarketFragment();
                return sportFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
