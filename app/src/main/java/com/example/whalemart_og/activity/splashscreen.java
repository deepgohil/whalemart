package com.example.whalemart_og.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.whalemart_og.R;

public class splashscreen extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent startmain= new Intent(splashscreen.this, MainActivity.class);
                startActivity(startmain);
                finish();
            }
        },1000);
    }
}