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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class you extends Fragment {
    FirebaseAuth firebaseAuth;
    ProgressBar youprogressbar;
    String mail;
    String image;
    String name;
    String phone;
    TextView username,userphonenumber,useremail;
    View view;
    ImageView profile_image;
    Button log_out,noti;
    String userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_you, container, false);

        ///////////////////////////////findingview
//        useremail=view.findViewById(R.id.useremail);
        youprogressbar=view.findViewById(R.id.youprogressbar);
        userphonenumber=view.findViewById(R.id.userphonenumber);
        username=view.findViewById(R.id.username);
        profile_image=view.findViewById(R.id.profile_image);
        log_out=view.findViewById(R.id.logout);
        userid=FirebaseAuth.getInstance().getUid();
        useremail=view.findViewById(R.id.mymail);
//        noti=view.findViewById(R.id.noti);
        //////////////////////////////////////////////////////////////////////noti
//        noti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),
//                        add_product.class);
//                startActivity(intent);
//            }
//        });


        ////////////////////////////////////////signout
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("token","");

                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseAuth.getInstance().signOut();
                                Intent i = new Intent(getActivity(),
                                        MainActivity.class);
                                getActivity().finish();
                            }
                        });
            }
        });

        ///////////////////
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mail = dataSnapshot.child("email").getValue(String.class);
                        image = dataSnapshot.child("image").getValue(String.class);
                        name = dataSnapshot.child("name").getValue(String.class);
                        phone = dataSnapshot.child("phone").getValue(String.class);

                        Log.wtf("TAG", mail+image+name+phone);
                        username.setText(name);
                        userphonenumber.setText(phone);
                        useremail.setText(mail);

                        //////////////////////image load

                        Picasso.get().load(image).into(profile_image);
                        youprogressbar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return view;
    }

}