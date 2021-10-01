package com.example.whalemart_og;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.annotations.NonNull;

public class home extends Fragment {
    SearchView datatosearch;
    AdapterSeller adapterSeller;
    String type;
    RecyclerView recycle;
    ProgressBar recycleprogress;
    String mail;
    LinearLayout datatohide;
    ImageView walktry,homeTry,history;
//
    View view;


    LinearLayout hidecard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_home, container, false);


//        loadallproducts();
//       profile_image=view.findViewById(R.id.userimage);

        datatosearch=view.findViewById(R.id.datatosearch);
        recycle=view.findViewById(R.id.recycle);
        hidecard=view.findViewById(R.id.hidecard);
        datatohide=view.findViewById(R.id.datatohide);

        recycleprogress=view.findViewById(R.id.recycleprogress);//
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              recycleprogress.setVisibility(View.GONE);
              hidecard.setVisibility(View.GONE);


            }
        },2000);

        homeTry=view.findViewById(R.id.homeTry);
        homeTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), showHomeTryProduct.class);
                startActivity(intent);

            }
        });

        walktry=view.findViewById(R.id.walktry);
        walktry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), showwalkTryProduct.class);
                startActivity(intent);

            }
        });

        history=view.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), history_of_user.class);
                startActivity(intent);

            }
        });

//        datatosearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//try {
//adapterProductSeller.getFilter().filter(s);
//}
//catch (Exception e)
//{
//e.printStackTrace();
//}
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


//////////////////////////recycle
                recycle=view.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirebaseRecyclerOptions<ModelProduct> options =
                new FirebaseRecyclerOptions.Builder<ModelProduct>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product"), ModelProduct.class)
                        .build();

        adapterSeller=new AdapterSeller(options);
        recycle.setAdapter(adapterSeller);


        /////////////////////////////////////////////////////////////////////hide data for regular user
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        type = dataSnapshot.child("type").getValue(String.class);

                        if(type.equals("owner"))
                        {
                            datatohide.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        datatohide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openshop = new Intent(getActivity(), shopMainActivity.class);
                startActivity(openshop);
//                try {
//                    Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +"Kandivli, Apartment, Pravin Sanghavi Rd, Kandivali, Best Colony, Jethava Nagar, Kandivali West, Mumbai, Maharashtra 400067" +"/" +"Maharshi Karve Rd, Churchgate, Mumbai, Maharashtra 400020");
//                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//                    intent.setPackage("com.google.android.apps.maps");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//                catch (ActivityNotFoundException e)
//                {
//                    Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +"Kandivli, Apartment, Pravin Sanghavi Rd, Kandivali, Best Colony, Jethava Nagar, Kandivali West, Mumbai, Maharashtra 400067" +"/" +"Maharshi Karve Rd, Churchgate, Mumbai, Maharashtra 400020");
//                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                   startActivity(intent);
//
//                }
            }
        });
        /////////////////////////////////////////searching
//        dataTosearch=view.findViewById(R.id.dataTosearch);
datatosearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchdata(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        searchdata(newText);
        return false;
    }
});

        return view;
    }
//
//    private void loadallproducts() {
//        productList =new ArrayList<>();
//        ////////////////get all products
//        FirebaseDatabase.getInstance().getReference()
//                .child("product")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        productList.clear();
//                        for (DataSnapshot ds: snapshot.getChildren()){
//                            ModelProduct modelProduct= ds.getValue(ModelProduct.class);
//                            productList.add(modelProduct);
//                        }
//                        adapterProductSeller =new AdapterProductSeller(home.this,productList);
//                        ///
//                        recycle.setAdapter(adapterProductSeller);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//    }

    private void searchdata(String query) {
        FirebaseRecyclerOptions<ModelProduct> options =
                new FirebaseRecyclerOptions.Builder<ModelProduct>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").orderByChild("Title").startAt(query).endAt(query+"\uf8ff"), ModelProduct.class)
                        .build();

        adapterSeller=new AdapterSeller(options);
        adapterSeller.startListening();
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