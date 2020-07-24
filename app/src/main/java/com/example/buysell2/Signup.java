package com.example.buysell2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner Country_Spinner;
    String[] CountryNames={"--Select--","INDIA","PAKISTAN","CHINA","SRILANKA","BANGLADESH","USA","UK"};
    ArrayAdapter<String> CountriesAdapter;
    TextView s_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        s_login=findViewById(R.id.slogin);
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_slogin=new Intent(Signup.this,MainActivity.class);
                startActivity(i_slogin);
            }
        });
        Country_Spinner=findViewById(R.id.Country_Spinner);
        CountriesAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,CountryNames);
        Country_Spinner.setAdapter(CountriesAdapter);
        Country_Spinner.setOnItemSelectedListener(this);


    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"selecteditem"+item,Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
}
