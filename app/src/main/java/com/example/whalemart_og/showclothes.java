package com.example.whalemart_og;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class showclothes extends AppCompatActivity {
SearchView search;
    RecyclerView recycle;
    AdapterSeller adapterSeller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showclothes);
//        search=findViewById(R.id.search);
//        datatosearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchdata(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                searchdata(newText);
//                return false;
//            }
//        });
        recycle=findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModelProduct> options =
                new FirebaseRecyclerOptions.Builder<ModelProduct>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cloths"), ModelProduct.class)
                        .build();

        adapterSeller=new AdapterSeller(options);
        recycle.setAdapter(adapterSeller);




    }
//
//
//    private void searchdata(String query) {
//        FirebaseRecyclerOptions<ModelProduct> options =
//                new FirebaseRecyclerOptions.Builder<ModelProduct>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cloths").orderByChild("Title").startAt(query).endAt(query+"\uf8ff"), ModelProduct.class)
//                        .build();
//
//        adapterSeller=new AdapterSeller(options);
//        adapterSeller.startListening();
//        recycle.setAdapter(adapterSeller);
//    }
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