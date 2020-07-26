package com.example.buysell2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.buysell2.Adapters.MarketTableAdapter;
import com.example.buysell2.R;
import com.google.android.material.tabs.TabLayout;


public class MarketFragment extends Fragment {

    //    String[] SportName={"Vollyball","Football","Cricket","Hockey"};
//    int[] SportImage={R.drawable.vollyboll,R.drawable.football,R.drawable.cricket,R.drawable.hockey};
//    ListView listView;
    TabLayout tabLayout;
    ViewPager viewPager;
    MarketTableAdapter marketTableAdapter;
    FragmentManager fm;
    Context mcontext;

    public MarketFragment() {
//        this.mcontext = mcontext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_market, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Buyers"));
        tabLayout.addTab(tabLayout.newTab().setText("Sellers"));
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);

        marketTableAdapter = new MarketTableAdapter(getChildFragmentManager(),mcontext,tabLayout.getTabCount());
        viewPager.setAdapter(marketTableAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
