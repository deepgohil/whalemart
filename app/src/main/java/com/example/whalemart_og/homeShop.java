package com.example.whalemart_og;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class homeShop extends Fragment {

Button add_product;
TextView hidedata;
ImageView erroimage;
RecyclerView recycle;
AdapterShowOrderToSeller adapterShowOrderToSeller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          View view= inflater.inflate(R.layout.fragment_home_shop, container, false);
//        hidedata=view.findViewById(R.id.hidedata);
        erroimage=view.findViewById(R.id.erroimage);
        ////////////////recycle

        recycle=view.findViewById(R.id.recycle);

        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recycle.setLayoutManager(layoutManager);



        FirebaseRecyclerOptions<ModelShowOrderToSeller> options=
                new FirebaseRecyclerOptions.Builder<ModelShowOrderToSeller>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("yourorders"), ModelShowOrderToSeller.class)
                        .build();
        adapterShowOrderToSeller=new AdapterShowOrderToSeller(options);
        recycle.setAdapter(adapterShowOrderToSeller);


        ///////////////////////////////////////////////////
        add_product=view.findViewById(R.id.add_product);
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        add_product.class);
                startActivity(intent);
            }
        });
//        erroimage=view.findViewById(R.id.erroimage);
//        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot snapshot) {
//                        if (snapshot.hasChild("walkTry")) {
//                            erroimage.setVisibility(View.GONE);
//                            // it exists!
//                        }
//                        else{
//                            erroimage.setVisibility(View.VISIBLE);
//                            // does not exist
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
          return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterShowOrderToSeller.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterShowOrderToSeller.stopListening();

    }
}