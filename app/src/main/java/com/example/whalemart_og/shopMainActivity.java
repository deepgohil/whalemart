package com.example.whalemart_og;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class shopMainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);



        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new homeShop()).commit();
        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment temp=null;
                switch (item.getItemId())
                {

                    case R.id.homeshop:
                        temp=new homeShop();
                        break;
                    case R.id.product :
                        temp=new shop_product_show();
                        break;
                    case R.id.notification :
                        temp=new notification();

                        break;
                    case R.id.profile :
                        temp=new you();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();

                return true;
            }
        });


    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(shopMainActivity.this, MainActivity.class));
        finish();
    }

}