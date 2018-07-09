package com.example.aashish.myassistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {
    private Context c;
    private HashMap<String, List<String>> childTitles;
    private List<String> headerTitles;
    TextView txt;
    String titles;
    boolean myswitch;

    public MyAdapter(Context dataFragment, HashMap<String, List<String>> childTitles, List<String> headerTitles,boolean dswitch) {
        c = dataFragment;
        myswitch =dswitch;
        this.headerTitles = headerTitles;
        this.childTitles = childTitles;

    }


    @Override
    public int getGroupCount() {
        return headerTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childTitles.get(headerTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childTitles.get(headerTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        //may be childid
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.headerlv, null);
        }
        TextView txt1 = convertView.findViewById(R.id.header);
        txt1.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        titles = (String) this.getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.childrenlv, null);
        }

        txt = convertView.findViewById(R.id.child);
        txt.setText(titles);

        txt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if(myswitch==false) {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.putExtra("com.android.phone.extra.slot", 0);
                    i.putExtra("simSlot", 0);
                    i.setData(Uri.parse("tel:" + "*1415%23"));
                    c.startActivity(i);
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.putExtra("com.android.phone.extra.slot", 1);
                    i.putExtra("simSlot", 1);
                    i.setData(Uri.parse("tel:" + "*17123%23"));
                    c.startActivity(i);
                }

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public static class Ntcprovider {
        public static HashMap<String, List<String>> getInfo() {
            HashMap<String, List<String>> headerdetails = new HashMap<String, List<String>>();
            List<String> summer = new ArrayList<String>();
            summer.add("All Time Data");
            summer.add("Night Data");
            summer.add("Social Media Pack");
            summer.add("Streaming Pack");
            summer.add("Voice Pack");
            summer.add("4G Offer");
            summer.add("Night Voice");

            List<String> weekend = new ArrayList<String>();
            weekend.add("HAPPY Weekend");
            List<String> unlimited = new ArrayList<String>();
            unlimited.add("30 Nights");
            unlimited.add("7 Nights");
            unlimited.add("1 Night");
            List<String> voice = new ArrayList<String>();
            voice.add("11 mins @Rs 10 - 1 day");
            voice.add("30 mins @Rs 25 - 3 days");

            voice.add("75 mins @Rs 50 - 5 days");

            voice.add("200 mins @Rs 100 - 10 days");

            voice.add("410 mins @Rs 200 - 28 days");
            voice.add("650 mins @Rs 300 - 30 days");
            voice.add("1200 mins @Rs 550 - 60 days");

            List<String> perday = new ArrayList<String>();
            perday.add("1 GB @Rs 45");
            perday.add("7GB @Rs 250");

            perday.add("28GB @Rs 850");


            List<String> india = new ArrayList<String>();
            india.add("15 mins @Rs 35 - 1 day");
            india.add("65 mins @Rs 150 - 7 days");

            india.add("225 mins @Rs 500 - 30 days");


            List<String> special = new ArrayList<String>();
            special.add("Unlimited Voice, SMS & Data @Rs 18 - 1 Hour");
            special.add("120 SMS @Rs 12  5 days");


            headerdetails.put("Summer  Data", summer);

            headerdetails.put("Weekend Data", weekend);

            headerdetails.put("Unlimited Data ", unlimited);

            headerdetails.put("Voice", voice);
            headerdetails.put("1GB per day ", perday);

            headerdetails.put("India Call", india);

            headerdetails.put("SPECIAL Pack ", special);
            return headerdetails;
        }



    }

    public static class Ncellprovider {
        public static HashMap<String, List<String>> getInfo() {
            HashMap<String, List<String>> headers = new HashMap<String, List<String>>();
            List<String> ramropack = new ArrayList<String>();
            ramropack.add("30 MB @Rs 10-1day");
            ramropack.add("100 MB @Rs 30-3days");
            ramropack.add("250 MB @Rs 70-7days");
            ramropack.add("1250 MB @Rs 300-30days");


            List<String> damipack = new ArrayList<String>();
            damipack.add("200 MB @Rs 20-1day");
            damipack.add("400 MB @Rs 50-3days");
            damipack.add("1000 MB @Rs 100-7days");
            damipack.add("5000 MB @Rs 500-30days");


            List<String> sahipack = new ArrayList<String>();
            sahipack.add("0.5GB @Rs 39 - 1 day");
            sahipack.add("1.5GB @Rs 99 - 3 days");
            sahipack.add("3.5GB @Rs 199 -7  days");
            sahipack.add("15GB @Rs 799 - 30 days");

            List<String> regularpack = new ArrayList<String>();
            regularpack.add("Night Pack-11PM to 5AM");

            headers.put("Ramro  Data", ramropack);

            headers.put("Dami Data", damipack);

            headers.put("Sahi Data ", sahipack);

            headers.put("Regular Data", regularpack);
            return headers;

        }
    }
}
