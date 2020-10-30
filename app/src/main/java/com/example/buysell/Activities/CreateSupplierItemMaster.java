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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buysell.ApiServices;
import com.example.buysell.CalendarUtils;
import com.example.buysell.Do.SpinnerDo;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.Preference;
import com.example.buysell.common.ServiceURLs;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreateSupplierItemMaster extends BaseActivity {
    private LinearLayout llCreate_supplier_item_master;
    private EditText edtItemName, edtUOM, edtPrice, edtDeliveryCharges, edtTaxPercentage, edtTaxAmount, edtStock;
    private String strItemName = "", strUOM = "", strPrice = "", strDeliveryCharges = "", strTaxPercentage = "", strTaxAmount = "",
            strResponce = "", from = "Create Supplier item", strStoc = "", strMeasureType = "",
            measureTypes[] = {"nominal", "ordinal", "interval", "ratio"};
    private Button btnCreateSupplierItem;
    private String[] category, subCategory;
    private int intCategory = 0, intSubCategory = 0;
    private ApiServices apiServices;
    private CShowProgress cShowProgress;
    private SupplierItemMasterDo supplierItemMasterDo = null;
    private SupplierItemMasterDo supplierItemMaster = null;
    private Spinner spinCategory, spinSubCategory, spinMeasureType;
    private List<SpinnerDo> categoryList = new ArrayList();
    private List<SpinnerDo> subCategoryList = new ArrayList();
    private ArrayAdapter<String> measureAdaptor, categoryAdapter, SubcategoryAdaptor;

    @Override
    public void initialize() {
        llCreate_supplier_item_master = (LinearLayout) inflater.inflate(R.layout.create_supplier_item_master, null);
        llBody.addView(llCreate_supplier_item_master, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        inisilization();
        loadData();
        measureAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measureTypes);
        spinMeasureType.setAdapter(measureAdaptor);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            from = bundle.getString("from");
            if (getIntent().hasExtra("supplierItemMaster")) {
                supplierItemMaster = (SupplierItemMasterDo) bundle.getSerializable("supplierItemMaster");
                btnCreateSupplierItem.setText("Update item");
                edtItemName.setText(supplierItemMaster.SIM_IT_Name);
                edtDeliveryCharges.setText("" + supplierItemMaster.SIM_IT_Delivery_Charge);
                edtUOM.setText(supplierItemMaster.SIM_IT_UOM);
                edtPrice.setText("" + supplierItemMaster.SIM_IT_Price);
                edtTaxAmount.setText("" + supplierItemMaster.SIM_Tax_Amount);
                edtTaxPercentage.setText("" + supplierItemMaster.SIM_Tax_Percentage);
                edtStock.setText(supplierItemMaster.SIM_Stock_Avilable);
                spinCategory.setSelection(supplierItemMaster.SIM_Category);
                spinMeasureType.setSelection(measureAdaptor.getPosition(supplierItemMaster.SIM_MeasureType));
            }
        }

        txt_head.setText(from);
        btnCreateSupplierItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strItemName = edtItemName.getText().toString();
                strUOM = edtUOM.getText().toString();
                strPrice = edtPrice.getText().toString();
                strDeliveryCharges = edtDeliveryCharges.getText().toString();
                strTaxPercentage = edtTaxPercentage.getText().toString();
                strTaxAmount = edtTaxAmount.getText().toString();
                strStoc = edtStock.getText().toString();
                strMeasureType = spinMeasureType.getSelectedItem().toString();
                intCategory = categoryAdapter.getPosition(spinCategory.getSelectedItem().toString());
//                intSubCategory = SubcategoryAdaptor.getPosition(spinSubCategory.getSelectedItem().toString());

                if (strItemName.equalsIgnoreCase("") || strUOM.equalsIgnoreCase("") || strPrice.equalsIgnoreCase("") || strDeliveryCharges.equalsIgnoreCase("") || strTaxPercentage.equalsIgnoreCase("") || strTaxAmount.equalsIgnoreCase("")) {
                    showCustomDialog(CreateSupplierItemMaster.this, getString(R.string.warning), "Please enter all details fully.", getString(R.string.OK), "", "");
                } else if (isNetworkConnectionAvailable(CreateSupplierItemMaster.this)) {
                    if (from.equalsIgnoreCase(AppConstants.FORCREATESUPPLIERITEM)) {
                        syncTaskForCreatingSupplierItem syncTaskForCreatingSupplieritem = new syncTaskForCreatingSupplierItem();
                        syncTaskForCreatingSupplieritem.execute();
                    } else if (from.equalsIgnoreCase(AppConstants.FOREDITSUPPLIERITEM)) {
                        if (strItemName.equalsIgnoreCase(supplierItemMaster.SIM_IT_Name) && strUOM.equalsIgnoreCase(supplierItemMaster.SIM_IT_UOM) && strPrice.equalsIgnoreCase("" + supplierItemMaster.SIM_IT_Price)
                                && strDeliveryCharges.equalsIgnoreCase("" + supplierItemMaster.SIM_IT_Delivery_Charge) &&
                                strTaxAmount.equalsIgnoreCase("" + supplierItemMaster.SIM_Tax_Amount) && strTaxPercentage.equalsIgnoreCase("" + supplierItemMaster.SIM_Tax_Percentage)
                                && strStoc.equalsIgnoreCase("" + supplierItemMaster.SIM_Stock_Avilable) && categoryAdapter.getPosition(spinCategory.getSelectedItem().toString()) == supplierItemMaster.SIM_Category &&
                                spinMeasureType.getSelectedItem().toString().equalsIgnoreCase(supplierItemMaster.SIM_MeasureType)) {
                            showCustomDialog(CreateSupplierItemMaster.this, getString(R.string.warning), "No Changes are appear", getString(R.string.OK), "", "");
                        } else {
                            syncTaskForCreatingSupplierItem syncTaskForCreatingSupplieritem = new syncTaskForCreatingSupplierItem();
                            syncTaskForCreatingSupplieritem.execute();
                        }
                    }
                } else {
                    showCustomDialog(CreateSupplierItemMaster.this, getString(R.string.warning), "Connect to Internet.", getString(R.string.OK), "", "");
                }
            }
        });
    }

    private void loadData() {
        loadCatogeryList();
//        loadSubCatogeryList();
    }

    private void loadCatogeryList() {
        cShowProgress = CShowProgress.getInstance();
        cShowProgress.showProgress(CreateSupplierItemMaster.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServiceURLs.SPINNER_PRODUCT_CATEGORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cShowProgress.hideProgress();
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<List<SpinnerDo>>() {
                    }.getType();
                    try {
                        categoryList = gson.fromJson(response, listType);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            category = new String[categoryList.size()];
                            for (int i = 0; i < categoryList.size(); i++) {
                                category[i] = categoryList.get(i).getName();
                            }
                            categoryAdapter = new ArrayAdapter<String>(CreateSupplierItemMaster.this, android.R.layout.simple_spinner_dropdown_item, category);
                            spinCategory.setAdapter(categoryAdapter);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadSubCatogeryList() {
        cShowProgress = CShowProgress.getInstance();
        cShowProgress.showProgress(CreateSupplierItemMaster.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServiceURLs.SPINNER_PRODUCT_SUB_CATEGORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cShowProgress.hideProgress();
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<List<SpinnerDo>>() {
                    }.getType();
                    try {
                        subCategoryList = gson.fromJson(response, listType);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            subCategory = new String[subCategoryList.size()];
                            for (int i = 0; i < subCategoryList.size(); i++) {
                                subCategory[i] = subCategoryList.get(i).getName();
                            }
                            SubcategoryAdaptor = new ArrayAdapter<String>(CreateSupplierItemMaster.this, android.R.layout.simple_spinner_dropdown_item, subCategory);
                            spinSubCategory.setAdapter(SubcategoryAdaptor);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void inisilization() {
        apiServices = new ApiServices();
        edtItemName = llCreate_supplier_item_master.findViewById(R.id.edtItemName);
        edtUOM = llCreate_supplier_item_master.findViewById(R.id.edtUOM);
        edtPrice = llCreate_supplier_item_master.findViewById(R.id.edtPrice);
        edtDeliveryCharges = llCreate_supplier_item_master.findViewById(R.id.edtDeliveryCharges);
        edtTaxPercentage = llCreate_supplier_item_master.findViewById(R.id.edtTaxPercentage);
        edtTaxAmount = llCreate_supplier_item_master.findViewById(R.id.edtTaxAmount);
        btnCreateSupplierItem = llCreate_supplier_item_master.findViewById(R.id.btnCreateSupplierItem);
        edtStock = llCreate_supplier_item_master.findViewById(R.id.edtStock);
        spinCategory = llCreate_supplier_item_master.findViewById(R.id.spinCategory);
        spinSubCategory = llCreate_supplier_item_master.findViewById(R.id.spinSubCategory);
        spinMeasureType = llCreate_supplier_item_master.findViewById(R.id.spinMeasureType);
    }

    public class syncTaskForCreatingSupplierItem extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (from.equalsIgnoreCase(AppConstants.FORCREATESUPPLIERITEM)) {
                strResponce = apiServices.PostDatausingToken(generateJsonToString(), ServiceURLs.SUPPLIER_ITEM_CREATE, preference.getStringFromPreference(AppConstants.LOGIN_TOKE, ""));
            } else if (from.equalsIgnoreCase(AppConstants.FOREDITSUPPLIERITEM)) {
                strResponce = apiServices.UpdateItemUsingToken(ServiceURLs.UPDATE_SUPPLIER_ITEM + "?id=" + supplierItemMaster.SIM_ID, generateJsonToStringForEdit(), preference.getStringFromPreference(AppConstants.LOGIN_TOKE, ""));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cShowProgress.hideProgress();
            Gson gson = new Gson();
            if (strResponce.equalsIgnoreCase(AppConstants.INTERNAL_ERROR) && !strResponce.equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), strResponce, Toast.LENGTH_SHORT).show();
            } else if (strResponce.equalsIgnoreCase("Updated")) {
                showCustomDialog(CreateSupplierItemMaster.this, getString(R.string.warning), "Changes are done", getString(R.string.OK), "", "finish");
            } else {
                try {
                    supplierItemMasterDo = gson.fromJson(strResponce, SupplierItemMasterDo.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                }

                if (supplierItemMasterDo != null) {
                    showCustomDialog(CreateSupplierItemMaster.this, getString(R.string.warning), "Item created successfully", getString(R.string.OK), "", "finish");
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cShowProgress = CShowProgress.getInstance();
            cShowProgress.showProgress(CreateSupplierItemMaster.this);
        }
    }

    private String generateJsonToString() {
        String jsonString = "{" +
                "\"SIM_SM_ID\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\", " +
//                "\"SIM_IT_ID\": \"" + preference.getStringFromPreference(Preference.USERID,"") + "\", " +
                "\"SIM_IT_Name\": \"" + strItemName + "\"," +
                "\"SIM_Category\": " + intCategory + "," +
                "\"SIM_SubCategory\": " + intSubCategory + "," +
//                "\"SIM_IT_Pic\": \""+"logo"+" \"," +
                "\"SIM_IT_UOM\":  \"" + strUOM + "\"," +
                "\"SIM_IT_Price\": " + strPrice + "," +
                "\"SIM_IT_Delivery_Charge\": " + strDeliveryCharges + "," +
                "\"SIM_Tax_Percentage\":  " + strTaxPercentage + "," +
                "\"SIM_Tax_Amount\": " + strTaxAmount + "," +
                "\"SIM_MeasureType\": \"" + strMeasureType + "\"," +
                "\"SIM_Stock_Avilable\": " + Integer.parseInt(strStoc) + "," +
                "\"SIM_Status\": \"" + "A" + "\"," +
                "\"SIM_Created_By\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\"" +
                "\"SM_Created_Date\": \"" + CalendarUtils.getCurrentDateTime() + "\"" + "}";
        return jsonString;
    }

    private String generateJsonToStringForEdit() {
        String jsonString = "{" +
                "\"SIM_ID\": " + supplierItemMaster.SIM_ID + "," +
                "\"SIM_SM_ID\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\", " +
//                "\"SIM_IT_ID\": \"" + preference.getStringFromPreference(Preference.USERID,"") + "\", " +
                "\"SIM_IT_Name\": \"" + strItemName + "\"," +
                "\"SIM_Category\": " + intCategory + "," +
                "\"SIM_SubCategory\": " + intSubCategory + "," +
//                "\"SIM_IT_Pic\": \""+"logo"+" \"," +
                "\"SIM_IT_UOM\":  \"" + strUOM + "\"," +
                "\"SIM_IT_Price\": " + strPrice + "," +
                "\"SIM_IT_Delivery_Charge\": " + strDeliveryCharges + "," +
                "\"SIM_Tax_Percentage\":  " + strTaxPercentage + "," +
                "\"SIM_Tax_Amount\": " + strTaxAmount + "," +
                "\"SIM_MeasureType\": \"" + strMeasureType + "\"," +
                "\"SIM_Stock_Avilable\": " + Integer.parseInt(strStoc) + "," +
                "\"SIM_Status\": \"" + "A" + "\"," +
                "\"SIM_Created_By\": \"" + preference.getStringFromPreference(Preference.USERID, "") + "\"" +
                "\"SIM_Modified_Date\": \"" + CalendarUtils.getCurrentDateTime() + "\"" + "}";
        return jsonString;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
