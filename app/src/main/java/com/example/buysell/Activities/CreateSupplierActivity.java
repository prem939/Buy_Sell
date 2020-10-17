package com.example.buysell.Activities;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buysell.ApiServices;
import com.example.buysell.Do.SupplierMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.Preference;
import com.example.buysell.common.ServiceURLs;
import com.google.gson.Gson;

public class CreateSupplierActivity extends BaseActivity {
    String[] CountryNames = {"--Select--", "INDIA", "PAKISTAN", "CHINA", "SRILANKA", "BANGLADESH", "USA", "UK"};
    ArrayAdapter<String> CAdapter;
    LinearLayout llcreate_supplier;
    private EditText edtSupplierName, edtTemplate, edtMin, edtMax, edtPin_AadharNo,
            edtRegisterAddress,edtState,edtCity, edtZipCode, edtGstNo, edtMobileNo,edtDispatchAddress;
    private Spinner spinCountry;
    private Button btnCreateSupplier;
    private CShowProgress cShowProgress;
    private String strSupplierName = "", strTemplate = "", strMin = "", strMax = "", strPin_AadharNo = "",
            strRegisterAddress = "", strCity = "", strZipCode = "", strCountry = "", strResponce = "", strGstNo = "",strDispatchAddress="",
            strMobileNo = "",strState="";
    private ApiServices apiServices;

