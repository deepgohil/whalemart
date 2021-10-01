package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class homeTry extends AppCompatActivity {
    String title,id,price,url,size,desc,paymentType;
    ImageView productimage;
    RadioGroup radioGroup;
    RadioButton radiobtn;
    TextView producttitle,productprice,psize,productdesc,priceforbuy,plus,totalPrice;
    LinearLayout delevery;
    Button confirm;
    Long tsLong;
    EditText address;
    String ts,ordetime;
    int myNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_try);
        title=getIntent().getStringExtra("title");
        id=getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");
        url=getIntent().getStringExtra("url1");
//        size=getIntent().getStringExtra("size");
        desc=getIntent().getStringExtra("desc");
        radioGroup=findViewById(R.id.radioGroup);

        productimage=findViewById(R.id.productimage);
        priceforbuy=findViewById(R.id.priceforbuy);
        producttitle=findViewById(R.id.producttitle);
        productprice=findViewById(R.id.productprice);
//        psize=findViewById(R.id.psize);
        productdesc=findViewById(R.id.productdesc);
        ///////////////////////data to hide
        plus=findViewById(R.id.plus);
        delevery=findViewById(R.id.delevery);
        /////////////////////Totalprice
        totalPrice=findViewById(R.id.totalPrice);
        address=findViewById(R.id.address);



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
                    if (radiobtn.getText().toString().equals("Cash on delivery"))
                    {
                        processCODorder();
                    }
            }
        });
        tsLong = System.currentTimeMillis()/1000;
        ts= tsLong.toString();


        ordetime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


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
        map.put("size","");
        map.put("price",totalPrice.getText().toString());
        map.put("desc",desc);




        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("homeTry")
                .push()
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
}