package com.example.quote_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.quote_application.FragmentUtils.FragmentUtils;
import com.example.quote_application.Fragments.HomeFragment;
import com.example.quote_application.Fragments.Save_Quotes_Fragment;
import com.example.quote_application.Network.NetworkUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        FragmentUtils.setFragment(fragmentManager,R.id.parentFrameLayout,new HomeFragment());


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homePage1){
                    FragmentUtils.setFragment(fragmentManager,R.id.parentFrameLayout,new HomeFragment());
                    return true;
                }
                if (id == R.id.savedQuotePage){
                    FragmentUtils.setFragment(fragmentManager,R.id.parentFrameLayout,new Save_Quotes_Fragment());

                    return true;

                }

                return false;
            }
        });

        CheckInterNetAccess();

    }

    private void CheckInterNetAccess() {
        if (NetworkUtil.isNetworkAvailable(this)){
            NetworkUtil.hasInternetAccess(new NetworkUtil.InternetAccessCallback() {
                @Override
                public void onInternetAccessResult(boolean hasInternetAccess) {
                    if (hasInternetAccess){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                    else {
                        //no internet
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You have no internet connection", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setActionTextColor(getResources().getColor(R.color.red));
                        snackbar.setAction("Check Again", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CheckInterNetAccess();
                            }
                        });
                        snackbar.show();
                    }
                }
            });
        }
        else {
            // not connected to internet
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Make sure you are connected to the internet", Snackbar.LENGTH_INDEFINITE);
            snackbar.setActionTextColor(getResources().getColor(R.color.red));
            snackbar.setAction("DISMISS", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

}