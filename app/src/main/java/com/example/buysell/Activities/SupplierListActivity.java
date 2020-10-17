package com.example.buysell.Activities;

import android.app.DownloadManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buysell.Adapters.SupplierListAdaptor;
import com.example.buysell.Do.SupplierDo;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.ServiceURLs;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SupplierListActivity extends BaseActivity {
    private LinearLayout llSupplierList;
    private RecyclerView rv_supplier_list;
    private SupplierListAdaptor supplierListAdaptor;
    private TextView txtNoData;
    private CShowProgress cShowProgress;
    private List<SupplierItemMasterDo> SupplierListItems = new ArrayList<>();

    @Override
    public void initialize() {
        llSupplierList = (LinearLayout) inflater.inflate(R.layout.supplier_list, null);
        llBody.addView(llSupplierList, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        txt_head.setText((getIntent().getStringExtra("from").equalsIgnoreCase(AppConstants.CUSTOMERLIST)) ? "Customer List" : "Supplier List");

        rv_supplier_list = llSupplierList.findViewById(R.id.rv_supplier_list);
        txtNoData = llSupplierList.findViewById(R.id.txtNoData);

        loadSupplierList();
    }

    private void loadSupplierList() {
        cShowProgress = CShowProgress.getInstance();
        cShowProgress.showProgress(SupplierListActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServiceURLs.SUPPLIER_ITEMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cShowProgress.hideProgress();
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<List<SupplierItemMasterDo>>() {
                    }.getType();
                    SupplierListItems = gson.fromJson(response, listType);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (SupplierListItems.size() > 0) {
                                rv_supplier_list.setVisibility(View.VISIBLE);
                                txtNoData.setVisibility(View.GONE);
                                supplierListAdaptor = new SupplierListAdaptor(SupplierListActivity.this, SupplierListItems);
                                rv_supplier_list.setLayoutManager(new LinearLayoutManager(SupplierListActivity.this));
                                rv_supplier_list.setAdapter(supplierListAdaptor);
                            }else{
                                rv_supplier_list.setVisibility(View.GONE);
                                txtNoData.setVisibility(View.VISIBLE);
                            }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
