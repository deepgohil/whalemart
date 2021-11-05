package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class product extends AppCompatActivity {
    EditText size;
ViewPager2 viewPager2;
Adapter adapter;
List<Data> data;
String url="https://firebasestorage.googleapis.com/v0/b/whalemart-og.appspot.com/o/product%2F1630481597739.png?alt=media&token=10d8827f-82be-4441-8019-598f4ff84965";
    String id;
    TextView productname,showprice,details,catagory,sizetv;
    String url2,url3,catagory1,shopid;
    ImageView sizechart;
    Button trynowbtn,buyNow;
    TextInputLayout tohide;
    CardView loadinganimation;
    ProgressBar progressBar;
    String mysize;
    String xs,s,m,l,xl,uk7,uk8,uk9,uk10,uk11,uk12,Eq;
   String title;
    String price;

    String description,catagoryname;
    int xs_size_q,xs_size_q2;


//    int k;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        sizetv=findViewById(R.id.sizetv);
        progressBar=findViewById(R.id.progressBar3);
        loadinganimation=findViewById(R.id.loadinganimation);
        trynowbtn=findViewById(R.id.trynowbtn);
        tohide=findViewById(R.id.tohide);
        sizechart=findViewById(R.id.sizechart);
        catagory=findViewById(R.id.catagory);
        sizechart.setVisibility(View.GONE);
        productname=findViewById(R.id.productname);
        showprice=findViewById(R.id.showprice);
        details=findViewById(R.id.details);
        size=findViewById(R.id.size);
        buyNow=findViewById(R.id.buyNow);


//        final String cat=getIntent().getStringExtra("catogary");
//        catagory.setText(cat);
////        if(cat.toString().equals("shoes"))
////        {
////            catagory.setText("shoes");
////        }
//        size.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clothssize();
//            }
//
//
//
//        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                loadinganimation.setVisibility(View.GONE);


            }
        },2000);


        title=getIntent().getStringExtra("title");
        id=getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");
        url=getIntent().getStringExtra("url1");
        catagoryname=getIntent().getStringExtra("catogary");



        productname.setText(title);
        showprice.setText("₹"+price);

        FirebaseDatabase.getInstance().getReference().child("product").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       description = dataSnapshot.child("description").getValue(String.class);
                       url2 = dataSnapshot.child("img2").getValue(String.class);
                       url3 = dataSnapshot.child("img3").getValue(String.class);
                       catagory1 = dataSnapshot.child("catagory").getValue(String.class);
                       shopid = dataSnapshot.child("shop id").getValue(String.class);
                        if (catagory1.toString().equals("cloths"))
                        {

//                            xs = dataSnapshot.child("xs_size").getValue(String.class);
                            catagory.setText("clothes");
                            xs = (String) dataSnapshot.child("xs_size").getValue();

                            s = dataSnapshot.child("s_size").getValue(String.class);
                            m = dataSnapshot.child("m_size").getValue(String.class);
                            l = dataSnapshot.child("l_size").getValue(String.class);
                            xl = dataSnapshot.child("xl_size").getValue(String.class);
//                            k=Integer.parseInt(String.valueOf(dataSnapshot.child("xs_size").getValue()));
                            setclothesdata();


                        }
                        else if(catagory1.toString().equals("shoes"))
                        {
                            catagory.setText("Shoes");
                            uk7 = dataSnapshot.child("uk 7").getValue(String.class);
                            uk8 = dataSnapshot.child("uk 8").getValue(String.class);
                            uk9 = dataSnapshot.child("uk 9").getValue(String.class);
                            uk10 = dataSnapshot.child("uk 10").getValue(String.class);
                            uk11 = dataSnapshot.child("uk 11").getValue(String.class);
                            uk12 = dataSnapshot.child("uk 12").getValue(String.class);
                            setsohedata();
                        }
                        else if(catagory1.toString().equals("elec"))
                        {
                            catagory.setText("electronics");
                            Eq=dataSnapshot.child("quantity").getValue(String.class);

                            size.setVisibility(View.GONE);
                            tohide.setVisibility(View.GONE);
                            sizetv.setVisibility(View.GONE);
                        }

                        viewPager2=findViewById(R.id.view_pager);
                        data=new ArrayList<>();
                        data.add(new Data(url));

                        data.add(new Data(url2));

                        data.add(new Data(url3));

                        adapter=new Adapter(data);
                        viewPager2.setAdapter(adapter);

                        details.setText(description);
//
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        ////////////////////size


//        else if(catagory1.toString().equals("shoes"))
//        {
//            showclothsize();
//
//        }
//        else {
//            size.setVisibility(View.GONE);
//        }
        trynowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(product.this, catagoryname, Toast.LENGTH_SHORT).show();
                mysize=size.getText().toString();
