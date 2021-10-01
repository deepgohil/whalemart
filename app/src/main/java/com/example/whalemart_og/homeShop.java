package com.example.whalemart_og;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          View view= inflater.inflate(R.layout.fragment_home_shop, container, false);
//        hidedata=view.findViewById(R.id.hidedata);
        erroimage=view.findViewById(R.id.erroimage);
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
}