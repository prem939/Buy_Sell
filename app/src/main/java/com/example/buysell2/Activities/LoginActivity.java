package com.example.buysell2.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buysell2.ApiServices;
import com.example.buysell2.Do.UserDo;
import com.example.buysell2.R;
import com.example.buysell2.common.ServiceURLs;
import com.google.gson.Gson;

import java.io.Serializable;


public class LoginActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private EditText edit_username, edit_password;
    private TextView txt_forget_password, txtlogin, txtSignUp;
    private Button btn_login;
    private LinearLayout llLoginActivity;
    private String strUserName, strPassword;
    LinearLayout llLogin, llSignUp;
    private Spinner spinUserType;
    private ProgressBar process_bar;
    private String UserType[] = {"--select--", "Admin", "Sales Person", "Supplier", "Customer"}, StringJson = "", API_LOGIN = "";
    private ArrayAdapter<String> userTypeAdapter;
    ApiServices apiServices = new ApiServices();

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

                strUserName = edit_username.getText().toString();
                strPassword = edit_password.getText().toString();

                if (strUserName.equals("") || strPassword.equals("")) {
                    validateLogin(strUserName, strPassword);
                } else {
                    if (isNetworkConnectionAvailable(LoginActivity.this)) {
                        API_LOGIN = ServiceURLs.ALL_USERS + "?id=" + (Integer.parseInt(strUserName) - 1234567889);
                        syncTaskForLogin runner = new syncTaskForLogin();
                        runner.execute();
                    } else
                        showCustomDialog(LoginActivity.this, getString(R.string.warning), "Could not connect to Internet", getString(R.string.OK), "", "");

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


    private void validateLogin(String username, String password) {
        if (strUserName.equals("") && strPassword.equals("")) {
            showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username_password), getString(R.string.OK), "", "");
            edit_username.requestFocus();
        } else if (strUserName.equals("")) {
            showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username), getString(R.string.OK), "", "");
            edit_username.requestFocus();
        } else if (strPassword.equals("")) {
            showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_password), getString(R.string.OK), "", "");
            edit_password.requestFocus();
        }
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
        process_bar = llLoginActivity.findViewById(R.id.process_bar);
    }

    public class syncTaskForLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            StringJson = apiServices.getDataForSingleUser(API_LOGIN, strUserName, strPassword);
            Gson gson = new Gson();
            if (StringJson != null) {
                UserDo userDo = gson.fromJson(StringJson, UserDo.class);
                if (userDo.UP_Password.equalsIgnoreCase(strPassword) || !userDo.UP_Password.equalsIgnoreCase("") || userDo.UP_Password != null) {
                    Intent intent = new Intent(LoginActivity.this, HomeScreenActivity_new.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoginUserData", userDo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            } else {
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "UserName or Password is wrong", getString(R.string.OK), "", "");
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            process_bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            process_bar.setVisibility(View.GONE);
        }
    }
}
