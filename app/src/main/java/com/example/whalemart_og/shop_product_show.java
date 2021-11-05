package com.example.whalemart_og;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class shop_product_show extends Fragment {
    View view;
    RecyclerView recycle;
    AdapterToShopOwnerShowProduct adapterToShopOwnerShowProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_shop_product_show, container, false);

        recycle=view.findViewById(R.id.recycle);

        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycle.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<ModelToShopOwnerShowProduct> options =
                new FirebaseRecyclerOptions.Builder<ModelToShopOwnerShowProduct>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                                .child("userproducts"), ModelToShopOwnerShowProduct.class)
                        .build();


        adapterToShopOwnerShowProduct=new AdapterToShopOwnerShowProduct(options);
        recycle.setAdapter(adapterToShopOwnerShowProduct);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterToShopOwnerShowProduct.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterToShopOwnerShowProduct.stopListening();

    }
}