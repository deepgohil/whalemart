package com.example.whalemart_og;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class showshoes extends AppCompatActivity {
    RecyclerView recycle;
    AdapterSeller adapterSeller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showshoes);

        recycle=findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModelProduct> options =
                new FirebaseRecyclerOptions.Builder<ModelProduct>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shoe"), ModelProduct.class)
                        .build();

        adapterSeller=new AdapterSeller(options);
        recycle.setAdapter(adapterSeller);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterSeller.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterSeller.stopListening();

    }
}