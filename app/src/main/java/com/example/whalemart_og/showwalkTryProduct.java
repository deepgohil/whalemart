package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whalemart_og.activity.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showwalkTryProduct extends AppCompatActivity {
    ImageView erroimage;
RecyclerView loadproduct;
Adatpterwalktry adatpterwalktry;
String data;
TextView datamsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        data=FirebaseAuth.getInstance().getUid();
        setContentView(R.layout.activity_show_showwalk_try_product);

//
        erroimage=findViewById(R.id.erroimage);
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("walkTry")) {
                            erroimage.setVisibility(View.GONE);
                            // it exists!
                        }
                        else{
                           erroimage.setVisibility(View.VISIBLE);
                            // does not exist
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        loadproduct=findViewById(R.id.loadproduct);

        LinearLayoutManager layoutManager = new LinearLayoutManager(showwalkTryProduct.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        loadproduct.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Modelwalktry> options =
                new FirebaseRecyclerOptions.Builder<Modelwalktry>()
                     .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("walkTry"), Modelwalktry.class)
                .build();

        adatpterwalktry =new Adatpterwalktry(options);
        loadproduct.setAdapter(adatpterwalktry);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(showwalkTryProduct.this, MainActivity.class));
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        adatpterwalktry.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adatpterwalktry.stopListening();

    }
}