package com.example.buysell2.Activities;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.buysell2.R;

public class HomeScreenActivity_new extends BaseActivity {
    LinearLayout llhomeScreen, ll_ct_upd_supp, ll_ct_upd_item, cv_ct_up_promo, ll_ct_up_shipto, ll_c_up_billto;

    @Override
    public void initialize() {
        llhomeScreen = (LinearLayout) inflater.inflate(R.layout.home_screen, null);//GODREJ
        llBody.addView(llhomeScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initialization();
//        llheader.setVisibility(View.GONE);
//        flMenu.setVisibility(View.GONE);
        ll_ct_upd_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, CreateSupplierActivity.class);
                startActivity(intent);
            }
        });
        ll_ct_upd_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity_new.this, CreateItemActivity.class);
                startActivity(intent);
            }
        });
        cv_ct_up_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Not yet Implimented",Toast.LENGTH_SHORT).show();
            }
        });
        ll_ct_up_shipto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Not yet Implimented",Toast.LENGTH_SHORT).show();
            }
        });
        ll_c_up_billto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Not yet Implimented",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialization() {
        ll_ct_upd_supp = llhomeScreen.findViewById(R.id.ll_ct_upd_supp);
        ll_ct_upd_item = llhomeScreen.findViewById(R.id.ll_ct_upd_item);
        cv_ct_up_promo = llhomeScreen.findViewById(R.id.cv_ct_up_promo);
        ll_ct_up_shipto = llhomeScreen.findViewById(R.id.ll_ct_up_shipto);
        ll_c_up_billto = llhomeScreen.findViewById(R.id.ll_c_up_billto);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showCustomDialog(this, getString(R.string.warning), getResources().getString(R.string.do_you_want_to_logout), getString(R.string.OK), null, "logout");
    }
}
