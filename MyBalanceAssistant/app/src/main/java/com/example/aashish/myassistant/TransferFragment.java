package com.example.aashish.myassistant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Aashish on 31/05/2018.
 */

public class TransferFragment extends android.support.v4.app.Fragment{
    EditText security, phone, amount;
    Button transfer;
    ImageButton img;
    String prefix="984";
    View v;

    Context applicationContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.balancetransfer,null);
        security=v.findViewById(R.id.security);
        phone = v.findViewById(R.id.phone);
        amount = v.findViewById(R.id.amount);
        transfer= v.findViewById(R.id.transfer);
        img = v.findViewById(R.id.contacts);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,1);
            }
        });




    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        String number="";
        switch (reqCode) {
            case 1 :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    applicationContext = MainActivity.getContextOfApplication();
                    try {
                        Cursor c = applicationContext.getContentResolver().query(contactData, null, null, null, null);

                        c.moveToNext();
                        number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phone.setText(number);
                        prefix=phone.getText().toString().substring(0,3);

                        if((prefix.equals("984"))||(prefix.equals("985"))||(prefix.equals("986"))){
                            security.setVisibility(View.VISIBLE);
                            transfer.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   // Toast.makeText(getContext(), "Ntc "+prefix, Toast.LENGTH_SHORT).show();
                                  Intent i = new Intent(Intent.ACTION_CALL);
                                    i.putExtra("com.android.phone.extra.slot",0);
                                    i.putExtra("simSlot",0);
                                    i.setData( Uri.parse("tel:" + "*422" + "*" + security.getText().toString() + "*" + phone.getText().toString() + "*" + amount.getText().toString() + "%23"));
                                     startActivity(i);
                                }
                            });
                        }
                        else  if((prefix.equals("980"))||(prefix.equals("981"))||(prefix.equals("982")))
                        {
                            security.setVisibility(View.INVISIBLE);
                            transfer.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                           //         Toast.makeText(getContext(), "NCEll"+prefix, Toast.LENGTH_SHORT).show();
                  Intent i = new Intent(Intent.ACTION_CALL);
                                    i.putExtra("com.android.phone.extra.slot",1);
                                    i.putExtra("simSlot",1);
                                    i.setData( Uri.parse("tel:" + "*17122" + "*" + phone.getText().toString() + "*" + amount.getText().toString() + "%23"));
                  startActivity(i);
                                }
                            });
                        }else
                        {
                            transfer.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getContext(), "Please Recheck the Phone number", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                    }
                     catch(Exception e)
                     {
                         Toast.makeText(getActivity(), "No Contact selected", Toast.LENGTH_SHORT).show();
                     }
                }
                break;
        }

    }

}
