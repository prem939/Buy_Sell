package com.example.buysell2.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.example.buysell2.R;

public class CShowProgress {
    public static CShowProgress mCShowProgress;
    public Dialog mDialog;

    public CShowProgress() {
    }

    public static CShowProgress getInstance() {
        if (mCShowProgress== null) {
            mCShowProgress= new CShowProgress();
        }
        return mCShowProgress;
    }

    public void showProgress(Context mContext) {
        mDialog= new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.custom_progress_layout);
        mDialog.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    public void hideProgress() {
        if (mDialog!= null) {
            mDialog.dismiss();
            mDialog= null;
        }
    }
}
