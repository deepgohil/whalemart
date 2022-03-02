package com.example.whalemart_og.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.whalemart_og.R;
import com.example.whalemart_og.Videocall_incoming;
import com.example.whalemart_og.home;
import com.example.whalemart_og.fragment.notification;
import com.example.whalemart_og.search;
import com.example.whalemart_og.you;
import com.example.whalemart_og.login;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    String check,UID;
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

        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {

                        check = dataSnapshot.child("call").getValue(String.class);
                        UID = dataSnapshot.child("incominguid").getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                    }
                });
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(check.equals("true"))
                {
                    Intent intent = new Intent(MainActivity.this, Videocall_incoming.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("UID",UID);
                    startActivity(intent);

                }
            }
        },5000);

        Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                    check="error";
                    UID="error";

            }
        },6000);




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


//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },2000);

    }

}