    @Override
    public void initialize() {
        llcreate_supplier = (LinearLayout) inflater.inflate(R.layout.activity_create_supplier, null);
        llBody.addView(llcreate_supplier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txt_head.setText("Create Supplier");
        inisilization();
        CAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, CountryNames);
        spinCountry.setAdapter(CAdapter);

        btnCreateSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strSupplierName = edtSupplierName.getText().toString();
                strTemplate = edtTemplate.getText().toString();
                strMin = edtMin.getText().toString();
                strMax = edtMax.getText().toString();
                strPin_AadharNo = edtPin_AadharNo.getText().toString();
                strDispatchAddress = edtDispatchAddress.getText().toString();
                strRegisterAddress = edtRegisterAddress.getText().toString();
                strCity = edtCity.getText().toString();
                strZipCode = edtZipCode.getText().toString();
                strCountry = spinCountry.getSelectedItem().toString();
                strGstNo = edtGstNo.getText().toString();
                strMobileNo = edtMobileNo.getText().toString();
                strState = edtState.getText().toString();

                if (strSupplierName.equalsIgnoreCase("") || strTemplate.equalsIgnoreCase("") || strCountry.equalsIgnoreCase("--select--") || strMin.equalsIgnoreCase("") || strMax.equalsIgnoreCase("") || strPin_AadharNo.equalsIgnoreCase("") || strDispatchAddress.equalsIgnoreCase("") || strCity.equalsIgnoreCase("") || strZipCode.equalsIgnoreCase("") || strGstNo.equalsIgnoreCase("") || strRegisterAddress.equalsIgnoreCase("")) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Please enter all details fully.", getString(R.string.OK), "", "");
                } else if (strMobileNo.length() < 10) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Enter valid mobile no.", getString(R.string.OK), "", "");
                } else if (isNetworkConnectionAvailable(CreateSupplierActivity.this)) {
                    syncTaskForCreatingSupplier syncTaskForCreatingSupplier = new syncTaskForCreatingSupplier();
                    syncTaskForCreatingSupplier.execute();
                } else {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Connect to Internet.", getString(R.string.OK), "", "");
                }
            }
        });
    }

    private void inisilization() {
        apiServices = new ApiServices();
        spinCountry = llcreate_supplier.findViewById(R.id.spinCountry);
        btnCreateSupplier = llcreate_supplier.findViewById(R.id.btnCreateSupplier);
        edtSupplierName = llcreate_supplier.findViewById(R.id.edtSupplierName);
        edtTemplate = llcreate_supplier.findViewById(R.id.edtTemplate);
        edtMin = llcreate_supplier.findViewById(R.id.edtMin);
        edtMax = llcreate_supplier.findViewById(R.id.edtMax);
        edtPin_AadharNo = llcreate_supplier.findViewById(R.id.edtPin_AadharNo);
        edtDispatchAddress = llcreate_supplier.findViewById(R.id.edtDispatchAddress);
        edtRegisterAddress = llcreate_supplier.findViewById(R.id.edtRegisterAddress);
        edtCity = llcreate_supplier.findViewById(R.id.edtCity);
        edtZipCode = llcreate_supplier.findViewById(R.id.edtZipCode);
        edtMobileNo = llcreate_supplier.findViewById(R.id.edtMobileNo);
        edtGstNo = llcreate_supplier.findViewById(R.id.edtGstNo);
        edtState = llcreate_supplier.findViewById(R.id.edtState);
    }


    public class syncTaskForCreatingSupplier extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            strResponce = apiServices.PostDatausingToken(generateJsonToString(), ServiceURLs.SUPPLIER_MASTER_CREATE, preference.getStringFromPreference(AppConstants.LOGIN_TOKE, ""));
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(CreateSupplierActivity.this);
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            cShowProgress.hideProgress();
            Gson gson = new Gson();
          if (strResponce.equalsIgnoreCase(AppConstants.MOBILE_NO_ALREADY_EXISTS)) {
                showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.MOBILE_NO_ALREADY_EXISTS, getString(R.string.OK), "", "");
            } else if (strResponce.equalsIgnoreCase(AppConstants.SUPPLIER_NAME_ALREADY_EXISTS)) {
               showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.SUPPLIER_NAME_ALREADY_EXISTS, getString(R.string.OK), "", "");
           }else if (strResponce.equalsIgnoreCase(AppConstants.PAN_NO_ALREADY_EXISTS)) {
               showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.PAN_NO_ALREADY_EXISTS, getString(R.string.OK), "", "");
           }else if (strResponce.equalsIgnoreCase(AppConstants.GST_NO_ALREADY_EXISTS)) {
               showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.GST_NO_ALREADY_EXISTS, getString(R.string.OK), "", "");
           }else {
                SupplierMasterDo supplierMasterDo = gson.fromJson(strResponce, SupplierMasterDo.class);
                if(supplierMasterDo != null){
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Supplier master is successfully created", getString(R.string.OK), "", "Supplier Created");
                }else{
                    Toast.makeText(getApplicationContext(), strResponce, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String generateJsonToString() {
        String jsonString = "{" +
                "\"SM_Name\": \"" + strSupplierName+ "\", " +
                "\"SM_Logo\": \"" + "Logo" + "\", " +
                "\"SM_Template_ID\": \"" + strTemplate + "\"," +
                "\"SM_Status\": \""+"A"+" \"," +
                "\"SM_Rating\": " + 0 + "," +
                "\"SM_Min_Delivery_Charge\": \"" + strMin + "\"," +
                "\"SM_Max_Delivery_Charge\": \"" + strMax + "\"," +
                "\"SM_UserID\": \"" + preference.getStringFromPreference(Preference.USERID,"") + "\"," +
                "\"SD_PAN\": \"" + strPin_AadharNo + "\"," +
                "\"SD_Register_Address\": \"" + strRegisterAddress + "\"," +
                "\"SD_Dispatch_Address\": \"" + strDispatchAddress + "\"," +
                "\"SD_City\": \"" + strCity + "\"," +
                "\"SD_State\": \"" + strState + "\"," +
                "\"SD_Country\": \"" + strCountry + "\"," +
                "\"SD_PINCode\": \"" + strZipCode + "\"," +
                "\"SD_GST_No\": \"" + strGstNo + "\"," +
                "\"SD_LandLine_No\": \"" + "0000000000" + "\"," +
                "\"SD_Mobile_No\": \"" + strMobileNo + "\"" + "}";
        return jsonString;
    }
}

