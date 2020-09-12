package com.example.buysell2.Activities;

import android.os.AsyncTask;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.buysell2.ApiServices;
import com.example.buysell2.R;
import com.example.buysell2.common.Preference;

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

    @Override
    public void initialize() {
        llEditUserDetails = (LinearLayout) inflater.inflate(R.layout.edit_user_details, null);
        llBody.addView(llEditUserDetails, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Edit Profile");

        initilization();

        if (!preference.getStringFromPreference(Preference.USERNAME, "").equalsIgnoreCase("")) {
            strName = preference.getStringFromPreference(Preference.USERNAME, "");
            etName.setText(strName);
        }
        if (!preference.getStringFromPreference(Preference.PASSWORD, "").equalsIgnoreCase("")) {
            strPassword = preference.getStringFromPreference(Preference.PASSWORD, "");
            etPassword.setText(strPassword);
        }
        if (!preference.getStringFromPreference(Preference.MOBILE_NO, "").equalsIgnoreCase("")) {
            strMobileNo = preference.getStringFromPreference(Preference.MOBILE_NO, "");
            etMobileNo.setText(strMobileNo);
        }
        if (!preference.getStringFromPreference(Preference.EMAIL, "").equalsIgnoreCase("")) {
            strEmail = preference.getStringFromPreference(Preference.EMAIL, "");
            etEmail.setText(strEmail);
        }
        if (!preference.getStringFromPreference(Preference.TYPE, "").equalsIgnoreCase("")) {
            strType = preference.getStringFromPreference(Preference.TYPE, "");
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strName.equalsIgnoreCase(etName.getText().toString()) && strEmail.equalsIgnoreCase(etEmail.getText().toString()) && strMobileNo.equalsIgnoreCase(etMobileNo.getText().toString()) && strPassword.equalsIgnoreCase(etPassword.getText().toString())) {
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