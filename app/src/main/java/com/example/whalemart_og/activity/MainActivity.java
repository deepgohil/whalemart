package com.example.whalemart_og.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.whalemart_og.R;
import com.example.whalemart_og.home;
import com.example.whalemart_og.fragment.notification;
import com.example.whalemart_og.search;
import com.example.whalemart_og.you;
import com.example.whalemart_og.login;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            Intent intent=new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }

//        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE)

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new home()).commit();
        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigation);
        BadgeDrawable badgeDrawable=bnv.getOrCreateBadge(R.id.notification);
        badgeDrawable.setBackgroundColor(Color.RED);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        badgeDrawable.setMaxCharacterCount(2);
        badgeDrawable.setNumber(300);
        badgeDrawable.setVisible(true);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment temp=null;
                switch (item.getItemId())
                {

                    case R.id.home : temp=new home();
                        break;
                    case R.id.search : temp=new search();
                        break;
                    case R.id.notification : temp=new notification();
                        badgeDrawable.setVisible(false);
                        break;
                    case R.id.you : temp=new you();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();

                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }
}