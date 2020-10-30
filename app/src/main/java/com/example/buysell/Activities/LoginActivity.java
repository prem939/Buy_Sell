package com.example.buysell.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buysell.ApiServices;
import com.example.buysell.Do.UserDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.Preference;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.util.Collections;


public class LoginActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private EditText edit_userId, edit_password, etFirstName, etLastName, etEmail, etPhone, etPassword, etId;
    private TextView txt_forget_password, txtlogin, txtSignUp;
    private Button btn_login, btnCreateAccount;
    private LinearLayout llLoginActivity;
    private String strUserId = "", strPassword = "", strFname = "", strLname = "", strEmail = "", strPhoneNumber = "", strPasswd = "", strType = "", strId = "";
    LinearLayout llLogin, llSignUp;
    private Spinner spinUserType;
    private String UserType[] = {"Admin", "Supplier", "Customer"}, StringJson = "", API_LOGIN = "", emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",stringToken="",strResponce="";
    private ArrayAdapter<String> userTypeAdapter;
    ApiServices apiServices = new ApiServices();
    private CShowProgress cShowProgress;
    private CheckBox pwdCheckbox;
    private ImageView imgChangePassword;
    private boolean show = false;

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

                strUserId = edit_userId.getText().toString();
                strPassword = edit_password.getText().toString();

                if (strUserId.equals("") || strPassword.equals("")) {
                    validateLogin();
                } else {
                    if (isNetworkConnectionAvailable(LoginActivity.this)) {
                        syncTaskForLogin runner = new syncTaskForLogin();
                        runner.execute();
                    } else
                        showCustomDialog(LoginActivity.this, getString(R.string.warning), "Connect to Internet", getString(R.string.OK), "", "");

                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strFname = etFirstName.getText().toString();
                strLname = etLastName.getText().toString();
                strEmail = etEmail.getText().toString();
                strPhoneNumber = etPhone.getText().toString();
                strPasswd = etPassword.getText().toString();
                if (spinUserType.getSelectedItem().toString().equalsIgnoreCase("Sales Person")) {
                    strType = "Salesperson";
                } else {
                    strType = spinUserType.getSelectedItem().toString();
                }
                if(strFname.equalsIgnoreCase("")){
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "First name need not be empty.", getString(R.string.OK), "", "");
                }else if(strLname.equalsIgnoreCase("")){
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Last name need not be empty.", getString(R.string.OK), "", "");
                }else if(strPhoneNumber.equalsIgnoreCase("")){
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Phone no need not be empty.", getString(R.string.OK), "", "");
                }else if(strPasswd.equalsIgnoreCase("")){
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Enter the password", getString(R.string.OK), "", "");
                }else if(strPhoneNumber.length() < 10){
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Enter valid phone number", getString(R.string.OK), "", "");
                } else if (!strEmail.equalsIgnoreCase("") && !strEmail.trim().matches(emailPattern)) {
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Mail address is not a valid one", getString(R.string.OK), "", "");
                } else if (isNetworkConnectionAvailable(LoginActivity.this)) {
                    syncTaskForCreateAccount syncTaskForCreateAccount = new syncTaskForCreateAccount();
                    syncTaskForCreateAccount.execute();
                } else {
                    showCustomDialog(LoginActivity.this, getString(R.string.warning), "Connect to Internet", getString(R.string.OK), "", "");
                }

            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                clearLoginEditTest();
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
                clearSignInEdittest();
                txtlogin.setTextColor(getResources().getColor(R.color.lite_green));
                llLogin.setVisibility(View.GONE);

                txtSignUp.setTextColor(getResources().getColor(R.color.white));
                llSignUp.setVisibility(View.VISIBLE);
            }
        });
        pwdCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        imgChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!show) {
                    imgChangePassword.setImageDrawable(getResources().getDrawable(R.drawable.see_icon));
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show = true;
                } else {
                    show = false;
                    imgChangePassword.setImageDrawable(getResources().getDrawable(R.drawable.not_see_icon));
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void validateLogin() {
        if (strUserId.equals("") && strPassword.equals("")) {
            showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username_password), getString(R.string.OK), "", "");
            edit_userId.requestFocus();
        } else if (strUserId.equals("")) {
            showCustomDialog(LoginActivity.this, getString(R.string.warning), getString(R.string.enter_username), getString(R.string.OK), "", "");
            edit_userId.requestFocus();
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
        edit_userId = llLoginActivity.findViewById(R.id.edit_userId);
        edit_password = llLoginActivity.findViewById(R.id.edit_password);
        txt_forget_password = llLoginActivity.findViewById(R.id.txt_forgot_password);
        btn_login = llLoginActivity.findViewById(R.id.btn_login);
        llLogin = llLoginActivity.findViewById(R.id.lllogin);
        llSignUp = llLoginActivity.findViewById(R.id.llsignup);
        txtlogin = llLoginActivity.findViewById(R.id.txtlogin);
        txtSignUp = llLoginActivity.findViewById(R.id.txtsignup);
        spinUserType = llLoginActivity.findViewById(R.id.spinUserType);
        btnCreateAccount = llLoginActivity.findViewById(R.id.btnCreateAccount);
        etFirstName = llLoginActivity.findViewById(R.id.etFirstName);
        etLastName = llLoginActivity.findViewById(R.id.etLastName);
        etEmail = llLoginActivity.findViewById(R.id.etEmail);
        etPhone = llLoginActivity.findViewById(R.id.etPhone);
        etPassword = llLoginActivity.findViewById(R.id.etPassword);
        pwdCheckbox = llLoginActivity.findViewById(R.id.pwdCheckbox);
        imgChangePassword = llLoginActivity.findViewById(R.id.imgChangePassword);
    }

    public class syncTaskForLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            stringToken = apiServices.getLoginToken(JsontoStringForLogin(strUserId,strPassword));
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(LoginActivity.this);
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            cShowProgress.hideProgress();

            if(stringToken.equalsIgnoreCase(AppConstants.INTERNAL_ERROR) || stringToken.equalsIgnoreCase(AppConstants.INVALID_USERID_PASS)){
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Invalid UserId or Password", getString(R.string.OK), "", "");
            }else if (isNetworkConnectionAvailable(LoginActivity.this)){
                preference.saveStringInPreference(AppConstants.LOGIN_TOKE,stringToken.substring(1,stringToken.length()-1));
                preference.commitPreference();
                syncTaskForGetUserProfile syncTaskForGetUserProfile = new syncTaskForGetUserProfile();
                syncTaskForGetUserProfile.execute();
            }else{
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Connect to Internet", getString(R.string.OK), "", "");
            }
        }
    }
    public class syncTaskForGetUserProfile extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            strResponce = apiServices.getDataForSingleUser(strUserId);
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(LoginActivity.this);
        }
        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            cShowProgress.hideProgress();
            UserDo userDo = null;
            if (strResponce != null && !strResponce.equalsIgnoreCase("")) {
                try {
                    userDo = new Gson().fromJson(strResponce, UserDo.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                }

                if (userDo != null) {
                    preference.saveStringInPreference(Preference.USERID,""+userDo.UP_UserID);
                    preference.commitPreference();
                    Intent intent = new Intent(LoginActivity.this, HomeScreenActivity_new.class);
                    intent.putExtra("LoginUserData", userDo);
                    intent.putExtras(intent);
                    startActivity(intent);
                }
            }
        }
    }
    public class syncTaskForCreateAccount extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            strResponce = apiServices.PostDataForCreateUser(generateJsonToString());
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(LoginActivity.this);
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            cShowProgress.hideProgress();
            if(strResponce.equalsIgnoreCase(AppConstants.EMAIL_ALREADY_EXISTS)){
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Email already exists", getString(R.string.OK), "", "");
            }else if(strResponce.equalsIgnoreCase(AppConstants.MOBILE_NO_ALREADY_EXISTS)){
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Mobile No already exists", getString(R.string.OK), "", "");
            }else  if(strResponce.equalsIgnoreCase(AppConstants.CREATED_SUCCESSFULLY)){
                txtlogin.performClick();
                clearSignInEdittest();
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Created Successfully. Yours UserId is : "+strPhoneNumber, getString(R.string.OK), "", "");
            }else {
//                Toast.makeText(getApplicationContext(),strResponce,Toast.LENGTH_SHORT).show();
                showCustomDialog(LoginActivity.this, getString(R.string.warning), "Something went wrong", getString(R.string.OK), "", "");
            }
        }
    }

    private void clearSignInEdittest() {
        etFirstName.setText("");
        etLastName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etPassword.setText("");
    }
    private void clearLoginEditTest(){
        edit_userId.setText("");
        edit_password.setText("");
    }
    private String generateJsonToString() {
        String jsonString = "{" +
                "\"UP_Name\": \"" + strFname + " " + strLname + "\", " +
                "\"UP_User_Type\": \"" + strType + "\", " +
                "\"UP_Email\": \"" + strEmail + "\"," +
                "\"UP_Mobile_No\": " + strPhoneNumber + "," +
                "\"UP_UserID\": " + strPhoneNumber + "," +
                "\"UP_Password\": \"" + strPasswd + "\"," +
                "\"UP_Status\": \"A\"" + "}";
        return jsonString;
    }
    private String JsontoStringForLogin(String userId, String password){
        String jsonString = "{" +
                "\"UserName\": \"" + userId + "\", " +
                "\"Password\": \"" + password + "\" " + "}";
        return jsonString;
    }

}
