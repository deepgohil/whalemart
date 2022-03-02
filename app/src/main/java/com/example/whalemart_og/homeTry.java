package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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

public class homeTry extends AppCompatActivity {
    String title,id,price,url,size,desc,paymentType,cat;
    ImageView productimage;
    RadioGroup radioGroup;
    RadioButton radiobtn;
    TextView producttitle,productprice,hsize,productdesc,priceforbuy,plus,totalPrice,setsize;
    LinearLayout delevery;
    Button confirm;
    Long tsLong;
    EditText address,username,mobilenumber,pincodeet;
    String ts,ordetime;
    int myNum;
    String thisDate;
    String shop_id;
    String mail;
    String image;
    String name;
    String phone;
    String Address,pincode;
    LinearLayout sizedetails,getdiretion;
    private FusedLocationProviderClient mFusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_try);
        title=getIntent().getStringExtra("title");
        id=getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");
        url=getIntent().getStringExtra("url1");
        size=getIntent().getStringExtra("size");
        desc=getIntent().getStringExtra("desc");
        cat=getIntent().getStringExtra("catagary");
        shop_id=getIntent().getStringExtra("shop_id");



        radioGroup=findViewById(R.id.radioGroup);

        productimage=findViewById(R.id.productimage);
        priceforbuy=findViewById(R.id.priceforbuy);
        producttitle=findViewById(R.id.producttitle);
        mobilenumber=findViewById(R.id.mobilenumber);
        productprice=findViewById(R.id.productprice);
        getdiretion=findViewById(R.id.getdiretion);
        pincodeet=findViewById(R.id.pincodeet);
//        psize=findViewById(R.id.psize);
        productdesc=findViewById(R.id.productdesc);
        ///////////////////////data to hide
        plus=findViewById(R.id.plus);
        delevery=findViewById(R.id.delevery);
        /////////////////////Totalprice
        totalPrice=findViewById(R.id.totalPrice);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        ///////////////////setsize
        setsize=findViewById(R.id.setsize);
        hsize=findViewById(R.id.hsize);
        sizedetails=findViewById(R.id.sizedetails);
//        Toast.makeText(homeTry.this, cat, Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        name = dataSnapshot.child("name").getValue(String.class);
                        phone = dataSnapshot.child("phone").getValue(String.class);


                        username.setText(name);
                        mobilenumber.setText(phone);


                        //////////////////////image load


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
if(cat.equals("shoes"))
{
    sizedetails.setVisibility(View.VISIBLE);
//    setsize.setVisibility(View.GONE);
//    hsize.setVisibility(View.GONE);
//    size=" ";
    setsize.setText(size);
}

if(cat.equals("cloths"))
        {
            sizedetails.setVisibility(View.VISIBLE);
//    setsize.setVisibility(View.GONE);
//    hsize.setVisibility(View.GONE);
//    size=" ";
            setsize.setText(size);
        }
//        setsize.setText(size);
        //////////////////////////////////////set address on automatic location
        ///////////////////location
        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);
        getdiretion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlocation();
            }
        });
//////////////////////////////////////get shopkeeper ID
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
        //////////////////////////////////////////////getuser name


        try {
            myNum = Integer.parseInt(price);

        } catch(NumberFormatException nfe) {
            Toast.makeText(homeTry.this,"Could not parse " + nfe, Toast.LENGTH_SHORT).show();
        }
        int finalprice=myNum+50;
        totalPrice.setText(""+finalprice+" ₹");
        if (myNum>499)
        {
plus.setVisibility(View.GONE);
delevery.setVisibility(View.GONE);
totalPrice.setText(price+" ₹");
        }

        productprice.setText(price+" ₹");
        priceforbuy.setText(price+" ₹");
        producttitle.setText(title);
//        psize.setText(size);
        productdesc.setText(desc);
        Picasso.get().load(url).into(productimage);
        ////////////////////////////////////////confirm
        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=radioGroup.getCheckedRadioButtonId();
                radiobtn=findViewById(id);
                Toast.makeText(homeTry.this, radiobtn.getText(), Toast.LENGTH_SHORT).show();
                    if (address.getText().toString().isEmpty())
                    {
                        Toast.makeText(homeTry.this, "Please enter Address", Toast.LENGTH_SHORT).show();
                    }
                    else if (username.getText().toString().isEmpty())
                    {
                        Toast.makeText(homeTry.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        processCODorder();
                    }
            }
        });
        tsLong = System.currentTimeMillis()/1000;
        ts= tsLong.toString();


        ordetime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        thisDate = df.format(c);
    }

    private void getpersnaldata() {



    }

    private void processCODorder() {
        HashMap<String,Object> map = new HashMap<>();
//        map.put("productId",id);
        map.put("productId",id);
        map.put("title",title);
        map.put("sAddress",address.getText().toString());
        map.put("time",ordetime.toString());
        map.put("orderId",ts);
        map.put("imageurl",url);
        map.put("size",size);
        map.put("price",totalPrice.getText().toString());
        map.put("desc",desc);
        map.put("shopid",shop_id);







        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("homeTry")
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(homeTry.this, "Sucess", Toast.LENGTH_SHORT).show();
//                        Intent startmain = new Intent(homeTry.this, MainActivity.class);
//                        startActivity(startmain);
                        savetoshop(ts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(homeTry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void savetoshop(String ts) {
        HashMap<String,Object> map = new HashMap<>();
//        map.put("productId",id);
        map.put("productId",id);
        map.put("title",title);
        map.put("Buyerid",FirebaseAuth.getInstance().getUid());
        map.put("time",ordetime.toString());
        map.put("orderId",ts);
        map.put("imageurl",url);
        map.put("username",username.getText().toString());
        map.put("size",size);
        map.put("price",totalPrice.getText().toString());
        map.put("desc",desc);
        map.put("date",thisDate);
        map.put("Address",address.getText().toString());

        ///////////////////important

        map.put("phonenumber",mobilenumber.getText().toString());
        map.put("paymentmode","offline");
        map.put("ordertype","homeTry");
        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(shop_id)
                .child("yourorders")
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(homeTry.this, "Sucess", Toast.LENGTH_SHORT).show();
                        Intent startmain = new Intent(homeTry.this, MainActivity.class);
                        startActivity(startmain);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(homeTry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getlocation() {
        if (ActivityCompat.checkSelfPermission(homeTry.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            setlocation();
        } else
            ActivityCompat.requestPermissions(homeTry.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
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
                    Geocoder geocoder=new Geocoder(homeTry.this,Locale.getDefault());
                    try {
                        List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        double latitude= (double) addressList.get(0).getLatitude();
                        double longitude= (double) addressList.get(0).getLongitude();
                        Address=addressList.get(0).getAddressLine(0);
                        pincode = addressList.get(0).getPostalCode();

                        address.setText(Address);
                        pincodeet.setText(pincode);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(homeTry.this, "ERROR ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}