//                catagory.setText(mysize);
                if(catagory1.toString().equals("shoes"))
                {
                    validateShoeData();
                }
                if(catagory1.toString().equals("cloths"))
                {
                    validateClothsData();
                }
                if(catagory1.toString().equals("elec"))
                {
                    validateElecData();
                }


            }
        });



    }

    private void validateElecData() {

        if (Eq.equals("0"))
        {
            Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

        }
        else {
//            Intent startmain = new Intent(product.this, ordertype.class);
//            startmain.putExtra("title",title);
//            startmain.putExtra("id",id);
//            startmain.putExtra("price",price);
//            startmain.putExtra("url1",url);
//            startmain.putExtra("desc",description);
//            startmain.putExtra("productcount",Eq);
//
//            startActivity(startmain);
            HashMap<String,Object> map = new HashMap<>();
            map.put("title",title);
            map.put("id",id);
            map.put("price",price);
            map.put("url1",url);
            map.put("desc",description);
            map.put("productcount",Eq);
            map.put("size:","");

            FirebaseDatabase.getInstance().getReference()
                    .child("User")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("history")
                    .push()
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent startmain = new Intent(product.this, ordertype.class);
                            startmain.putExtra("title",title);
                            startmain.putExtra("id",id);
                            startmain.putExtra("price",price);
                            startmain.putExtra("url1",url);
                            startmain.putExtra("desc",description);
                            startmain.putExtra("productcount",Eq);
                            startmain.putExtra("catogary",catagoryname);
                            startmain.putExtra("shopid",shopid);
                            startActivity(startmain);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }

    }

    private void validateClothsData() {



        switch (mysize)
        {
            case "XS":

                if (xs.equals("0"))
                {
                    tosatError();

                }
                else {


                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","XS");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","XS");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }
                break;
            case "S":  if (s.equals("0"))
            {
                tosatError();
            }
            else {
                HashMap<String,Object> map = new HashMap<>();
                map.put("title",title);
                map.put("id",id);
                map.put("price",price);
                map.put("url1",url);
                map.put("size","S");
                map.put("desc",description);
                map.put("productcount",xs);

                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("history")
                        .push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent startmain = new Intent(product.this, ordertype.class);
                                startmain.putExtra("title",title);
                                startmain.putExtra("id",id);
                                startmain.putExtra("price",price);
                                startmain.putExtra("url1",url);
                                startmain.putExtra("size","S");
                                startmain.putExtra("desc",description);
                                startmain.putExtra("productcount",xs);
                                startmain.putExtra("catogary",catagoryname);
                                startmain.putExtra("shopid",shopid);
                                startActivity(startmain);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
                break;
            case "M":
                if (m.equals("0"))
                {
                    tosatError();
                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","M");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","M");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    }
                break;
            case "L":
                if (l.equals("0"))
            {
                tosatError();
            }
            else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","L");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","L");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


            }
                break;
            case "XL":
                if (xl.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","XL");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","XL");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }
                break;

            default:
                Toast.makeText(product.this, "please select correct size", Toast.LENGTH_SHORT).show();;
                break;


        }
    }

    private void tosatError() {

        Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

    }


    private void validateShoeData() {
        switch (mysize)
        {

            case "UK 7":
                if (uk7.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 7");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 7");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }
                break;
            case "UK 8":
                if (uk8.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 8");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 8");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                break;
            case "UK 9":
                if (uk9.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 9");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 9");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
            case "UK 10":
                if (uk10.equals("0"))
            {
                Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

            }
            else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 10");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 10");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

            }
                break;
            case "UK 11":
                if (uk11.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 11");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 11");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                break;
            case "UK 12":
                if (uk12.equals("0"))
                {
                    Toast.makeText(product.this,"out of stock", Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("id",id);
                    map.put("price",price);
                    map.put("url1",url);
                    map.put("size","UK 12");
                    map.put("desc",description);
                    map.put("productcount",xs);

                    FirebaseDatabase.getInstance().getReference()
                            .child("User")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child("history")
                            .push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent startmain = new Intent(product.this, ordertype.class);
                                    startmain.putExtra("title",title);
                                    startmain.putExtra("id",id);
                                    startmain.putExtra("price",price);
                                    startmain.putExtra("url1",url);
                                    startmain.putExtra("size","UK 12");
                                    startmain.putExtra("desc",description);
                                    startmain.putExtra("catogary",catagoryname);
                                    startmain.putExtra("productcount",xs);
                                    startmain.putExtra("shopid",shopid);
                                    startActivity(startmain);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                break;
            default:
                Toast.makeText(product.this, "please select correct size", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    private void setclothesdata() {

        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showclothsize();
            }


        });


    }

    private void setsohedata() {
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoesize();
            }


        });


    }

    private void clothssize() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select size")
                .setItems(constants.cloths, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sizetext=constants.cloths[which];
                        size.setText(sizetext);
                    }
                }).show();
    }
//
    private void showclothsize() {
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothssize();
            }



        });
    }
//
//    private void clothssize() {
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("Select size")
//                .setItems(constants.cloths, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String sizetext=constants.cloths[which];
//                        size.setText(sizetext);
//                    }
//                }).show();
//    }
//
//    private void showshoesize() {
//        size.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shoesize();
//            }
//
//
//        });
//    }
//
    private void shoesize() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select size")
                .setItems(constants.shoe, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sizetext=constants.shoe[which];
                        size.setText(sizetext);
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(product.this, MainActivity.class));
        finish();
    }
}