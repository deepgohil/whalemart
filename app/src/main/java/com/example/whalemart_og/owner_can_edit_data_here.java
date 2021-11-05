package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class owner_can_edit_data_here extends AppCompatActivity {
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

        id = getIntent().getStringExtra("id");
        url1 = getIntent().getStringExtra("url1");
        price = getIntent().getStringExtra("price");
        catagory = getIntent().getStringExtra("catagory");
        title = getIntent().getStringExtra("title");
//////////////////////////////image
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(owner_can_edit_data_here.this, shopMainActivity.class));
        finish();
    }
}