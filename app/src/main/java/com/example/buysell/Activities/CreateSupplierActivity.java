package com.example.buysell.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buysell.ApiServices;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.Do.SupplierMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.Preference;
import com.example.buysell.common.ServiceURLs;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

public class CreateSupplierActivity extends BaseActivity {
    private String[] CountryNames = {"INDIA", "PAKISTAN", "CHINA", "SRILANKA", "BANGLADESH", "USA", "UK"},
            supplierType = {"Personal", "Retail", "Distribution", "Corporate"},
            businessType = {"Beverages", "Food", "Groceries", "Furniture", "Electronics", "Gas", "Services", "Medical", "Transport"};
    private ArrayAdapter<String> CAdapter, supplierTypeAdaptor, businessTypeAdaptor;
    private LinearLayout llcreate_supplier;
    private EditText edtSupplierName, edtTemplate, edtMin, edtMax, edtPin_AadharNo,
            edtRegisterAddress, edtState, edtCity, edtZipCode, edtGstNo, edtMobileNo, edtDispatchAddress;
    private Button btnCreateSupplier;
    private CShowProgress cShowProgress;
    private String strSupplierName = "", strTemplate = "", strMin = "", strMax = "", strPan_AadharNo = "",
            strRegisterAddress = "", strCity = "", strZipCode = "", strCountry = "", strResponce = "", strGstNo = "", strDispatchAddress = "",
            strMobileNo = "", strState = "", from = "Create Supplier";
    private ApiServices apiServices;
    private Spinner spinCountry, spinSupplierType, spinBusinessType;
    private int intbusinessType = 0, intSupplierType = 0;
    private SupplierMasterDo supplierMaster;
    private SupplierMasterDo supplierMasterDo;

