package com.example.buysell2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends BaseActivity {
TextView sign_up,Tlogin;
Button Login;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_up=findViewById(R.id.signup);
        Login=findViewById(R.id.login);
        Tlogin=findViewById(R.id.tlogin);
        Tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t_login= new Intent(MainActivity.this,MainActivity.class);
                startActivity(t_login);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_login=new Intent(MainActivity.this,Home.class);
                startActivity(i_login);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_signup=new Intent(MainActivity.this,Signup.class);
               startActivity(i_signup);
            }
        });
    }

    @Override
    public void initialize() {

    }
}
