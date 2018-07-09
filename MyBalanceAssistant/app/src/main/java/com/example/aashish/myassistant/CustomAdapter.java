package com.example.aashish.myassistant;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context c;
    List<DataModule>list=new ArrayList<>();

    public CustomAdapter(Context myActivity, List<DataModule> data) {
        c=myActivity;
       list=data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(c).inflate(R.layout.viewdata,null);
        TextView name=convertView.findViewById(R.id.name);
        final TextView password=convertView.findViewById(R.id.pass);
        final TextView site  =convertView.findViewById(R.id.site);

        name.setText(list.get(position).getName());
        password.setText(list.get(position).getPassword());
        site.setText(list.get(position).getSite());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                View convertView=LayoutInflater.from(c).inflate(R.layout.editpassword,null);
                final EditText ename=convertView.findViewById(R.id.name);
                final EditText epass=convertView.findViewById(R.id.pass);
                final EditText esite=convertView.findViewById(R.id.site);

                Button update=convertView.findViewById(R.id.update);
                Button delete=convertView.findViewById(R.id.delete);
                Button cancel=convertView.findViewById(R.id.cancel);
                AlertDialog.Builder alert1=new AlertDialog.Builder(c);
                alert1.setView(convertView);
                final AlertDialog dialog=alert1.create();
                dialog.show();
                delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        MyDatabaseHelper db=new MyDatabaseHelper(c);
                        db.deletedata(list.get(position).getId());
                        dialog.dismiss();



                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataModule m=new DataModule();
                        m.setName(ename.getText().toString());
                        m.setPassword(epass.getText().toString());
                        m.setSite(esite.getText().toString());

                        m.setId(list.get(position).getId());
                        MyDatabaseHelper db=new MyDatabaseHelper(c);
                        db.updateRecords(m);
                        dialog.dismiss();



                    }
                });
                return false;

            }
        });





        return convertView;
    }
}
