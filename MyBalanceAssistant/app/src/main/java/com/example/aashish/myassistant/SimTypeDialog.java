package com.example.aashish.myassistant;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

public class SimTypeDialog extends Dialog implements android.view.View.OnClickListener{
    ImageView ntc,ncell;

    Context c;
    OnButtonClick onButtonClick;
    public SimTypeDialog(@NonNull Context context) {
        super(context);
        c= context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simtype);
        ntc= findViewById(R.id.ntc);
        ncell=findViewById(R.id.ncell);

        ntc.setOnClickListener(this);
        ncell.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == ntc) {
            onButtonClick.onNtcClick();
        }
        if (v == ncell) {
            onButtonClick.onNcellClick();
        }
    }

    public interface OnButtonClick {
        void onNtcClick();

        void onNcellClick();
    }
    public void setListener(SimTypeDialog.OnButtonClick onButtonClick){
        this.onButtonClick = onButtonClick;
    }
}
