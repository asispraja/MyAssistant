package com.example.aashish.myassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aashish on 31/05/2018.
 */

public class DataFragment extends Fragment {
    HashMap<String, List<String>> header;
    List<String> child;
    ExpandableListView elv;
    MyAdapter adapter;
    SimTypeDialog dialog;
    Switch dataswitch;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.datapack, null);
        elv = v.findViewById(R.id.list);
       header = MyAdapter.Ntcprovider.getInfo();
        child = new ArrayList<String>(header.keySet());
        dataswitch = v.findViewById(R.id.dataswitch);
        dataswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataswitch.isChecked())
                {
                    onNcellClick();
                }
                else
                {
                    onNtcClick();
                }
            }
        });



        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        header = MyAdapter.Ntcprovider.getInfo();
        child = new ArrayList<String>(header.keySet());
        adapter = new MyAdapter(getContext(), header, child,dataswitch.isChecked());
        elv.setAdapter(adapter);



    }

    public void onNtcClick() {
        header = MyAdapter.Ntcprovider.getInfo();
        dataswitch.setText("NTC");


        child = new ArrayList<String>(header.keySet());
        adapter = new MyAdapter(getContext(), header, child,false);

        elv.setAdapter(adapter);
    }

    public void onNcellClick() {
        dataswitch.setText("NCELL");
        header = MyAdapter.Ncellprovider.getInfo();

        child = new ArrayList<String>(header.keySet());
        adapter = new MyAdapter(getContext(), header, child,true);

        elv.setAdapter(adapter);
    }


}
