package com.example.iqbal.banglarkrishok;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = null;
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#228B22")));
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_home:
                this.getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();

                break;
            case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DeveloperProfileFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_about:

                break;
            case R.id.nav_contact:

                break;
            case R.id.nav_login:
                //this.getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new LoginFragment()).addToBackStack(null).commit();


                break;
            case R.id.nav_exit:
                askForExit();
                break;

        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment f = this.getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(f instanceof LoginFragment || f instanceof SignUpFragment || f instanceof AdminLoginFragment){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new MainFragment()).addToBackStack(null).commit();
            }else if (f instanceof MainFragment){
                askForExit();
            }else if(f instanceof ReqForAdviceFragment || f instanceof AdminHomepageFragment){
                askForLogout();
            }else{
                super.onBackPressed();
            }



        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void askForExit(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("সতর্ক বার্তা")
                .setIcon(getResources().getDrawable(R.drawable.icon_alert))
                .setMessage("আপনি কি অ্যাপটি বন্ধ করতে চান?")
                .setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void askForLogout(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("সতর্ক বার্তা")
                .setIcon(getResources().getDrawable(R.drawable.icon_alert))
                .setMessage("আপনি কি লগঅউট করতে চান?")
                .setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                new MainFragment()).addToBackStack(null).commit();
                    }
                })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}
