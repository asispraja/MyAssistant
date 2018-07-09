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
import android.widget.ImageView;

/**
 * Created by Aashish on 31/05/2018.
 */

public class BalanceFragment extends Fragment{
    ImageView ntc,ncell;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.simtype,null);
        ntc = v.findViewById(R.id.ntc);
        ncell = v.findViewById(R.id.ncell);

        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ntc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.putExtra("com.android.phone.extra.slot",0);
                i.putExtra("simSlot",0);
                i.setData( Uri.parse("tel:" + "*400%23"));
                startActivity(i);

            }
        });
        ncell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.putExtra("com.android.phone.extra.slot",1);
                i.putExtra("simSlot",1);
                i.setData( Uri.parse("tel:" + "*901%23"));
                startActivity(i);
            }
        });
    }
}
