package com.example.buysell.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.buysell.common.AppConstants;
import com.example.buysell.Do.DashbordDo;
import com.example.buysell.R;
import com.example.buysell.common.CustomDialog;
import com.example.buysell.common.Preference;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity {
    public LayoutInflater inflater;
    public LinearLayout llBody, llheader, llheader2, llSearchBar;
    public Button btnMenu, btn_search;
    public FrameLayout flMenu;
    public DrawerLayout drawerLayout;
    protected DashBoardOptionsCustomAdapter adapter;
    public ListView lvDashBoard;
    public Preference preference;
    public CustomDialog customDialog;
    public TextView txt_head,txt_add;
    public ImageView img_cart, img_Menu, img_back;
    public EditText edtSearch_bar;
    private String UserType = "Admin";

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

        preference = new Preference(getApplicationContext());
        inflater = this.getLayoutInflater();
        initialization();
        if (!preference.getStringFromPreference(Preference.TYPE, "").equalsIgnoreCase("")) {
            UserType = preference.getStringFromPreference(Preference.TYPE, "");
        }

        if (adapter == null) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
                @Override
                public void run() {
                    adapter = new DashBoardOptionsCustomAdapter(AppConstants.admin_menu());
                    lvDashBoard.setAdapter(adapter);
                }
            });
        }

        img_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
