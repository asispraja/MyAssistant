package com.example.aashish.myassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MoreFragment extends Fragment {
    FrameLayout cc,my5,landline,adsl,callrate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.other,null);
        cc=v.findViewById(R.id.cc);
        my5=v.findViewById(R.id.my5);
        landline=v.findViewById(R.id.landline);
        adsl=v.findViewById(R.id.adsl);
        callrate=v.findViewById(R.id.callrate);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        landline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
