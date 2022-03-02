package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ordertype extends AppCompatActivity {
    String title,id,price,url,size,desc,productcount,catagory,shop_id,token,UID;
    TextView producttitle,productprice,psize,productdesc;
    LinearLayout gotowalktry,gotohometry,videocall;
    ImageView productimage;
    int myNum = 0;
    int newid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertype);

        Bundle bundle=getIntent().getExtras();
if(bundle==null) {
    title ="error";
    id = "error";
   price = "error";
   url ="error";
    size ="error";
 desc ="error";
  productcount ="error";
  catagory ="error";
  shop_id ="error";


}
        title = bundle.getString("title");
        id = bundle.getString("id");
        price = bundle.getString("price");
        url = bundle.getString("url1");
        size = bundle.getString("size");
        desc = bundle.getString("desc");
        productcount = bundle.getString("productcount");
        catagory = bundle.getString("catogary");
        shop_id = bundle.getString("shopid");


        FirebaseDatabase.getInstance().getReference().child("User").child(shop_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        token = dataSnapshot.child("token").getValue(String.class);
                       //
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


///////////////////////decrese item by one code
//        Toast.makeText(ordertype.this,productcount, Toast.LENGTH_SHORT).show();
//        try {
//            myNum = Integer.parseInt(productcount);
//            myNum--;
////            Toast.makeText(ordertype.this,""+myNum, Toast.LENGTH_SHORT).show();
//        } catch(NumberFormatException nfe) {
////            Toast.makeText(ordertype.this,"Could not parse " + nfe, Toast.LENGTH_SHORT).show();
//            Log.d("error", String.valueOf(nfe));
//        }
        //////////////////code end here

//        FirebaseDatabase.getInstance().getReference().child("product").child(id)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        shop_id= dataSnapshot.child("shop id").getValue(String.class).toString();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

//

//////////////////////handler
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                FirebaseDatabase.getInstance().getReference().child("User").child(shop_id)
////                        .addListenerForSingleValueEvent(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////
////                                token= dataSnapshot.child("token").getValue(String.class);
////
////
////
////                            }
////
////                            @Override
////                            public void onCancelled(@NonNull DatabaseError error) {
////
////                            }
////                        });
//
//
//
//
////                Toast.makeText(ordertype.this, token, Toast.LENGTH_SHORT).show();
////               ///////////////////////shopid
////                newid = Integer.parseInt(id);
////                FirebaseDatabase.getInstance().getReference().child("User").child("bd4VaV3VImhI99OUe9O2zNopuPM2")
////                        .addListenerForSingleValueEvent(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////                                token= dataSnapshot.child("token").getValue(String.class);
////
////                            }
////
////                            @Override
////                            public void onCancelled(@NonNull DatabaseError error) {
////
////                            }
////                        });
////                Toast.makeText(ordertype.this,"shop ID"+token, Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        },3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                videocall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent starthome = new Intent(ordertype.this, Videocall_outgoing.class);
                        UID=FirebaseAuth.getInstance().getUid();
                        Toast.makeText(ordertype.this,"TOKEN"+token, Toast.LENGTH_SHORT).show();
                        FcmNotificationsSender notificationsSender=new FcmNotificationsSender(token,"Video call",UID,getApplicationContext(),ordertype.this);
                        notificationsSender.SendNotifications();
                        starthome.putExtra("title",title);
                        starthome.putExtra("price",price);
                        starthome.putExtra("size",size);
                        starthome.putExtra("desc",desc);
                        starthome.putExtra("id",id);
                        starthome.putExtra("url1",url);
                        starthome.putExtra("catagary",catagory);
                        starthome.putExtra("token",token);
                        starthome.putExtra("shop_id",shop_id);
                        startActivity(starthome);
                    }
                });

            }
        },2000);

        productimage=findViewById(R.id.productimage);
        producttitle=findViewById(R.id.producttitle);
        productprice=findViewById(R.id.productprice);
        videocall=findViewById(R.id.videocall);

        psize=findViewById(R.id.psize);
        productdesc=findViewById(R.id.productdesc);

        productprice.setText(price+"₹");
    producttitle.setText(title);
    psize.setText(size);
    productdesc.setText(desc);
    Picasso.get().load(url).into(productimage);
    ////////////////////walktry
        gotowalktry=findViewById(R.id.gotowalktry);
        gotowalktry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starthome = new Intent(ordertype.this, walkTry.class);
                starthome.putExtra("url1",url);
                starthome.putExtra("id",id);
                starthome.putExtra("catagary",catagory);
                starthome.putExtra("title",title);
                starthome.putExtra("size",size);
                starthome.putExtra("price",price);
                starthome.putExtra("desc",desc);
                starthome.putExtra("shopid",shop_id);

                startActivity(starthome);

            }
        });

////////////////////////hometry
        gotohometry=findViewById(R.id.gotohometry);
        gotohometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starthome = new Intent(ordertype.this, homeTry.class);
                starthome.putExtra("title",title);
                starthome.putExtra("price",price);
                starthome.putExtra("size",size);
                starthome.putExtra("desc",desc);
                starthome.putExtra("id",id);
                starthome.putExtra("url1",url);
                starthome.putExtra("catagary",catagory);
                starthome.putExtra("shopid",shop_id);
                startActivity(starthome);
            }
        });


        ///////////////////video call




    }
}