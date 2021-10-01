package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class walkTry extends AppCompatActivity {
    ImageView imgproduct;
    TextView shopname,description,openmap,currentTime,orderId;
    String urlproduct;
    Button confirm;
    String ts,ordetime;
    Long tsLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_try);
        imgproduct=findViewById(R.id.imgproduct);
        description=findViewById(R.id.description);
        currentTime=findViewById(R.id.currentTime);
        openmap=findViewById(R.id.openmap);
        orderId=findViewById(R.id.orderId);
       shopname=findViewById(R.id.shopname);
       urlproduct=getIntent().getStringExtra("url1");
        Picasso.get().load(urlproduct).into(imgproduct);

        tsLong = System.currentTimeMillis()/1000;
         ts= tsLong.toString();
        orderId.setText(ts);

      ordetime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        currentTime.setText(ordetime);

        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processOrder();

            }
        });

    }

    private void processOrder() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("shopname",shopname.getText().toString());
        map.put("address",description.getText().toString());
        map.put("time",ordetime);
        map.put("ID",ts);
        map.put("sAddress","comming soon");
        map.put("dAddress","comming soon");
        map.put("productId","");
        map.put("imageurl",urlproduct);

        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("walkTry")
                .push()
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(walkTry.this, MainActivity.class));
        finish();
    }
}