    @Override
    public void initialize() {
        llcreate_supplier = (LinearLayout) inflater.inflate(R.layout.activity_create_supplier, null);
        llBody.addView(llcreate_supplier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        inisilization();

        CAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, CountryNames);
        spinCountry.setAdapter(CAdapter);
        supplierTypeAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, supplierType);
        spinSupplierType.setAdapter(supplierTypeAdaptor);
        businessTypeAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, businessType);
        spinBusinessType.setAdapter(businessTypeAdaptor);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            from = bundle.getString("from");
            if (getIntent().hasExtra("supplierMaster")) {
                supplierMaster = (SupplierMasterDo) bundle.getSerializable("supplierMaster");
                btnCreateSupplier.setText("Update Supplier");
                edtSupplierName.setText(supplierMaster.SM_Name);
                edtTemplate.setText(supplierMaster.SM_Template_ID);
                edtMin.setText("" + supplierMaster.SM_Min_Delivery_Charge);
                edtMax.setText("" + supplierMaster.SM_Max_Delivery_Charge);
                edtPin_AadharNo.setText("" + supplierMaster.SD_PAN);
                edtRegisterAddress.setText("" + supplierMaster.SD_Register_Address);
                edtState.setText(supplierMaster.SD_State);
                edtCity.setText(supplierMaster.SD_City);
                edtGstNo.setText(supplierMaster.SD_GST_No);
                edtZipCode.setText("" + supplierMaster.SD_PINCode);
                edtMobileNo.setText("" + supplierMaster.SD_Mobile_No);
                edtDispatchAddress.setText("" + supplierMaster.SD_Dispatch_Address);
                spinCountry.setSelection(CAdapter.getPosition(supplierMaster.SD_Country));
                spinSupplierType.setSelection(supplierMaster.SM_Type);
                spinBusinessType.setSelection(supplierMaster.SM_BussinessType);
            }
        }
        txt_head.setText(from);
        btnCreateSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strSupplierName = edtSupplierName.getText().toString();
                strTemplate = edtTemplate.getText().toString();
                strMin = edtMin.getText().toString();
                strMax = edtMax.getText().toString();
                strPan_AadharNo = edtPin_AadharNo.getText().toString();
                strDispatchAddress = edtDispatchAddress.getText().toString();
                strRegisterAddress = edtRegisterAddress.getText().toString();
                strCity = edtCity.getText().toString();
                strZipCode = edtZipCode.getText().toString();
                strCountry = spinCountry.getSelectedItem().toString();
                strGstNo = edtGstNo.getText().toString();
                strMobileNo = edtMobileNo.getText().toString();
                strState = edtState.getText().toString();
                intSupplierType = supplierTypeAdaptor.getPosition(spinSupplierType.getSelectedItem().toString());
                intbusinessType = businessTypeAdaptor.getPosition(spinBusinessType.getSelectedItem().toString());

                if (strSupplierName.equalsIgnoreCase("") || strTemplate.equalsIgnoreCase("") ||
                        strMin.equalsIgnoreCase("") || strMax.equalsIgnoreCase("") || strPan_AadharNo.equalsIgnoreCase("") ||
                        strDispatchAddress.equalsIgnoreCase("") || strCity.equalsIgnoreCase("") ||
                        strZipCode.equalsIgnoreCase("") || strGstNo.equalsIgnoreCase("") ||
                        strRegisterAddress.equalsIgnoreCase("")) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Please enter all details fully.", getString(R.string.OK), "", "");
                } else if (strMobileNo.length() < 10) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Enter valid mobile no.", getString(R.string.OK), "", "");
                } else if (from.equalsIgnoreCase("Supplier Update") && strSupplierName.equalsIgnoreCase(supplierMaster.SM_Name) && strTemplate.equalsIgnoreCase(supplierMaster.SM_Template_ID) &&
                        strMin.equalsIgnoreCase("" + supplierMaster.SM_Min_Delivery_Charge) && strMax.equalsIgnoreCase("" + supplierMaster.SM_Max_Delivery_Charge)
                        && strPan_AadharNo.equalsIgnoreCase(supplierMaster.SD_PAN) && strDispatchAddress.equalsIgnoreCase(supplierMaster.SD_Dispatch_Address)
                        && strCity.equalsIgnoreCase(supplierMaster.SD_City) && strZipCode.equalsIgnoreCase("" + supplierMaster.SD_PINCode)
                        && strGstNo.equalsIgnoreCase(supplierMaster.SD_GST_No) && strRegisterAddress.equalsIgnoreCase(supplierMaster.SD_Register_Address)
                        && intSupplierType == supplierMaster.SM_Type && intbusinessType == supplierMaster.SM_BussinessType && strMobileNo.equalsIgnoreCase(""+supplierMaster.SD_Mobile_No)
                && strState.equalsIgnoreCase(supplierMaster.SD_State)) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "No change appear.", getString(R.string.OK), "", "");
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
        spinSupplierType = llcreate_supplier.findViewById(R.id.spinSupplierType);
        spinBusinessType = llcreate_supplier.findViewById(R.id.spinBusinessType);
    }


    public class syncTaskForCreatingSupplier extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (from.equalsIgnoreCase("Supplier Update")) {
                strResponce = apiServices.UpdateItemUsingToken(ServiceURLs.SUPPLIERS_MASTERS_UPDATE, generateJsonToStringForEdit(), preference.getStringFromPreference(AppConstants.LOGIN_TOKE, ""));
            } else {
                strResponce = apiServices.PostDatausingToken(generateJsonToString(), ServiceURLs.SUPPLIER_MASTER_CREATE, preference.getStringFromPreference(AppConstants.LOGIN_TOKE, ""));
            }
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
            } else if (strResponce.equalsIgnoreCase(AppConstants.PAN_NO_ALREADY_EXISTS)) {
                showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.PAN_NO_ALREADY_EXISTS, getString(R.string.OK), "", "");
            } else if (strResponce.equalsIgnoreCase(AppConstants.GST_NO_ALREADY_EXISTS)) {
                showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.GST_NO_ALREADY_EXISTS, getString(R.string.OK), "", "");
            } else if (strResponce.equalsIgnoreCase("Updated")) {
                showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "Changes are done", getString(R.string.OK), "", "finish");
            } else if (strResponce.equalsIgnoreCase(AppConstants.USERID_NOT_FOUND)) {
                showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), AppConstants.USERID_NOT_FOUND, getString(R.string.OK), "", "");
            } else {
                try {
                    supplierMasterDo = gson.fromJson(strResponce, SupplierMasterDo.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                }
                if (supplierMasterDo != null) {
                    showCustomDialog(CreateSupplierActivity.this, getString(R.string.warning), "A new Supplier is added", getString(R.string.OK), "", "finish");
                } else {
                    Toast.makeText(getApplicationContext(), strResponce, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String generateJsonToString() {
        String jsonString = "{" +
                "\"SM_Name\": \"" + strSupplierName + "\", " +
                "\"SM_Logo\": \"" + "Logo" + "\", " +
                "\"SM_Template_ID\": \"" + strTemplate + "\"," +
                "\"SM_BussinessType\": " + intbusinessType + "," +
                "\"SM_Type\": " + intSupplierType + "," +
                "\"SM_Status\": \"" + "A" + " \"," +
                "\"SM_Rating\": " + 0 + "," +
                "\"SM_Min_Delivery_Charge\": \"" + strMin + "\"," +
                "\"SM_Max_Delivery_Charge\": \"" + strMax + "\"," +
                "\"SM_UserID\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\"," +
                "\"SD_PAN\": \"" + strPan_AadharNo + "\"," +
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

    public String generateJsonToStringForEdit() {
        String jsonString = "{" +
                "\"SM_ID\": " + supplierMaster.SM_Id + "," +
                "\"SM_Name\": \"" + strSupplierName + "\", " +
                "\"SM_Logo\": \"" + "Logo" + "\", " +
                "\"SM_Template_ID\": \"" + strTemplate + "\"," +
                "\"SM_BussinessType\": " + intbusinessType + "," +
                "\"SM_Type\": " + intSupplierType + "," +
                "\"SM_Status\": \"" + "A" + " \"," +
                "\"SM_Rating\": " + 2 + "," +
                "\"SM_Min_Delivery_Charge\": \"" + strMin + "\"," +
                "\"SM_Max_Delivery_Charge\": \"" + strMax + "\"," +
                "\"SM_UserID\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\"," +
                "\"SD_PAN\": \"" + strPan_AadharNo + "\"," +
                "\"SD_Register_Address\": \"" + strRegisterAddress + "\"," +
                "\"SD_Dispatch_Address\": \"" + strDispatchAddress + "\"," +
                "\"SD_City\": \"" + strCity + "\"," +
                "\"SD_State\": \"" + strState + "\"," +
                "\"SD_Country\": \"" + strCountry + "\"," +
                "\"SD_PINCode\": \"" + strZipCode + "\"," +
                "\"SD_GST_No\": \"" + strGstNo + "\"," +
                "\"SD_LandLine_No\": " + 0 + "," +
                "\"SD_Mobile_No\": \"" + strMobileNo + "\"" + "}";
        return jsonString;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

