package com.example.buysell2.Activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buysell2.R;

public class CreateSupplierActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    Spinner spin_country;
    String[] CountryNames = {"--Select--", "INDIA", "PAKISTAN", "CHINA", "SRILANKA", "BANGLADESH", "USA", "UK"};
    ArrayAdapter<String> CAdapter;
    LinearLayout llcreate_supplier;

    @Override
    public void initialize() {
        llcreate_supplier = (LinearLayout) inflater.inflate(R.layout.activity_create_supplier, null);//GODREJ
        llBody.addView(llcreate_supplier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        btnMenu.setVisibility(View.GONE);
        txt_head.setText("Create Supplier");
        spin_country = findViewById(R.id.spin_country);
        CAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, CountryNames);
        spin_country.setAdapter(CAdapter);
        spin_country.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), "selecteditem" + item, Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
}

