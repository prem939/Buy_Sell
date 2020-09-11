package com.example.buysell2.Activities;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buysell2.R;
import com.example.buysell2.common.AppConstants;
import com.example.buysell2.common.Preference;

public class HomeScreenActivity_new extends BaseActivity {
    LinearLayout llhomeScreen;
    TextView txtCust_list, txtSupp_list, txtfav_item_list, txt_so, txt_po;
    private ImageView imgEditUserDetails;
    private String UserType = "Admin";

    @Override
    public void initialize() {
        llhomeScreen = (LinearLayout) inflater.inflate(R.layout.home_screen, null);
        llBody.addView(llhomeScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initialization();

        if (!preference.getStringFromPreference(Preference.TYPE, "").equalsIgnoreCase("")) {
            UserType = preference.getStringFromPreference(Preference.TYPE, "");
        }

        btn_search.setVisibility(View.VISIBLE);
        img_Menu.setVisibility(View.VISIBLE);
        img_back.setVisibility(View.GONE);
        img_cart.setVisibility(View.VISIBLE);

        if (UserType.equalsIgnoreCase("Supplier")) {
            txt_so.setVisibility(View.VISIBLE);
            txtfav_item_list.setVisibility(View.VISIBLE);
        } else {
            txt_so.setVisibility(View.GONE);
            txtfav_item_list.setVisibility(View.GONE);
        }

        imgEditUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity_new.this, EditUserDetailsActivity.class);
                startActivity(intent);
            }
        });

        txtCust_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, SupplierListActivity.class);
                intent.putExtra("from", AppConstants.CUSTOMERLIST);
                startActivity(intent);
            }
        });
        txtSupp_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, SupplierListActivity.class);
                intent.putExtra("from", AppConstants.SUPPLLIERLIST);
                startActivity(intent);
            }
        });
        txtfav_item_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, CartActivity.class);
                intent.putExtra("from", AppConstants.FAVOLIST);
                startActivity(intent);
            }
        });
        txt_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, POpageActivity.class);
                startActivity(intent);
            }
        });
        txt_so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, SoPageActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialization() {

        txtCust_list = llhomeScreen.findViewById(R.id.txtCust_list);
        txtSupp_list = llhomeScreen.findViewById(R.id.txtSupp_list);
        txtfav_item_list = llhomeScreen.findViewById(R.id.txtfav_item_list);
        txt_so = llhomeScreen.findViewById(R.id.txt_so);
        txt_po = llhomeScreen.findViewById(R.id.txt_po);
        imgEditUserDetails = llhomeScreen.findViewById(R.id.imgEditUserDetails);

    }

    @Override
    public void onBackPressed() {
        moveToNextActivity("Log Out");
//        super.onBackPressed();
    }
}
