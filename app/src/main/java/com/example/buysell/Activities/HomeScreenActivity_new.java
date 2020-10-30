package com.example.buysell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.buysell.Do.UserDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.Preference;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity_new extends BaseActivity {
    private LinearLayout llhomeScreen,llAdmin,llSupplier,llBuyer;
    private TextView txtCust_list, txtSupp_list, txtfav_item_list, txt_so, txt_po,txtOrders,txtType;
    private ImageView imgEditUserDetails;
    private String UserType = "Admin";
    private UserDo user= null;
    private AnyChartView anyChartView;
    @Override
    public void initialize() {
        llhomeScreen = (LinearLayout) inflater.inflate(R.layout.home_screen, null);
        llBody.addView(llhomeScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initialization();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            user = (UserDo) bundle.get("LoginUserData");
            UserType = user.UP_User_Type;
            txtType.setText(UserType);
        }
        btn_search.setVisibility(View.VISIBLE);
        img_Menu.setVisibility(View.VISIBLE);
        img_back.setVisibility(View.GONE);
        img_cart.setVisibility(View.VISIBLE);

        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));


//        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry("Open Po's", 6371664));
//        data.add(new ValueDataEntry("To Receive", 789622));
//        data.add(new ValueDataEntry("To Pay", 7216301));
//        data.add(new ValueDataEntry("Open So's", 1486621));
//        data.add(new ValueDataEntry("Oranges", 1200000));
//

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry(getResources().getString(R.string.open_pos), 25));
        data.add(new ValueDataEntry(getResources().getString(R.string.to_receive), 25));
        data.add(new ValueDataEntry(getResources().getString(R.string.to_pay), 25));
        data.add(new ValueDataEntry(getResources().getString(R.string.open_sos), 25));

        pie.data(data);
        pie.labels().position("outside");
        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);

        if (UserType.equalsIgnoreCase("Supplier")) {
            llAdmin.setVisibility(View.GONE);
            llBuyer.setVisibility(View.GONE);
            llSupplier.setVisibility(View.VISIBLE);

            txt_so.setVisibility(View.VISIBLE);
            txtOrders.setVisibility(View.GONE);
            txtCust_list.setVisibility(View.VISIBLE);
            txt_po.setVisibility(View.VISIBLE);
        } else if(UserType.equalsIgnoreCase("Admin")){
            llAdmin.setVisibility(View.VISIBLE);
            llBuyer.setVisibility(View.GONE);
            llSupplier.setVisibility(View.GONE);

            txt_so.setVisibility(View.GONE);
            txtOrders.setVisibility(View.VISIBLE);
            txtCust_list.setVisibility(View.GONE);
            txt_po.setVisibility(View.GONE);
        }else if(UserType.equalsIgnoreCase("Customer")){
            llAdmin.setVisibility(View.GONE);
            llBuyer.setVisibility(View.VISIBLE);
            llSupplier.setVisibility(View.GONE);

            txt_so.setVisibility(View.GONE);
            txtOrders.setVisibility(View.VISIBLE);
            txtCust_list.setVisibility(View.GONE);
            txt_po.setVisibility(View.GONE);
        }

        imgEditUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity_new.this, EditUserDetailsActivity.class);
                intent.putExtra("LoginUserData", user);
                startActivity(intent);
            }
        });

        txtCust_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtSupp_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(HomeScreenActivity_new.this,SupplierListActivity.class);
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
        txtOrders = llhomeScreen.findViewById(R.id.txtOrders);
        txtType = llhomeScreen.findViewById(R.id.txtType);
        llAdmin = llhomeScreen.findViewById(R.id.llAdmin);
        llBuyer = llhomeScreen.findViewById(R.id.llBuyer);
        llSupplier = llhomeScreen.findViewById(R.id.llSupplier);

    }

    @Override
    public void onBackPressed() {
        moveToNextActivity("Log Out");
//        super.onBackPressed();
    }
}
