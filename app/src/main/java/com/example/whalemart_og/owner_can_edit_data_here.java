package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class owner_can_edit_data_here extends AppCompatActivity {
    ProgressBar progressBar;
    String shopname,shopaddress;
    Button update,delete;
String title,id,price,url1,catagory,url2,url3;
///////////////////string for common things
String description_,actualprice_,realprice_;
///////////////////string for elect
String elecQuantity;
    /////////////////////string clothes
    String xs_size1,s_size1,m_size1,l_size1,xl_size1;
    EditText xs_size,s_size,m_size,l_size,xl_size;
    ////////////////////shoes
EditText suk7,suk8,suk9,suk10,suk11,suk12;
String suk71,suk81,suk91,suk101,suk111,suk121;
/////////////////////////////////////////////////////

    EditText titleone,description,actualprice,realprice,Elcquantity;


LinearLayout shoe,cloths,elec;
////////image
    ImageView img1,img2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_can_edit_data_here);

        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       shopname= dataSnapshot.child("shopname").getValue(String.class);
                       shopaddress= dataSnapshot.child("shopaddress").getValue(String.class);





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        id = getIntent().getStringExtra("id");
        url1 = getIntent().getStringExtra("url1");
        price = getIntent().getStringExtra("price");
        catagory = getIntent().getStringExtra("catagory");
        title = getIntent().getStringExtra("title");
