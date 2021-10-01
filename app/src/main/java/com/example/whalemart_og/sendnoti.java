package com.example.whalemart_og;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class sendnoti extends AppCompatActivity {
TextView title,body;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnoti);
        title=findViewById(R.id.title);
        body=findViewById(R.id.body);
        send=findViewById(R.id.send);
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",title.getText().toString(),body.getText().toString(),getApplicationContext(),sendnoti.this);
                notificationsSender.SendNotifications();
            }
        });
    }
}