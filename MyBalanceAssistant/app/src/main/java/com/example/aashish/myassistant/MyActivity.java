package com.example.aashish.myassistant;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Fragment {
    private ListView lv;
private Button add;
    List<DataModule> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passwordlayout, null);
        lv = v.findViewById(R.id.list);


        add = v.findViewById(R.id.add);
        data=new ArrayList<>();
        MyDatabaseHelper db=new MyDatabaseHelper(getActivity());
        data=db.readRecords();
        lv.setAdapter(new CustomAdapter(getActivity(),data));

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View convertView= LayoutInflater.from(getActivity()).inflate(R.layout.passbook, null);
                final EditText name=convertView.findViewById(R.id.name);
                final EditText password=convertView.findViewById(R.id.pass);
                final EditText site=convertView.findViewById(R.id.site);

                Button save=convertView.findViewById(R.id.save);
                Button cancel=convertView.findViewById(R.id.cancel);
                AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
                alert.setView(convertView);
                final AlertDialog dialog=alert.create();
                dialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataModule m=new DataModule();
                        m.setName(name.getText().toString());
                        m.setPassword(password.getText().toString());
                        m.setSite(site.getText().toString());

                        MyDatabaseHelper db=new MyDatabaseHelper(getActivity());
                        db.addrecord(m);
                        Toast.makeText(getActivity(), "Records are added successfully",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                       data=new ArrayList<>();

                        data=db.readRecords();
                        lv.setAdapter(new CustomAdapter(getActivity(),data));

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });


            }
        });

    }

}
