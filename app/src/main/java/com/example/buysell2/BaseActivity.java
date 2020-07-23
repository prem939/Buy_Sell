package com.example.buysell2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract  class BaseActivity extends FragmentActivity {
    public LayoutInflater inflater;
    public LinearLayout  llBody;
    public Button btnMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        inflater 				= 		this.getLayoutInflater();
        llBody 					= 		(LinearLayout) findViewById(R.id.llBody);
        btnMenu                 =        findViewById(R.id.btnMenu);
        initialize();
    }
    public abstract void initialize();
}
