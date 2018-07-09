package com.example.aashish.myassistant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;

/**
 * Created by Aashish on 31/05/2018.
 */

public class RechargeFragment extends android.support.v4.app.Fragment implements SimTypeDialog.OnButtonClick{
   ImageButton contacts;
   EditText pin,phone;
   Button recharge;
   TextView tips;
   SimTypeDialog dialog;
    String pinNum;
    String code;
    int sim;
    Spinner spinner;
    ImageView camera;
    String number;

    Context applicationContext;



    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scancard,null);
        camera= v.findViewById(R.id.camera);
        pin = v.findViewById(R.id.pin);
        recharge = v.findViewById(R.id.recharge);
        tips= v.findViewById(R.id.tips);
        spinner = v.findViewById(R.id.spinner);
        phone=v.findViewById(R.id.phone);
        contacts = v.findViewById(R.id.contacts);




        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new SimTypeDialog(getContext());
        dialog.setListener(this);
        dialog.show();

        String[] mylist = {"Mobile Recharge","Landline Recharge","ADSL Recharge"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(getContext(),android.R.layout.simple_dropdown_item_1line,mylist);
       spinner.setAdapter(adapter);
        contacts.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                       intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                       startActivityForResult(intent,1);
                   }
               });






recharge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String select = spinner.getSelectedItem().toString();
                pinNum=pin.getText().toString();
                switch(select)
                {
                    case "Mobile Recharge":
                        code=code+pinNum+"%23";
                        phone.setVisibility(View.VISIBLE);
                        contacts.setVisibility(View.VISIBLE);
                        break;
                    case "Landline Recharge":
                        phone.setVisibility(View.VISIBLE);
                        contacts.setVisibility(View.VISIBLE);
                        code= "1412,1,3,"+phone.getText().toString()+pinNum;
                        break;
                    case "ADSL Recharge":
                        phone.setVisibility(View.VISIBLE);
                        contacts.setVisibility(View.VISIBLE);
                        code="1412,1,4,"+phone.getText().toString()+pinNum;
                        break;

                }

                Intent i = new Intent(Intent.ACTION_CALL);
                i.putExtra("com.android.phone.extra.slot",sim);
                i.putExtra("simSlot",sim);

                i.setData( Uri.parse("tel:" + code));
                startActivity(i);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OcrCaptureActivity.class);


                startActivityForResult(intent, RC_OCR_CAPTURE);
            }
        });
        String tipss= "  > Scratch the recharge card with clear view of numbers \n   > Keep 10-15 cm distance between the phone and" +
                          "recharge card";
        tips.setText(tipss);
    }

    @Override
    public void onNtcClick() {



        code="*412*";
        sim=0;
        dialog.dismiss();
        spinner.setVisibility(View.VISIBLE);
        contacts.setVisibility(View.VISIBLE);
        phone.setVisibility(View.VISIBLE);

    }

    @Override
    public void onNcellClick() {

        code="*102*";
        sim=1;
        dialog.dismiss();
        spinner.setVisibility(View.INVISIBLE);
        contacts.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                applicationContext = MainActivity.getContextOfApplication();
                try {
                    Cursor c = applicationContext.getContentResolver().query(contactData, null, null, null, null);

                    c.moveToNext();
                    number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone.setText(number);
                } catch (Exception e) {
                    Toast.makeText(applicationContext, "No Contact Selected", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);

                    pin.setText(text);
                    Log.d(TAG, "Text read: " + text);
                } else {

                    Log.d(TAG, "No Text captured, intent data is null");
                }
            }
        }

    }

}
