package com.example.buysell2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Create_supplier extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner CountrySpinner;
    String[] CountryNames={"--Select--","INDIA","PAKISTAN","CHINA","SRILANKA","BANGLADESH","USA","UK"};
    ArrayAdapter<String> CAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_supplier);
        CountrySpinner=findViewById(R.id.CSpinner);
        CAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,CountryNames);
        CountrySpinner.setAdapter(CAdapter);
        CountrySpinner.setOnItemSelectedListener(this);

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"selecteditem"+item,Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
    }

