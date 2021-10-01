package com.example.whalemart_og;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class shopMainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigation);


        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new homeShop()).commit();
//        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item)
//            {
//                Fragment temp=null;
//                switch (item.getItemId())
//                {
//
//                    case R.id.home :
//                        temp=new home();
//                        break;
//                    case R.id.search : temp=new search();
//                        break;
//                    case R.id.notification : temp=new notification();
//
//                        break;
//                    case R.id.you : temp=new you();
//                        break;
//
//                }
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();
//
//                return true;
//            }
//        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(shopMainActivity.this, MainActivity.class));
        finish();
    }
}