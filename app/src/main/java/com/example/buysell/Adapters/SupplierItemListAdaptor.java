package com.example.buysell.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.buysell.Activities.BaseActivity;
import com.example.buysell.Activities.CreateSupplierItemMaster;
import com.example.buysell.Activities.LoginActivity;
import com.example.buysell.Activities.SupplierItemListActivity;
import com.example.buysell.ApiServices;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.CustomDialog;
import com.example.buysell.common.Preference;
import com.example.buysell.common.ServiceURLs;

import java.util.List;

public class SupplierItemListAdaptor extends RecyclerSwipeAdapter<SupplierItemListAdaptor.ViewHolder> {
    private Context mcontext;
    private List<SupplierItemMasterDo> SupplierListItems;
    private ApiServices apiServices= new ApiServices();
    public CustomDialog customDialog;
    public LayoutInflater inflater;
    public Preference preference;
    public SupplierItemListAdaptor(Context mcontext, List<SupplierItemMasterDo> supplierListItems,LayoutInflater inflater,Preference preference) {
        this.mcontext = mcontext;
        SupplierListItems = supplierListItems;
        this.inflater=inflater;
        this.preference=preference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.rv_supplier_item_list, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SupplierItemMasterDo supplierItemMaster = SupplierListItems.get(position);
        if(supplierItemMaster!=null){
            holder.txt_supplier_name.setText(supplierItemMaster.SIM_IT_Name);
            holder.txt_to_pay.setText("Rs: "+supplierItemMaster.SIM_IT_Price);
            holder.tvDelete.setTag(supplierItemMaster);
            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.bottomLeft));
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottomRight));
            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.swipeLayout.close();
                    Intent intent = new Intent(mcontext, CreateSupplierItemMaster.class);
                    intent.putExtra("from", AppConstants.FOREDITSUPPLIERITEM);
                    intent.putExtra("supplierItemMaster", supplierItemMaster);
                    ((SupplierItemListActivity)mcontext).startActivity(intent);
                }
            });


            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.swipeLayout.close();
                    TextView text = (TextView) view;
                    SupplierItemMasterDo supplierItemMaster = (SupplierItemMasterDo) text.getTag();
                    ((SupplierItemListActivity) mcontext).runOnUiThread(new RunshowCustomDialogs(mcontext,"Warning","Are to sure to delete this "+supplierItemMaster.SIM_IT_Name+" item","Yes","No","",true,supplierItemMaster,position,holder));
                }
            });
            mItemManger.bindView(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return SupplierListItems.size();
    }


    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView txt_supplier_name,txt_to_pay,txt_order_list,txt_notifs,tvDelete,tvEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            txt_supplier_name = itemView.findViewById(R.id.txt_supplier_name);
            txt_to_pay = itemView.findViewById(R.id.txt_to_pay);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvEdit = itemView.findViewById(R.id.tvEdit);
//            txt_order_list = itemView.findViewById(R.id.txt_order_list);
//            txt_order_list = itemView.findViewById(R.id.txt_order_list);
        }
    }
    public class syncTaskForDelete extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... params) {
            apiServices.deleteItem(ServiceURLs.SUPPLIER_ITEM_DELELTE+"?id="+params[0]);
            return null;
        }
    }
    class RunshowCustomDialogs implements Runnable {
        private String strTitle;// Title of the dialog
        private String strMessage;// Message to be shown in dialog
        private String firstBtnName;
        private String secondBtnName;
        private String from="";
        private String params;
        private Object paramateres;
        private boolean isCancelable = false;
        SupplierItemMasterDo supplierItemMaster;
        private int position=0;
        ViewHolder holder;

        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable,SupplierItemMasterDo supplierItemMaster,int position, ViewHolder holder) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            this.supplierItemMaster=supplierItemMaster;
            this.position=position;
            this.holder=holder;
        }

        @Override
        public void run() {
            if (customDialog != null && customDialog.isShowing())
                customDialog.dismiss();
            View view;

            view = inflater.inflate(R.layout.custom_common_popup, null);

            customDialog = new CustomDialog(mcontext, view, preference
                    .getIntFromPreference(Preference.DEVICE_DISPLAY_WIDTH, 320) - 60,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            customDialog.setCancelable(isCancelable);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitlePopup);
            TextView tvMessage = (TextView) view.findViewById(R.id.tvMessagePopup);
            Button btnYes = (Button) view.findViewById(R.id.btnYesPopup);
            Button btnNo = (Button) view.findViewById(R.id.btnNoPopup);

            tvTitle.setTypeface(AppConstants.Roboto_Condensed_Bold);
            tvMessage.setTypeface(AppConstants.Roboto_Condensed_Bold);
            btnYes.setTypeface(AppConstants.Roboto_Condensed_Bold);
            btnNo.setTypeface(AppConstants.Roboto_Condensed_Bold);

            tvTitle.setText("" + strTitle);
            tvMessage.setText("" + strMessage);
            btnYes.setText("" + firstBtnName);
            if (secondBtnName.equalsIgnoreCase("")) {
                btnNo.setVisibility(View.GONE);
            } else {
                btnNo.setText("" + secondBtnName);
            }
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                    mItemManger.removeShownLayouts(holder.swipeLayout);
                    SupplierListItems.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, SupplierListItems.size());
                    mItemManger.closeAllItems();

                    new syncTaskForDelete().execute(""+supplierItemMaster.SIM_ID);
                    Toast.makeText(v.getContext(), "Deleted " + supplierItemMaster.SIM_IT_Name, Toast.LENGTH_SHORT).show();
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            try {
                if (!customDialog.isShowing())
                    customDialog.showCustomDialog();
            } catch (Exception e) {
            }
        }
    }
}
