package com.example.buysell.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.buysell.ApiServices;
import com.example.buysell.Do.UserDo;
import com.example.buysell.R;
import com.example.buysell.common.Preference;

import java.net.HttpURLConnection;

public class EditUserDetailsActivity extends BaseActivity {
    private LinearLayout llEditUserDetails;
    private EditText etName, etEmail, etMobileNo, etPassword;
    private Button btnUpdate;
    private String strName = "", strEmail = "", strMobileNo = "", strPassword = "", strType = "";
    private int response = 0;
    private CShowProgress cShowProgress;
    private ImageView imgChangePassword;
    private boolean show = false;
    private UserDo user= null;
    @Override
    public void initialize() {
        llEditUserDetails = (LinearLayout) inflater.inflate(R.layout.edit_user_details, null);
        llBody.addView(llEditUserDetails, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Edit Profile");

        initilization();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            user = (UserDo) bundle.get("LoginUserData");
            etName.setText(user.UP_Name);
            etPassword.setText(user.UP_Password);
            etMobileNo.setText(""+user.UP_Mobile_No);
            etEmail.setText(user.UP_Email);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.UP_Name.equalsIgnoreCase(etName.getText().toString()) && user.UP_Email.equalsIgnoreCase(etEmail.getText().toString()) && etMobileNo.getText().toString().equalsIgnoreCase(""+user.UP_Mobile_No) && user.UP_Password.equalsIgnoreCase(etPassword.getText().toString())) {
                    showCustomDialog(EditUserDetailsActivity.this, getString(R.string.warning), "No Changes are Applied", getString(R.string.OK), "", "");
                } else {
                    SyncforUpdateUserDetails syncforUpdateUserDetails = new SyncforUpdateUserDetails();
                    syncforUpdateUserDetails.execute();
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

    private void initilization() {
        etName = llEditUserDetails.findViewById(R.id.etName);
        etEmail = llEditUserDetails.findViewById(R.id.etEmail);
        etMobileNo = llEditUserDetails.findViewById(R.id.etMobileNo);
        etPassword = llEditUserDetails.findViewById(R.id.etPassword);
        btnUpdate = llEditUserDetails.findViewById(R.id.btnUpdate);
        imgChangePassword = llEditUserDetails.findViewById(R.id.imgChangePassword);
    }

    public class SyncforUpdateUserDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            response = ApiServices.PutDataForCreateUser(generateJsonToString(), preference.getStringFromPreference(Preference.USERID, ""), preference.getStringFromPreference(Preference.PASSWORD, ""));
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(EditUserDetailsActivity.this);
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            cShowProgress.hideProgress();
            if (response == HttpURLConnection.HTTP_OK) {
                showCustomDialog(EditUserDetailsActivity.this, getString(R.string.warning), "Successful", getString(R.string.OK), "", "UpdateUserData");
            } else {
                showCustomDialog(EditUserDetailsActivity.this, getString(R.string.warning), "Some thing went wrong", getString(R.string.OK), "", "");
            }
        }
    }

    private String generateJsonToString() {
        String jsonString = "{" +
                "\"UP_ID\": " + Integer.parseInt(preference.getStringFromPreference(Preference.ID, "")) + ", " +
                "\"UP_Name\": \"" + etName.getText().toString() + "\", " +
                "\"UP_User_Type\": \"" + preference.getStringFromPreference(Preference.TYPE, "") + "\", " +
                "\"UP_Email\": \"" + etEmail.getText().toString() + "\"," +
                "\"UP_Mobile_No\": " + Long.parseLong(etMobileNo.getText().toString()) + "," +
                "\"UP_UserID\": " + Long.parseLong(etMobileNo.getText().toString()) + "," +
                "\"UP_Password\": \"" + etPassword.getText().toString() + "\"," +
                "\"UP_Status\": \"A\"" + "}";
        return jsonString;
    }
}