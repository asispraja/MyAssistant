package com.example.aashish.myassistant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MasterPassword.OnButtonClick {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
   NavigationView navigationView;
    FragmentTransaction ft;
    ActionBarDrawerToggle actionBarDrawerToggle;
    MasterPassword mp;
    boolean flag;
    String titles;
    public static Context contextOfApplication;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbarid);
        navigationView=findViewById(R.id.navigationview);
        contextOfApplication = getApplicationContext();
//        setting  custom toolbar (values/styles.xml)
        setSupportActionBar(toolbar);



//        state open or close
        actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        ft=getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setTitle("Balance ");
        ft.add(R.id.framelayout, new BalanceFragment());
        ft.commit();
        String s=getIntent().getExtras().getString("toOpen");
        setFragment(s);
        mp = new MasterPassword(MainActivity.this);
        mp.setListener(this);
//        click for nab bar

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.recharge:
                        ft=getSupportFragmentManager().beginTransaction();
                        getSupportActionBar().setTitle("Scan My Card");
                        ft.replace(R.id.framelayout, new RechargeFragment());
                        ft.commit();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.transfer:
                        ft=getSupportFragmentManager().beginTransaction();
                        getSupportActionBar().setTitle("Transfer My Balance");
                        ft.replace(R.id.framelayout, new TransferFragment());
                        ft.commit();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.data:
                        ft=getSupportFragmentManager().beginTransaction();
                        getSupportActionBar().setTitle(" Data Schemes");
                        ft.replace(R.id.framelayout,new DataFragment());
                        ft.commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.balance:
                        ft=getSupportFragmentManager().beginTransaction();
                        getSupportActionBar().setTitle(" Choose Sim");
                        ft.replace(R.id.framelayout,new BalanceFragment());
                        ft.commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mypass:
                        if(flag) {
                            mp.dismiss();
                            ft = getSupportFragmentManager().beginTransaction();
                            getSupportActionBar().setTitle(" Password Library");
                            ft.replace(R.id.framelayout, new MyActivity());
                            ft.commit();
                        }
                            drawerLayout.closeDrawers();
                            break;



                }
                return false;
            }
        });
    }
    public void setFragment(String s)
    {
        switch(s)
        {

            case "scanfragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle("Scan My Card");
                ft.replace(R.id.framelayout, new RechargeFragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;

            case "transferfragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle("Transfer My Balance");
                ft.replace(R.id.framelayout, new TransferFragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;

            case "datafragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle(" Data Schemes");
                ft.replace(R.id.framelayout,new DataFragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;
            case "balancefragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle(" Choose Sim");
                ft.replace(R.id.framelayout,new BalanceFragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;
            case "passfragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle("Password Library");
                ft.replace(R.id.framelayout,new MyActivity());
                ft.commit();
                drawerLayout.closeDrawers();
                break;
            case "morefragment":
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle(" More Services");
                ft.replace(R.id.framelayout,new Aboutfragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         titles=getSupportActionBar().getTitle().toString();
        if(titles.equals("Password Library"))
            getMenuInflater().inflate(R.menu.password,menu);
        else
        getMenuInflater().inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.abouts:
                ft=getSupportFragmentManager().beginTransaction();
                getSupportActionBar().setTitle(" About");
                ft.replace(R.id.framelayout,new Aboutfragment());
                ft.commit();
                drawerLayout.closeDrawers();
                break;

            case R.id.masterp:

                mp.show();
                mp.login.setText("Set Password");



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }


    @Override
    public void onLoginClick() {

        if(titles.equals("Password Library"))
        {
            SharedPreferences sp =getSharedPreferences("mypassword",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("pass",mp.master.getText().toString());
            editor.commit();
            mp.dismiss();

        }
        else {
            SharedPreferences sp = getSharedPreferences("mypassword", Context.MODE_PRIVATE);
            String mypass = sp.getString("pass", "1234");
            if (mp.master.getText().toString().equals(mypass)) {
                flag = true;
                mp.dismiss();
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("toOpen", "passfragment");
                startActivity(i);
            } else {
                mp.dismiss();
                flag = false;
            }
        }
    }
}