//////////////////////////////image
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
 ////////////pb
        progressBar=findViewById(R.id.progressBar);
       /////////////button
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        ///////////////////////////////load image 1
        Picasso.get().load(url1).into(img1);

        Elcquantity=findViewById(R.id.Elcquantity);
        titleone=findViewById(R.id.title);
        ///////////////////cloths
        xs_size=findViewById(R.id.xs_size);
        s_size=findViewById(R.id.s_size);
        m_size=findViewById(R.id.m_size);
        l_size=findViewById(R.id.l_size);
        xl_size=findViewById(R.id.xl_size);

        /////////////shoes
        suk7=findViewById(R.id.suk7);
        suk8=findViewById(R.id.suk8);
        suk9=findViewById(R.id.suk9);
        suk10=findViewById(R.id.suk10);
        suk11=findViewById(R.id.suk11);
        suk12=findViewById(R.id.suk12);


        description=findViewById(R.id.description);
        actualprice=findViewById(R.id.actualprice);
        realprice=findViewById(R.id.realprice);

        cloths=findViewById(R.id.clothes);
        shoe=findViewById(R.id.shoes);
        elec=findViewById(R.id.elec);
        if (catagory.equals("elec"))
        {
            elec.setVisibility(View.VISIBLE);
            titleone.setText(title);
            realprice.setText(price);
            FirebaseDatabase.getInstance().getReference().child("product").child(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            description_= dataSnapshot.child("description").getValue(String.class);
                            actualprice_= dataSnapshot.child("actualprice").getValue(String.class);
                            realprice_= dataSnapshot.child("realprice").getValue(String.class);
                            elecQuantity= dataSnapshot.child("quantity").getValue(String.class);
                            url2= dataSnapshot.child("img2").getValue(String.class);
                            url3= dataSnapshot.child("img3").getValue(String.class);

                            Picasso.get().load(url2).into(img2);
                            Picasso.get().load(url3).into(img3);
                            description.setText(description_);
                           actualprice.setText(actualprice_);
                           realprice.setText(realprice_);
                           Elcquantity.setText(elecQuantity);

                            //////////////////////image load
//
//                            Picasso.get().load(image).into(profile_image);
//                            youprogressbar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }

        else if (catagory.equals("cloths"))
        {
           cloths.setVisibility(View.VISIBLE);
//
            titleone.setText(title);
            realprice.setText(price);

            FirebaseDatabase.getInstance().getReference().child("product").child(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            description_= dataSnapshot.child("description").getValue(String.class);
                            actualprice_= dataSnapshot.child("actualprice").getValue(String.class);
                            realprice_= dataSnapshot.child("realprice").getValue(String.class);

                            xs_size1=dataSnapshot.child("xs_size").getValue(String.class);
                            s_size1=dataSnapshot.child("s_size").getValue(String.class);
                            m_size1=dataSnapshot.child("m_size").getValue(String.class);
                            l_size1=dataSnapshot.child("l_size").getValue(String.class);
                            xl_size1=dataSnapshot.child("xl_size").getValue(String.class);
                            url2= dataSnapshot.child("img2").getValue(String.class);
                            url3= dataSnapshot.child("img3").getValue(String.class);

                            Picasso.get().load(url2).into(img2);
                            Picasso.get().load(url3).into(img3);


                            description.setText(description_);
                            actualprice.setText(actualprice_);
                            realprice.setText(realprice_);
                            xs_size.setText(xs_size1);
                            s_size.setText(s_size1);
                            m_size.setText(m_size1);
                            l_size.setText(l_size1);
                            xl_size.setText(xl_size1);
                            //////////////////////image load
//
//                            Picasso.get().load(image).into(profile_image);
//                            youprogressbar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        else if (catagory.equals("shoes"))
        {
            shoe.setVisibility(View.VISIBLE);
            titleone.setText(title);
            realprice.setText(price);

            FirebaseDatabase.getInstance().getReference().child("product").child(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            description_= dataSnapshot.child("description").getValue(String.class);
                            actualprice_= dataSnapshot.child("actualprice").getValue(String.class);
                            realprice_= dataSnapshot.child("realprice").getValue(String.class);

                            suk71= dataSnapshot.child("uk 7").getValue(String.class);
                            suk81= dataSnapshot.child("uk 8").getValue(String.class);
                            suk91= dataSnapshot.child("uk 9").getValue(String.class);
                            suk101= dataSnapshot.child("uk 10").getValue(String.class);
                            suk111= dataSnapshot.child("uk 11").getValue(String.class);
                            suk121= dataSnapshot.child("uk 12").getValue(String.class);
                            url2= dataSnapshot.child("img2").getValue(String.class);
                            url3= dataSnapshot.child("img3").getValue(String.class);

                            Picasso.get().load(url2).into(img2);
                            Picasso.get().load(url3).into(img3);


                            description.setText(description_);
                            actualprice.setText(actualprice_);
                            realprice.setText(realprice_);
                            suk7.setText(suk71);
                            suk8.setText(suk81);
                            suk9.setText(suk91);
                            suk10.setText(suk101);
                            suk11.setText(suk111);
                            suk12.setText(suk121);

                            //////////////////////image load
//
//                            Picasso.get().load(image).into(profile_image);
//                            youprogressbar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        }

        else{
            shoe.setVisibility(View.GONE);
            elec.setVisibility(View.GONE);
            cloths.setVisibility(View.GONE);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(catagory.equals("cloths"))
                {
                    cloths();
                }
                else if (catagory.equals("elec"))
                {
                    elec();
                } else if (catagory.equals("shoes"))
                {
                    shoes();
                }
                else {
                    Toast.makeText(owner_can_edit_data_here.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }


        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(catagory.equals("cloths"))
                {
                    clothsdel();
                }
                else if (catagory.equals("elec"))
                {
                    elecdel();
                } else if (catagory.equals("shoes"))
                {
                    shoesdel();
                }
                else {
                    Toast.makeText(owner_can_edit_data_here.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }




        });
    }

    private void shoesdel() {
        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();

        FirebaseDatabase.getInstance().getReference()
                .child("shoe")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();


        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("userproducts")
                .child(id)
                .removeValue();
        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(owner_can_edit_data_here.this, MainActivity.class));
        finish();
    }

    private void elecdel() {
        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();

        FirebaseDatabase.getInstance().getReference()
                .child("elec")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();


        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("userproducts")
                .child(id)
                .removeValue();
        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(owner_can_edit_data_here.this, MainActivity.class));
        finish();

    }

    private void clothsdel() {
        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();

        FirebaseDatabase.getInstance().getReference()
                .child("cloths")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue();


        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("userproducts")
                .child(id)
                .removeValue();
        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(owner_can_edit_data_here.this, MainActivity.class));
        finish();


    }

    private void shoes() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
//    EditText titleshoes,descriptionshoe,actualpriceshoe,realpriceshoe,suk7,suk8,suk9,suk10,suk11,suk12;//

        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("img1", url1);
        map.put("img2", url2);
        map.put("img3", url3);
        map.put("catagory", "shoes");
        map.put("Title", titleone.getText().toString().trim());
        map.put("description", description.getText().toString().trim());
        map.put("actualprice", actualprice.getText().toString().trim());
        map.put("realprice", realprice.getText().toString().trim());
        map.put("uk 7", suk7.getText().toString().trim());
        map.put("uk 8", suk8.getText().toString().trim());
        map.put("uk 9", suk9.getText().toString().trim());
        map.put("uk 10", suk10.getText().toString().trim());
        map.put("uk 11", suk11.getText().toString().trim());
        map.put("uk 12", suk12.getText().toString().trim());
        map.put("shop id", FirebaseAuth.getInstance().getUid());
        map.put("shopname", shopname);
        map.put("shopaddress", shopaddress);


        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        uploadshoes(id);

                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(add_product.this, MainActivity.class));
//                        finish();
                    }
                });
    }

    private void uploadshoes(String ts) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", url1);
        map.put("catagory", "shoes");////
        map.put("Title", titleone.getText().toString().trim());///
        map.put("realprice", realprice.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("shoe")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(add_product.this, MainActivity.class));
