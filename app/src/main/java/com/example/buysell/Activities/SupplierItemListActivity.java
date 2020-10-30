package com.example.buysell.Activities;

import android.content.Intent;
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
import com.daimajia.swipe.util.Attributes;
import com.example.buysell.Adapters.SupplierItemListAdaptor;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.Do.UserDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.ServiceURLs;
import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SupplierItemListActivity extends BaseActivity {
    private LinearLayout llSupplierList;
    private RecyclerView rv_supplier_list;
    private SupplierItemListAdaptor supplierListAdaptor;
    private TextView txtNoData;
    private CShowProgress cShowProgress;
    private List<SupplierItemMasterDo> SupplierListItems = new ArrayList<>();

    @Override
    public void initialize() {
        llSupplierList = (LinearLayout) inflater.inflate(R.layout.supplier_item_list, null);
        llBody.addView(llSupplierList, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        txt_head.setText("Supplier items");

        rv_supplier_list = llSupplierList.findViewById(R.id.rv_supplier_list);
        txtNoData = llSupplierList.findViewById(R.id.txtNoData);
        txt_add.setVisibility(View.VISIBLE);
        txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierItemListActivity.this,CreateSupplierItemMaster.class);
                intent.putExtra("from",AppConstants.FORCREATESUPPLIERITEM);
                startActivity(intent);
            }
        });
    }

    private void loadSupplierList() {
        cShowProgress = CShowProgress.getInstance();
        cShowProgress.showProgress(SupplierItemListActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServiceURLs.SUPPLIER_ITEMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cShowProgress.hideProgress();
                Gson gson = new Gson();
                try {
                    Type listType = new TypeToken<List<SupplierItemMasterDo>>() {}.getType();
                    try {
                        SupplierListItems = gson.fromJson(response, listType);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (SupplierListItems.size() > 0) {
                                rv_supplier_list.setVisibility(View.VISIBLE);
                                txtNoData.setVisibility(View.GONE);
                                supplierListAdaptor = new SupplierItemListAdaptor(SupplierItemListActivity.this, SupplierListItems,inflater,preference);
                                ((SupplierItemListAdaptor) supplierListAdaptor).setMode(Attributes.Mode.Single);
                                rv_supplier_list.setLayoutManager(new LinearLayoutManager(SupplierItemListActivity.this));
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
                Toast.makeText(getApplicationContext(), "Server problem", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSupplierList();
    }
}
