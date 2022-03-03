package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showHomeTryProduct extends AppCompatActivity {
    ImageView erroimage;

    RecyclerView loadproduct;
    AdapterHometry adapterHometry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_home_try_product);
        loadproduct=findViewById(R.id.loadproduct);
        LinearLayoutManager layoutManager = new LinearLayoutManager(showHomeTryProduct.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        loadproduct.setLayoutManager(layoutManager);


        erroimage=findViewById(R.id.erroimage);
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("homeTry")) {
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

        FirebaseRecyclerOptions<Modelhometry> options =
                new FirebaseRecyclerOptions.Builder<Modelhometry>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("homeTry"), Modelhometry.class)
                        .build();

        adapterHometry =new AdapterHometry(options);

        loadproduct.setAdapter(adapterHometry);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(showHomeTryProduct.this, MainActivity.class));
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterHometry.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterHometry.stopListening();

    }
}