//                        finish();
                        selfupload("shoes");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void elec() {

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("img1", url1);
        map.put("img2", url2);
        map.put("img3", url3);
        map.put("catagory", "elec");
        map.put("Title", titleone.getText().toString().trim());
        map.put("description", description.getText().toString().trim());
        map.put("actualprice", actualprice.getText().toString().trim());
        map.put("realprice", realprice.getText().toString().trim());
        map.put("quantity",  Elcquantity.getText().toString().trim());
        map.put("shop id", FirebaseAuth.getInstance().getUid());
        map.put("shopname", shopname);
        map.put("shopaddress", shopaddress);


        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        uploadinelec(id);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadinelec(String ts) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", url1);///
        map.put("catagory", "elec");////
        map.put("Title", titleone.getText().toString().trim());///
        map.put("realprice", realprice.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("elec")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        selfupload("elec");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void cloths() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("img1", url1);
        map.put("img2", url2);
        map.put("img3", url3);
        map.put("catagory", "cloths");
        map.put("Title", titleone.getText().toString().trim());
        map.put("description", description.getText().toString().trim());
        map.put("actualprice", actualprice.getText().toString().trim());
        map.put("realprice", realprice.getText().toString().trim());
        map.put("xs_size", xs_size.getText().toString());
        map.put("s_size", s_size.getText().toString());
        map.put("m_size", m_size.getText().toString());
        map.put("l_size", l_size.getText().toString());
        map.put("xl_size", xl_size.getText().toString());
        map.put("shop id", FirebaseAuth.getInstance().getUid());
        map.put("shopname", shopname);
        map.put("shopaddress", shopaddress);


//    shopname = dataSnapshot.child("shopname").getValue(String.class);
//    address = dataSnapshot.child("address").getValue(String.class);
//

//        EditText titleone,description,actualprice,realprice,Elcquantity;

        FirebaseDatabase.getInstance().getReference()
                .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        uploadcloths(id);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void uploadcloths(String ts) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", url1);///
        map.put("catagory", "cloths");////
        map.put("Title", titleone.getText().toString().trim());///
        map.put("realprice", realprice.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("cloths")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        selfupload("cloths");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void selfupload(String ts) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", url1);///
        map.put("catagory", ts);////
        map.put("IDfordeleteproduct", id);////
        map.put("Title", titleone.getText().toString());///
        map.put("realprice",realprice.getText().toString());///

        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("userproducts")
                .child(id)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(owner_can_edit_data_here.this, MainActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(owner_can_edit_data_here.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(owner_can_edit_data_here.this, shopMainActivity.class));
        finish();
    }
}