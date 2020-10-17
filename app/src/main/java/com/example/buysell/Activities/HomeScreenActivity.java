package com.example.buysell.Activities;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.buysell.R;
import com.example.buysell.Adapters.TabAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeScreenActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout llhome;
    @Override
    public void initialize() {
        llhome = (LinearLayout) inflater.inflate(R.layout.activity_home, null);
        llBody.addView(llhome, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        tabLayout = (TabLayout) llhome.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) llhome.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Book Order"));
        tabLayout.addTab(tabLayout.newTab().setText("Order List"));
        tabLayout.addTab(tabLayout.newTab().setText("Market"));

        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);

        TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

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
    }
}

