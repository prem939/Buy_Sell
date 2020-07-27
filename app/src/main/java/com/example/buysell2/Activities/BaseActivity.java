package com.example.buysell2.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.buysell2.Adapters.CartAdaptor;
import com.example.buysell2.common.AppConstants;
import com.example.buysell2.Do.DashbordDo;
import com.example.buysell2.R;
import com.example.buysell2.common.CustomDialog;
import com.example.buysell2.common.Preference;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity {
    public LayoutInflater inflater;
    public LinearLayout llBody, llheader;
    public Button btnMenu;
    public FrameLayout flMenu;
    public DrawerLayout drawerLayout;
    protected DashBoardOptionsCustomAdapter adapter;
    public ListView lvDashBoard;
    public Preference preference;
    public CustomDialog customDialog;
    private String strAdminMenuOption[];
    private String strBuyerMenuOption[];
    private String strSupplierMenuOption[];
    private String strSalesManMenuOption[];
    public TextView txt_head;
    public ImageView img_cart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

        preference = new Preference(getApplicationContext());
        inflater = this.getLayoutInflater();
        initialization();
        if (adapter == null) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
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
                if (adapter != null) {
                    adapter.refreshList(AppConstants.LoadCheckinmenuforAdmin());
                }
            }
        });

        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        initialize();
    }

    private void initialization() {
        llBody = findViewById(R.id.llBody);
        llheader = findViewById(R.id.llheader);
        btnMenu = findViewById(R.id.btnMenu);
        flMenu = findViewById(R.id.flMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        lvDashBoard = findViewById(R.id.lvDashBoard);
        txt_head = findViewById(R.id.txt_head);
        img_cart = findViewById(R.id.img_cart);
        strAdminMenuOption = AppConstants.AdminCheckedInMenuOption;
        strBuyerMenuOption = AppConstants.CustomerCheckedInMenuOption;
        strSalesManMenuOption = AppConstants.SalesPersonCheckedInMenuOption;
        strSupplierMenuOption = AppConstants.SupplierCheckedInMenuOption;
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
            btnMenu.setBackground(getResources().getDrawable(R.drawable.menu));
        } else {
            drawerLayout.openDrawer(Gravity.START);
            btnMenu.setBackground(getResources().getDrawable(R.drawable.back_arrow));
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
                        moveToNextActivity(v.getTag().toString());
//                        Toast.makeText(getApplicationContext(),v.getTag().toString(),Toast.LENGTH_SHORT).show();
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

    private void moveToNextActivity(String strOptionSelected) {
        moveToNextActivityForCheckInAdmin(strOptionSelected);
    }

    private void moveToNextActivityForCheckInAdmin(String strOptionSelected) {
        if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[0]))) {
            Intent intent = new Intent(BaseActivity.this, CreateSupplierActivity.class);
            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[1]))) {
//            Intent intent = new Intent(BaseActivity.this, CheckInOptionActivity.class);
//            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[2]))) {
//            Intent intent = new Intent(BaseActivity.this, CheckInOptionActivity.class);
//            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[3]))) {
//            Intent intent = new Intent(BaseActivity.this, CheckInOptionActivity.class);
//            startActivity(intent);
        } else if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[4]))) {
            Intent intent = new Intent(BaseActivity.this, ProductsActivity.class);
            startActivity(intent);
        }  else if (strOptionSelected.equalsIgnoreCase((strAdminMenuOption[5]))) {
            Intent intent = new Intent(BaseActivity.this, HomeScreenActivity.class);
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

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
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
}
