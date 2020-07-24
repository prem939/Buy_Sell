package com.example.buysell2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.buysell2.Do.DashbordDo;

import java.util.ArrayList;
import java.util.List;

public abstract  class BaseActivity extends FragmentActivity {
    public LayoutInflater inflater;
    public LinearLayout llBody;
    public Button btnMenu;
    public FrameLayout flMenu;
    public DrawerLayout drawerLayout;
    protected DashBoardOptionsCustomAdapter adapter;
    public ListView lvDashBoard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        inflater = this.getLayoutInflater();
        llBody = findViewById(R.id.llBody);
        btnMenu = findViewById(R.id.btnMenu);
        flMenu = findViewById(R.id.flMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        lvDashBoard = findViewById(R.id.lvDashBoard);
        if(adapter == null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new DashBoardOptionsCustomAdapter(AppConstants.LoadCheckinmenuforAdmin());
                    lvDashBoard.setAdapter(adapter);
                    lvDashBoard.setAdapter(adapter);
                    lvDashBoard.setCacheColorHint(0);
                    lvDashBoard.setScrollBarStyle(0);
                    lvDashBoard.setScrollbarFadingEnabled(true);
                    lvDashBoard.setDividerHeight(1);
                    lvDashBoard.setDivider(getResources().getDrawable(R.drawable.saparetor_dash));

                }
            });

        }

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
//                hideCustomKeyBoard();
                TopBarMenuClick();
                if(adapter != null){
                    adapter.refreshList(AppConstants.LoadCheckinmenuforAdmin());
                }
            }
        });
        initialize();
    }

    public abstract void initialize();

    public void closeDrawer() {
        drawerLayout.closeDrawer(flMenu);
    }

    /**
     * Method for Top Menu click
     **/
    @SuppressLint("WrongConstant")
    public void TopBarMenuClick() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    public void hideKeyBoard(View v) {
        if (v != null && getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public class DashBoardOptionsCustomAdapter extends BaseAdapter {
        ArrayList<DashbordDo> data = null;

        public DashBoardOptionsCustomAdapter(ArrayList<DashbordDo> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DashbordDo dashbordDo = data.get(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.dashbord_option_call, null);
                TextView menuListName = convertView.findViewById(R.id.textViewName);

                menuListName.setText(dashbordDo.getName());

                convertView.setTag(dashbordDo.getName());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),v.getTag().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return convertView;
        }
        public void refreshList(List<DashbordDo> data){
            this.data = (ArrayList<DashbordDo>) data;
            notifyDataSetChanged();
        }
    }
}
