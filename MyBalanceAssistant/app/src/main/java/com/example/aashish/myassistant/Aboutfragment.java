package com.example.aashish.myassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Aashish on 31/05/2018.
 */

public class Aboutfragment extends Fragment{
    TextView tv1 ,name,contact,email,website,name1,contact1,email1,website1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about,null);
        tv1 = v.findViewById(R.id.about);
        contact= v.findViewById(R.id.contact);
        name= v.findViewById(R.id.name);
        name1= v.findViewById(R.id.name1);
        email= v.findViewById(R.id.email);


        email1= v.findViewById(R.id.email1);

        website= v.findViewById(R.id.website);
        website1= v.findViewById(R.id.website1);

        contact1= v.findViewById(R.id.contact1);


        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String about = " This app is designed on to provide Call, Recharge , Transfer Balance and Data Services with packages provided by Nepal Telecom(ntc.com.np) and Ncell(ncell.axiata.com). The app has features of Direct Scan and Recharge from a snap of recharge Card. \n Balance Transfer is done through available contact list o you never make another error during blance transfer. \n Data Scheme provided by service providers like nTC and NCell are also included.";

        String uname="Aashish Prajapati";
        String ucontact="9849761919";
        String uemail="aashishp1919@gmail.com";
        String uwebsite="www.aashishp.com.np";

        String uname1="Pragya Parajuli";
        String ucontact1="9843724404";
        String uemail1="pragyaparajuli2014@gmail.com";
        String uwebsite1="www.pragya.com.np";



        tv1.setText(about);

        name.setText(uname);
        contact.setText(ucontact);
        email1.setText(uemail);
        website.setText(uwebsite);

        name1.setText(uname1);
        contact1.setText(ucontact1);
        email1.setText(uemail1);
        website1.setText(uwebsite1);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S=website.getText().toString();
                Intent Go = new Intent(Intent.ACTION_VIEW);
                Go.setData(Uri.parse("http://"+S));
                startActivity(Go);
            }
        });
        website1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S=website1.getText().toString();
                Intent Go = new Intent(Intent.ACTION_VIEW);
                Go.setData(Uri.parse("http://"+S));
                startActivity(Go);
            }
        });

    }
}
