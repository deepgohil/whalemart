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

import com.example.whalemart_og.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class walktry_order_details_show_self extends AppCompatActivity {
//String id,dAddress,sAddress,title;
    ImageView imgproduct;
    Button deliverd;
    String id;
    TextView title,size,price,time,paymentmode,orderID;
String name_,phonenumber_,adddress_,title_,size_,price_,time_,paymentmode_,ordertype_,orderID_,url,dAddress_,sAddress_;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walktry_order_details_show_self);

        title=findViewById(R.id.title);
        size=findViewById(R.id.size);
        imgproduct=findViewById(R.id.imgproduct);
        price=findViewById(R.id.price);
        time=findViewById(R.id.time);
        paymentmode=findViewById(R.id.paymentmode);

        orderID=findViewById(R.id.orderID);
        deliverd=findViewById(R.id.deliverd);

        Bundle bundle=getIntent().getExtras();
        if(bundle==null) {

            id = "error";

        }
        id = bundle.getString("id");
        ///////////////////
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .child("walkTry")
                .child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dAddress_ = dataSnapshot.child("dAddress").getValue(String.class);
                        sAddress_ = dataSnapshot.child("sAddress").getValue(String.class);
                        title_=dataSnapshot.child("title").getValue(String.class);
                        size_=dataSnapshot.child("size").getValue(String.class);
                        price_=dataSnapshot.child("price").getValue(String.class);
                        time_=dataSnapshot.child("time").getValue(String.class);
                        orderID_=dataSnapshot.child("orderId").getValue(String.class);
                        url=dataSnapshot.child("imageurl").getValue(String.class);


                        title.setText(title_);
                        size.setText(size_);
                        price.setText( price_);
                        time.setText(time_);
//                        paymentmode.setText(paymentmode_);
                        orderID.setText(orderID_);
//                        mobilenumber.setText(phone);
                        Picasso.get().load(url).into(imgproduct);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("walkTry")
                        .child(id)
                        .removeValue();

                startActivity(new Intent(walktry_order_details_show_self.this, MainActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed () {
        super.onBackPressed();
        startActivity(new Intent(walktry_order_details_show_self.this, MainActivity.class));
        finish();
    }
}