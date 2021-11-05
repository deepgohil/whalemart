package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;

public class Videocall_outgoing extends AppCompatActivity {
ImageView accept,decline;
    String name,title,size,price,desc,shop_id,token,receiver_name,UID;
    String urlproduct;
    String key;
    String response;

    String  id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocall_outgoing);

        accept=findViewById(R.id.accept);
        decline=findViewById(R.id.decline);


//        Bundle bundle=getIntent().getExtras();
//        if(bundle!=null) {
            urlproduct = getIntent().getStringExtra("url1");
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            size = getIntent().getStringExtra("size");
            price = getIntent().getStringExtra("price");
            desc = getIntent().getStringExtra("desc");
            token = getIntent().getStringExtra("token");
            shop_id = getIntent().getStringExtra("shop_id");

        UID=FirebaseAuth.getInstance().getUid();

        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(shop_id)
                .child("incominguid")
                .setValue(UID)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        setvaluefirst("true");
//
//        FcmNotificationsSender notificationsSender=new FcmNotificationsSender("e6xRaVVuSL-s0fcJqkBR5T:APA91bELh2pdd8WevZ3kYYCgn07tZ0fzszpEMAitK_bTDTa77HKgZG_Ameu3ORGE-36KH12BMmSYC5w5TAkCQFpxOY_hvy19reTug4oZnZZyDqbebC4HVcnmW4G40z0GwImIl4_uD9qC","V",UID,getApplicationContext(),Videocall_outgoing.this);
//        notificationsSender.SendNotifications();

//        FirebaseDatabase.getInstance().getReference().child("User").child(shop_id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                receiver_name=snapshot.child("name").getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Videocall_outgoing.this,error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkresponce();

            }
        },25000);

//        else {
//            Toast.makeText(Videocall_outgoing.this,"ERROR", Toast.LENGTH_SHORT).show();
//        }

    }

    private void checkresponce() {
        FirebaseDatabase.getInstance().getReference().child("vref").child(UID).child(shop_id).child("res")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 key= dataSnapshot.child("key").getValue(String.class).toString();
                 response= dataSnapshot.child("response").getValue(String.class).toString();
                        
if(response.equals("yes"))
{

            Toast.makeText(Videocall_outgoing.this,"CALL ACCEPTED  "+response, Toast.LENGTH_SHORT).show();
             setvalue("false");
//            Toast.makeText(Videocall_outgoing.this,response, Toast.LENGTH_SHORT).show();
    joinmeeting(key);
}
else if(response.equals("no"))
{
    Toast.makeText(Videocall_outgoing.this,"CAN'T CONNECT", Toast.LENGTH_SHORT).show();
    setvalue("false");
    Intent intent = new Intent(Videocall_outgoing.this, MainActivity.class);
    startActivity(intent);
    finish();
}
else {
    Toast.makeText(Videocall_outgoing.this,"ERROR", Toast.LENGTH_SHORT).show();
    setvalue("false");

}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Videocall_outgoing.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




    }



    private void joinmeeting(String key) {

        try {
            JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setRoom(key)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeetActivity.launch(Videocall_outgoing.this,options);
            finish();

        }
        catch (Exception e)
        {

            Toast.makeText(Videocall_outgoing.this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    private void setvaluefirst(String s) {




                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(shop_id)
                        .child("call")
                        .setValue(s)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });








    }
    private void setvalue(String s) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(shop_id)
                        .child("call")
                        .setValue(s)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        },25000);





    }
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(shop_id)
                .child("call")
                .setValue("false")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(Videocall_outgoing.this, MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }
//    private void sendcallinvitation() {
//
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FcmNotificationsSender notificationsSender=new FcmNotificationsSender(token,"V",UID,getApplicationContext(),Videocall_outgoing.this);
//                notificationsSender.SendNotifications();
//
//
//            }
//        },3000);
//    }
}