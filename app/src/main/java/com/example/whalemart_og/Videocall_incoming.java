package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import java.util.HashMap;

public class Videocall_incoming extends AppCompatActivity {
    String sender_uid, receiver_uid;
    ImageView accept, decline;
    VCmodel model;
    String ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocall_incoming);

//        setvaluefirst("true");

        accept = findViewById(R.id.accept);
        decline = findViewById(R.id.decline);

        sender_uid = getIntent().getStringExtra("UID");
        model = new VCmodel();
        receiver_uid = FirebaseAuth.getInstance().getUid();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(Videocall_incoming.this, notification);
        r.play();


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.stop();
                String response = "yes";
                removetrue();
//                setvalue("true");
                setResponse(response);
            }
        });


        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.stop();
                String response = "no";
                removetrueinstant();
//                setvalue("false");
                setResponse(response);

            }
        });



    }

    private void removetrueinstant() {
        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(FirebaseAuth.getInstance().getUid())
                .child("call")
                .setValue("false")
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

    private void removetrue() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("call")
                        .setValue("false")
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
        },20000);

    }

//    private void setvalue(String s) {
//        FirebaseDatabase.getInstance().getReference()
//                .child("User")
//                .child(FirebaseAuth.getInstance().getUid())
//                .child("call")
//                .setValue(s)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
//
//
//
//
//
//    }
public void onBackPressed() {
    super.onBackPressed();
    FirebaseDatabase.getInstance().getReference()
            .child("User")
            .child(FirebaseAuth.getInstance().getUid())
            .child("call")
            .setValue("false")
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

    startActivity(new Intent(Videocall_incoming.this, MainActivity.class));
    finish();
}

    private void setResponse(String response)
    {
if(response.equals("yes"))
{
    Long tsLong = System.currentTimeMillis()/1000;
    ts= tsLong.toString();
    HashMap<String, Object> map = new HashMap<>();
    map.put("key",ts);
    map.put("response", "yes");
    FirebaseDatabase.getInstance().getReference().child("vref").child(sender_uid).child(receiver_uid).child("res")
            .setValue(map) .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Toast.makeText(Videocall_incoming.this,"STARTING CALL", Toast.LENGTH_SHORT).show();

            joinmeeting();

        }
    })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

    Handler handler=new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {

            Long tsLong = System.currentTimeMillis()/1000;
            ts= tsLong.toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("key",ts);
            map.put("response", "no");
            FirebaseDatabase.getInstance().getReference().child("vref").child(sender_uid).child(receiver_uid).child("res")
                    .setValue(map) .addOnSuccessListener(new OnSuccessListener<Void>() {
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
    },26000);
}
else if(response.equals("no"))
{
    Long tsLong = System.currentTimeMillis()/1000;
    ts= tsLong.toString();
    HashMap<String, Object> map = new HashMap<>();
    map.put("key",ts);
    map.put("response", "no");
    FirebaseDatabase.getInstance().getReference().child("vref").child(sender_uid).child(receiver_uid).child("res")
            .setValue(map) .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Toast.makeText(Videocall_incoming.this,"CALL END", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Videocall_incoming.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
    Handler handler=new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {

            Long tsLong = System.currentTimeMillis()/1000;
            ts= tsLong.toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("key",ts);
            map.put("response", "no");
            FirebaseDatabase.getInstance().getReference().child("vref").child(sender_uid).child(receiver_uid).child("res")
                    .setValue(map) .addOnSuccessListener(new OnSuccessListener<Void>() {
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
    },26000);
}
}

    private void joinmeeting() {

        try {
            JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setRoom(ts)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeetActivity.launch(Videocall_incoming.this,options);
            finish();

        }
        catch (Exception e)
        {

            Toast.makeText(Videocall_incoming.this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

}