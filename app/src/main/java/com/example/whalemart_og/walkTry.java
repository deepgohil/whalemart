package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class walkTry extends AppCompatActivity {
    ImageView imgproduct;
    TextView shopname, description, currentTime, orderId,getlocation;
    LinearLayout  openmap;
    String urlproduct;
    Button confirm;
    String ts, ordetime, id;
    Long tsLong;
    String shopaddress, shopnamestring;
    String Address;
    String name,title,size,price,desc,shop_id;
    String phone;
    String thisDate;
    String flag;
    EditText myadd;
    ///////////////new

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;

    //    private SettingsClient mSettingsClient;
//    private LocationRequest mLocationRequest;
//    private LocationSettingsRequest mLocationSettingsRequest;
//    private LocationCallback mLocationCallback;
//    private Location mCurrentLocation;
//    // boolean flag to toggle the ui
//    private Boolean mRequestingLocationUpdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_try);
        imgproduct = findViewById(R.id.imgproduct);
        description = findViewById(R.id.description);
        currentTime = findViewById(R.id.currentTime);
        openmap = findViewById(R.id.openmap);
        orderId = findViewById(R.id.orderId);
        shopname = findViewById(R.id.shopname);
//        getlocation=findViewById(R.id.getlocation);
//        myadd=findViewById(R.id.myadd);


        urlproduct = getIntent().getStringExtra("url1");
        id = getIntent().getStringExtra("id");
       title = getIntent().getStringExtra("title");
       size = getIntent().getStringExtra("size");
       price = getIntent().getStringExtra("price");
       desc= getIntent().getStringExtra("desc");

        Picasso.get().load(urlproduct).into(imgproduct);
        ////////////////////////////////////////////////////////////////
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        name = dataSnapshot.child("name").getValue(String.class);
                        phone = dataSnapshot.child("phone").getValue(String.class);




                        //////////////////////image load


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        ////////////////shop ID.
        FirebaseDatabase.getInstance().getReference().child("product").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        shop_id = dataSnapshot.child("shop id").getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
/////////////////////////////////////////////get shop name and address
        //get shop name and address
        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);
        //////////////////////////////////////////

        FirebaseDatabase.getInstance().getReference().child("product").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        shopnamestring = dataSnapshot.child("shopname").getValue(String.class);
                        shopaddress = dataSnapshot.child("shopaddress").getValue(String.class);
                        shopname.setText(shopnamestring);
                        description.setText(shopaddress);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(walkTry.this, "ERROR ", Toast.LENGTH_SHORT).show();
                    }
                });

//////
        openmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlocation();

            }
        });

//////////////////////////////////////////////////////////////////

        tsLong = System.currentTimeMillis() / 1000;
        ts = tsLong.toString();
        orderId.setText(ts);

        ordetime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        currentTime.setText(ordetime);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processOrder();

            }
        });

    }

    private void getlocation() {
        if (ActivityCompat.checkSelfPermission(walkTry.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            setlocation();
        } else
            ActivityCompat.requestPermissions(walkTry.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
    }

    private void setlocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                if(location!=null)
                {
                    Geocoder geocoder=new Geocoder(walkTry.this,Locale.getDefault());
                    try {
                        List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        double latitude= (double) addressList.get(0).getLatitude();
                        double longitude= (double) addressList.get(0).getLongitude();
                        Address=addressList.get(0).getAddressLine(0);
                       opengooglemaps();



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(walkTry.this, "ERROR ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void opengooglemaps() {
        try {
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +Address +"/" + shopaddress);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            holder.orderId.getContext().startActivity(intent);
         startActivity(intent);
        }
        catch (ActivityNotFoundException e)
        {
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +Address +"/" + shopaddress);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//          ((Activity)holder.orderId.getContext()).finish();

        }
    }

    private void processOrder() {


            HashMap<String, Object> map = new HashMap<>();
            map.put("shopname", shopname.getText().toString());
            map.put("daddress", description.getText().toString());
            map.put("time", ordetime);
            map.put("ID", ts);
            map.put("productId", id);
            map.put("imageurl", urlproduct);

            FirebaseDatabase.getInstance().getReference()
                    .child("User")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("walkTry")
                    .child(ts)
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(walkTry.this, "Sucess", Toast.LENGTH_SHORT).show();
//                            Intent startmain = new Intent(walkTry.this, MainActivity.class);
//                            startActivity(startmain);
                            savetoshop(ts);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(walkTry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        tsLong = System.currentTimeMillis()/1000;
        ts= tsLong.toString();


        ordetime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        thisDate = df.format(c);
        }
        @Override
        public void onBackPressed () {
            super.onBackPressed();
            startActivity(new Intent(walkTry.this, MainActivity.class));
            finish();
        }
    private void savetoshop(String ts) {
        HashMap<String,Object> map = new HashMap<>();
//        map.put("productId",id);
        map.put("productId",id);
        map.put("title",title);
        map.put("Buyerid",FirebaseAuth.getInstance().getUid());
        map.put("time",ordetime.toString());
        map.put("orderId",ts);
        map.put("imageurl",urlproduct);
        map.put("username",name);
        map.put("size",size);
        map.put("price",price);
        map.put("desc",desc);
        map.put("date",thisDate);
        map.put("Address","walk try");

        ///////////////////important

        map.put("phonenumber",phone);
        map.put("paymentmode","offline");
        map.put("ordertype","walk try");
        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(shop_id)
                .child("yourorders")
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                            Toast.makeText(walkTry.this, "Sucess", Toast.LENGTH_SHORT).show();
                            Intent startmain = new Intent(walkTry.this, MainActivity.class);
                            startActivity(startmain);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(walkTry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }

