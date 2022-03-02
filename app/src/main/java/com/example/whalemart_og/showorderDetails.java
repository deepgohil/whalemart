package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class showorderDetails extends AppCompatActivity {
String id;
String name_,phonenumber_,adddress_,title_,size_,price_,time_,paymentmode_,ordertype_,orderID_,url,buyid_;
TextView name,phonenumber,adddress,title,size,price,time,paymentmode,ordertype,orderID;
ImageView imgproduct;
Button deliverd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showorder_details);

        id=getIntent().getStringExtra("id");

        name=findViewById(R.id.name);
        phonenumber=findViewById(R.id.phonenumber);
        adddress=findViewById(R.id.adddress);
        title=findViewById(R.id.title);
        size=findViewById(R.id.size);
        imgproduct=findViewById(R.id.imgproduct);
        price=findViewById(R.id.price);
        time=findViewById(R.id.time);
      paymentmode=findViewById(R.id.paymentmode);
        ordertype=findViewById(R.id.ordertype);
        orderID=findViewById(R.id.orderID);
        deliverd=findViewById(R.id.deliverd);




        FirebaseDatabase.getInstance().getReference().child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("yourorders")
                .child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String name_,phonenumber_,adddress_,title_,size_,price_,time_,paymentmode_,ordertype_,orderID_;

                        name_ = dataSnapshot.child("username").getValue(String.class);
                        phonenumber_ = dataSnapshot.child("phonenumber").getValue(String.class);
                      adddress_ = dataSnapshot.child("Address").getValue(String.class);
                      title_=dataSnapshot.child("title").getValue(String.class);
                      size_=dataSnapshot.child("size").getValue(String.class);
                      price_=dataSnapshot.child("price").getValue(String.class);
                     time_=dataSnapshot.child("time").getValue(String.class);
                        paymentmode_=dataSnapshot.child("paymentmode").getValue(String.class);
                        ordertype_=dataSnapshot.child("ordertype").getValue(String.class);
                        orderID_=dataSnapshot.child("orderId").getValue(String.class);
                        url=dataSnapshot.child("imageurl").getValue(String.class);
                        buyid_=dataSnapshot.child("Buyerid").getValue(String.class);



                        name.setText(name_);
                        phonenumber.setText( phonenumber_);
                        adddress.setText(adddress_);
                        title.setText(title_);
                        size.setText(size_);
                        price.setText( price_);
                        time.setText(time_);
                        paymentmode.setText(paymentmode_);
                        ordertype.setText( ordertype_);
                        orderID.setText(orderID_);
//                        mobilenumber.setText(phone);
                        Picasso.get().load(url).into(imgproduct);


                        //////////////////////image load


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(showorderDetails.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
//        if(ordertype_.equals("walk try"))
//        {
//            adddress.setVisibility(View.GONE);
//        }

        deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("yourorders")
                        .child(id)
                        .removeValue();

                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(buyid_)
                        .child(ordertype_)
                        .child(id)
                        .removeValue();



                savetohistory();

            }
        });
    }

    private void savetohistory() {
        HashMap<String,Object> map = new HashMap<>();
        map.put( "name", name_);
        map.put("phonenumber",phonenumber_);
        map.put("address",adddress_);
        map.put("title",title_);
        map.put("size",size_);
        map.put("price",price_);
        map.put("token",ordertype_);
        map.put("imagg",url);





        FirebaseDatabase.getInstance().getReference()
                .child("history")
                .child(FirebaseAuth.getInstance().getUid())
                .push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(showorderDetails.this, MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });
    }
}