//                hideCustomKeyBoard();
                TopBarMenuClick();
                if (adapter != null) {
                    if (UserType.equalsIgnoreCase("Admin") || UserType.equalsIgnoreCase("Supplier")) {
                        adapter.refreshList(AppConstants.admin_menu());
                    } else {
                        adapter.refreshList(AppConstants.customer_menu());
                    }
                }
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, CartActivity.class);
                intent.putExtra("from", AppConstants.CART);
                startActivity(intent);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                img_Menu.setImageResource(R.mipmap.back_arrow);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                img_Menu.setImageResource(R.mipmap.menu);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        initialize();
    }

    private void initialization() {
        llBody = findViewById(R.id.llBody);
        llheader = findViewById(R.id.llheader);
        llheader2 = findViewById(R.id.llheader2);
//        btnMenu = findViewById(R.id.btnMenu);
        img_Menu = findViewById(R.id.img_Menu);
        flMenu = findViewById(R.id.flMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        lvDashBoard = findViewById(R.id.lvDashBoard);
        txt_head = findViewById(R.id.txt_head);
        img_cart = findViewById(R.id.img_cart);
        img_back = findViewById(R.id.img_back);
        btn_search = findViewById(R.id.btn_search);
        edtSearch_bar = findViewById(R.id.edtSearch_bar);
        llSearchBar = findViewById(R.id.llSearchBar);
        txt_add = findViewById(R.id.txt_add);
    }

    public abstract void initialize();

    @SuppressLint("WrongConstant")
    public void closeDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(flMenu);
            img_Menu.setImageResource(R.mipmap.menu);
        }
    }

    /**
     * Method for Top Menu click
     **/
    @SuppressLint("WrongConstant")
    public void TopBarMenuClick() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
            img_Menu.setImageResource(R.mipmap.menu);
        } else {
            drawerLayout.openDrawer(Gravity.START);
            img_Menu.setImageResource(R.mipmap.back_arrow);
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
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DashbordDo dashbordDo = data.get(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.dashbord_option_call, null);
                TextView menuListName = convertView.findViewById(R.id.textViewName);
//                ImageView menulisticon = convertView.findViewById(R.id.img_menuicon);
                menuListName.setText(dashbordDo.getName());
//                menulisticon.setImageResource(dashbordDo.getIcon());
                convertView.setTag(dashbordDo.getName());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToNextActivity(v.getTag().toString());
                    }
                });
            }
            return convertView;
        }

        public void refreshList(List<DashbordDo> data) {
            this.data = (ArrayList<DashbordDo>) data;
            notifyDataSetChanged();
        }

        public void hideKeyBoard(View v) {
            if (v != null && getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    public void moveToNextActivity(String strOptionSelected) {
        moveToNextActivityForCheckInAdmin(strOptionSelected);
    }

    private void moveToNextActivityForCheckInAdmin(String strOptionSelected) {
        closeDrawer();
        if (strOptionSelected.equalsIgnoreCase("Cr/Upd Supplier")) {
            Intent intent = new Intent(BaseActivity.this, CreateSupplierActivity.class);
            intent.putExtra("from", "Create Supplier");
            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase("Cr/Upd Products")) {
            Intent intent = new Intent(BaseActivity.this, CreateItemActivity.class);
            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase("Create Promo")) {
            Intent intent = new Intent(BaseActivity.this, AddPromoActivity.class);
            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase("Cr/Upd Sites")) {
            Toast.makeText(getApplicationContext(), "Not yet Implimented", Toast.LENGTH_SHORT).show();
        } else if (strOptionSelected.equalsIgnoreCase("Supplier items")) {
            Intent intent = new Intent(BaseActivity.this, SupplierItemListActivity.class);
            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase("Log Out")) {
            showCustomDialog(this, getString(R.string.warning), getResources().getString(R.string.do_you_want_to_logout), getString(R.string.OK), getString(R.string.Cancel), "logout");

        } else if (strOptionSelected.equalsIgnoreCase("Cr/Upd Supplier Item")) {
            Intent intent = new Intent(BaseActivity.this, CreateSupplierItemMaster.class);
            intent.putExtra("from",AppConstants.FORCREATESUPPLIERITEM);
            startActivity(intent);
        }

    }

    // For showing Dialog message.
    class RunshowCustomDialogs implements Runnable {
        private String strTitle;// Title of the dialog
        private String strMessage;// Message to be shown in dialog
        private String firstBtnName;
        private String secondBtnName;
        private String from;
        private String params;
        private Object paramateres;
        private boolean isCancelable = false;


        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            if (from != null)
                this.from = from;
            else
                this.from = "";
        }

        @Override
        public void run() {
            if (customDialog != null && customDialog.isShowing())
                customDialog.dismiss();
            View view;

            view = inflater.inflate(R.layout.custom_common_popup, null);

            customDialog = new CustomDialog(BaseActivity.this, view, preference
                    .getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 320) - 60,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            customDialog.setCancelable(isCancelable);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitlePopup);
            TextView tvMessage = (TextView) view.findViewById(R.id.tvMessagePopup);
            Button btnYes = (Button) view.findViewById(R.id.btnYesPopup);
            Button btnNo = (Button) view.findViewById(R.id.btnNoPopup);

            tvTitle.setTypeface(AppConstants.Roboto_Condensed_Bold);
            tvMessage.setTypeface(AppConstants.Roboto_Condensed_Bold);
            btnYes.setTypeface(AppConstants.Roboto_Condensed_Bold);
            btnNo.setTypeface(AppConstants.Roboto_Condensed_Bold);

            tvTitle.setText("" + strTitle);
            tvMessage.setText("" + strMessage);
            btnYes.setText("" + firstBtnName);
            if (secondBtnName.equalsIgnoreCase("")) {
                btnNo.setVisibility(View.GONE);
            } else {
                btnNo.setText("" + secondBtnName);
            }
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                    if (from.equalsIgnoreCase("logout")) {
                        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    if (from.equalsIgnoreCase("finish")) {
                        finish();
                    }
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            try {
                if (!customDialog.isShowing())
                    customDialog.showCustomDialog();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Method to Show the alert dialog
     **/
    public void showCustomDialog(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from) {
        runOnUiThread(new RunshowCustomDialogs(context, strTitle, strMessage, firstBtnName, secondBtnName, from, true));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnectionAvailable = false;
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null)
            isNetworkConnectionAvailable = activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;

        return isNetworkConnectionAvailable;
    }

}
