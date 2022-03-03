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

public class history_of_user extends AppCompatActivity {
    ImageView erroimage;
    RecyclerView loadproduct;
    AdapterHistory adapterHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_user);
        erroimage=findViewById(R.id.erroimage);
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("history")) {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(history_of_user.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        loadproduct.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Modelhistory> options =
                new FirebaseRecyclerOptions.Builder<Modelhistory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("history"), Modelhistory.class)
                        .build();

        adapterHistory =new AdapterHistory(options);
        loadproduct.setAdapter( adapterHistory);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(history_of_user.this, MainActivity.class));
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterHistory.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterHistory.stopListening();

    }
}