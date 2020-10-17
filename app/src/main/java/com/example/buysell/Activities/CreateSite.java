package com.example.buysell.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.buysell.R;

public class CreateSite extends BaseActivity {
    LinearLayout llCreateSite,ll_bill_to_address;
    ImageView img_check;
    boolean flag = false;
    @Override
    public void initialize() {
        llCreateSite = (LinearLayout) inflater.inflate(R.layout.site_screen, null);//GODREJ
        llBody.addView(llCreateSite, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Create Site");

        img_check = llCreateSite.findViewById(R.id.img_check);
        initilization();
        img_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag)
                {
                    img_check.setImageResource(R.drawable.check);
                    flag=false;
                    ll_bill_to_address.setVisibility(View.VISIBLE);
                }
                else
                {
                    img_check.setImageResource(R.drawable.uncheck);
                    flag=true;
                    ll_bill_to_address.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initilization() {
        ll_bill_to_address = llCreateSite.findViewById(R.id.ll_bill_to_address);
        img_check = llCreateSite.findViewById(R.id.img_check);
    }
}
