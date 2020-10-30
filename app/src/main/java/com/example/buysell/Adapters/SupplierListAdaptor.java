package com.example.buysell.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.buysell.Activities.CreateSupplierActivity;
import com.example.buysell.Activities.CreateSupplierItemMaster;
import com.example.buysell.Activities.SupplierItemListActivity;
import com.example.buysell.Activities.SupplierListActivity;
import com.example.buysell.ApiServices;
import com.example.buysell.Do.SupplierItemMasterDo;
import com.example.buysell.Do.SupplierMasterDo;
import com.example.buysell.R;
import com.example.buysell.common.AppConstants;
import com.example.buysell.common.CustomDialog;
import com.example.buysell.common.Preference;
import com.example.buysell.common.ServiceURLs;

import java.util.List;

public class SupplierListAdaptor extends RecyclerSwipeAdapter<SupplierListAdaptor.ViewHolder> {
    private Context mcontext;
    private List<SupplierMasterDo> SuppliersList;
    private ApiServices apiServices = new ApiServices();
    public CustomDialog customDialog;
    public LayoutInflater inflater;
    public Preference preference;

    public SupplierListAdaptor(Context mcontext, List<SupplierMasterDo> SuppliersList, LayoutInflater inflater, Preference preference) {
        this.mcontext = mcontext;
        this.SuppliersList = SuppliersList;
        this.inflater = inflater;
        this.preference = preference;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.supplier_list_adaptor, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SupplierMasterDo supplierMaster = SuppliersList.get(position);
        if (supplierMaster != null) {
            holder.txt_SM_Name.setText(supplierMaster.SM_Name);
            holder.txt_description.setText(supplierMaster.SD_Register_Address+", "+supplierMaster.SD_City+", "+supplierMaster.SD_State+", "+supplierMaster.SD_Country+", "+supplierMaster.SD_PINCode);
            holder.txt_SD_Mobile_No.setText("Mobile No : "+supplierMaster.SD_Mobile_No);
            holder.tvDelete.setTag(supplierMaster);
            holder.tvEdit.setTag(supplierMaster);
            holder.myImageViewText.setText(""+supplierMaster.SM_Name.charAt(0)+""+supplierMaster.SM_Name.charAt(1));
            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.bottomLeft));
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottomRight));
            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.swipeLayout.close();
                    TextView text = (TextView) view;
                    SupplierMasterDo supplierMaster = (SupplierMasterDo) text.getTag();

                    Intent intent = new Intent(mcontext, CreateSupplierActivity.class);
                    intent.putExtra("from", "Supplier Update");
                    intent.putExtra("supplierMaster", supplierMaster);
                    ((SupplierListActivity) mcontext).startActivity(intent);
                }
            });


            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.swipeLayout.close();
                    TextView text = (TextView) view;
                    SupplierMasterDo supplierMaster = (SupplierMasterDo) text.getTag();
                    ((SupplierListActivity) mcontext).runOnUiThread(new RunshowCustomDialogs(mcontext, "Warning", "Are to sure to delete this " + supplierMaster.SM_Name + " supplier", "Yes", "No", "", true, supplierMaster, position, holder));
                }
            });
            mItemManger.bindView(holder.itemView, position);
        }
    }
        @Override
        public int getItemCount () {
            return SuppliersList.size();
        }

        @Override
        public int getSwipeLayoutResourceId ( int position){
            return R.id.swipe;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            SwipeLayout swipeLayout;
            TextView tvDelete, tvEdit, txt_SM_Name, txt_description, txt_SD_Mobile_No,myImageViewText;
            ImageView img_SM_Logo;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
                tvDelete = itemView.findViewById(R.id.tvDelete);
                tvEdit = itemView.findViewById(R.id.tvEdit);
                txt_SM_Name = itemView.findViewById(R.id.txt_SM_Name);
                txt_description = itemView.findViewById(R.id.txt_description);
                txt_SD_Mobile_No = itemView.findViewById(R.id.txt_SD_Mobile_No);
                myImageViewText = itemView.findViewById(R.id.myImageViewText);
                img_SM_Logo = itemView.findViewById(R.id.img_SM_Logo);
            }
        }
    public class syncTaskForDelete extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... params) {
            apiServices.deleteItem(ServiceURLs.SUPPLIERS_MASTERS_DELETE+params[0]);
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
        private SupplierMasterDo supplierMaster;
        private int position=0;
        ViewHolder holder;

        public RunshowCustomDialogs(Context context, String strTitle, String strMessage, String firstBtnName, String secondBtnName, String from, boolean isCancelable,SupplierMasterDo supplierMaster,int position, ViewHolder holder) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;
            this.supplierMaster=supplierMaster;
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
                    SuppliersList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, SuppliersList.size());
                    mItemManger.closeAllItems();

                    new syncTaskForDelete().execute(""+supplierMaster.SM_Id);
                    Toast.makeText(v.getContext(), "Deleted " + supplierMaster.SM_Name+ "Supplier", Toast.LENGTH_SHORT).show();
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