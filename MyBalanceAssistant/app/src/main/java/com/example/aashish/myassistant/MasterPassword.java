package com.example.aashish.myassistant;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aashish.myassistant.R;

public class MasterPassword extends Dialog implements android.view.View.OnClickListener {
    EditText master;
    Button login;
    Context c;
    boolean flag = false;
    OnButtonClick onButtonClick;
    String filename = "mypassword";

    public MasterPassword(@NonNull Context context) {
        super(context);
        c = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterpassword);
        master = findViewById(R.id.textView);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);



        //yeta bigreko xa


    }


    @Override
        public void onClick(View v){
            if (v == login) {
                onButtonClick.onLoginClick();
            }

    }
    public interface OnButtonClick {
        void onLoginClick();

    }
    public void setListener(MasterPassword.OnButtonClick onButtonClick){
        this.onButtonClick = onButtonClick;
    }
}
