package com.example.aashish.myassistant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements  MasterPassword.OnButtonClick{
    FrameLayout scan,balance,transer,data,more,pass;
    int permissionCheck;
    MasterPassword mp;
    boolean flag=false;
    ImageView scanimg;
  // AnimationDrawable animation;
  // LinearLayout ly;

    android.support.v7.widget.Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myhome);
        permissionCheck = ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE);
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED)
        {

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,Manifest.permission.CAMERA},1);
        }
        scan=findViewById(R.id.scan);
        balance = findViewById(R.id.balance);
        transer = findViewById(R.id.transfer);
        data=findViewById(R.id.data);
        more=findViewById(R.id.others);
        pass=findViewById(R.id.passbook);
        toolbar=findViewById(R.id.toolbarid);
        scanimg = findViewById(R.id.scanimg);
//        ly=findViewById(R.id.mylayout);
//        animation=(AnimationDrawable)ly.getBackground();
//        animation.setEnterFadeDuration(3000);
//
//        animation.setExitFadeDuration(3000);
//        animation.start();
       //animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        SharedPreferences sp =getSharedPreferences("mypassword",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pass","1234");
        editor.commit();
        mp = new MasterPassword(HomeActivity.this);
        mp.setListener(this);


        setSupportActionBar(toolbar);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // scanimg.setAnimation(animation);
//                Handler handler= new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                        Intent i = new Intent(HomeActivity.this,MainActivity.class);
                        i.putExtra("toOpen", "scanfragment");
                        startActivity(i);
//                    }
//                },2000);

            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("toOpen", "balancefragment");
                startActivity(i);
            }
        });
        transer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("toOpen", "transferfragment");
                startActivity(i);
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("toOpen", "datafragment");
                startActivity(i);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("toOpen", "morefragment");
                startActivity(i);
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mp.show();
                //yeta bigreko xa

                if(flag) {
                    mp.dismiss();

                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]!=PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,Manifest.permission.CAMERA},1);
        }
    }

    @Override
    public void onLoginClick() {
        SharedPreferences sp = getSharedPreferences("mypassword", Context.MODE_PRIVATE);
                String mypass = sp.getString("pass", "1234");
                if (mp.master.getText().toString().equals(mypass)) {
                 flag=true;
                 mp.dismiss();
                    Intent i = new Intent(HomeActivity.this, MainActivity.class);
                    i.putExtra("toOpen", "passfragment");
                    startActivity(i);
                }
                else {
                  flag= false;
                  mp.dismiss();
                }
    }


}
