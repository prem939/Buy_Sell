package com.example.buysell2.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.buysell2.R;

public class LoginActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private EditText edit_username, edit_password;
    private TextView txt_forget_password,txtlogin,txtSignUp;
    private Button btn_login;
    private LinearLayout llLoginActivity;
    private String strUserName,strPassword;
    LinearLayout llLogin,llSignUp;
    private Spinner spinUserType;
    private String UserType[] = {"--select--","Admin","Sales Person","Supplier","Customer"};
    private ArrayAdapter<String> userTypeAdapter ;
    @Override
    public void initialize() {
        llLoginActivity = (LinearLayout) inflater.inflate(R.layout.login_screen, null);
        llBody.addView(llLoginActivity, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llheader.setVisibility(View.GONE);
        flMenu.setVisibility(View.GONE);
        initialization();

        userTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, UserType);
        spinUserType.setAdapter(userTypeAdapter);
        spinUserType.setOnItemSelectedListener(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(edit_password);

                strUserName		= 	edit_username.getText().toString();
                strPassword		=   edit_password.getText().toString();

                if(strUserName.equals("") || strPassword.equals(""))
                {
                    if(strUserName.equals("") && strPassword.equals(""))
                    {
                        showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username_password), getString(R.string.OK), null, "");
                        edit_username.requestFocus();
                    }
                    else if(strUserName.equals(""))
                    {
                        showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username), getString(R.string.OK), null, "");
                        edit_username.requestFocus();
                    }
                    else if(strPassword.equals(""))
                    {
                        showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_password), getString(R.string.OK), null, "");
                        edit_password.requestFocus();
                    }
                }
                else {
                    Intent intent	=	new Intent(LoginActivity.this, HomeScreenActivity_new.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                txtSignUp.setTextColor(getResources().getColor(R.color.lite_green));
                llSignUp.setVisibility(View.GONE);

                txtlogin.setTextColor(getResources().getColor(R.color.white));
                llLogin.setVisibility(View.VISIBLE);
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                txtlogin.setTextColor(getResources().getColor(R.color.lite_green));
                llLogin.setVisibility(View.GONE);

                txtSignUp.setTextColor(getResources().getColor(R.color.white));
                llSignUp.setVisibility(View.VISIBLE);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), "selecteditem" + item, Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void initialization() {
        edit_username = llLoginActivity.findViewById(R.id.edit_username);
        edit_password = llLoginActivity.findViewById(R.id.edit_password);
        txt_forget_password = llLoginActivity.findViewById(R.id.txt_forgot_password);
        btn_login = llLoginActivity.findViewById(R.id.btn_login);
        llLogin = llLoginActivity.findViewById(R.id.lllogin);
        llSignUp = llLoginActivity.findViewById(R.id.llsignup);
        txtlogin = llLoginActivity.findViewById(R.id.txtlogin);
        txtSignUp = llLoginActivity.findViewById(R.id.txtsignup);
        spinUserType = llLoginActivity.findViewById(R.id.spinUserType);
    }
}
