package com.example.whalemart_og;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    NotificationManager mNotificationManager;
    MediaPlayer mp;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();

        if(title.equals("Video call"))
        {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                r.setLooping(false);
            }

            // vibration
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {100, 300, 300, 300};
            v.vibrate(pattern, -1);


            int resourceImage = getResources().getIdentifier(remoteMessage.getNotification().getIcon(), "drawable", getPackageName());



            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder.setSmallIcon(R.drawable.icontrans);
                builder.setSmallIcon(resourceImage);
            } else {
//            builder.setSmallIcon(R.drawable.icon_kritikar);
                builder.setSmallIcon(resourceImage);
            }



            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setContentIntent(pendingIntent);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));
            builder.setAutoCancel(true);
            builder.setPriority(Notification.PRIORITY_MAX);

            mNotificationManager =
                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);




            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }



// notificationId is a unique int for each notification that you must define
            mNotificationManager.notify(100, builder.build());
//            startcall(body);

        }
        else
        {

// playing audio and vibration when user se reques
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                r.setLooping(false);
            }

            // vibration
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {100, 300, 300, 300};
            v.vibrate(pattern, -1);


            int resourceImage = getResources().getIdentifier(remoteMessage.getNotification().getIcon(), "drawable", getPackageName());



            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder.setSmallIcon(R.drawable.icontrans);
                builder.setSmallIcon(resourceImage);
            } else {
//            builder.setSmallIcon(R.drawable.icon_kritikar);
                builder.setSmallIcon(resourceImage);
            }



            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setContentIntent(pendingIntent);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));
            builder.setAutoCancel(true);
            builder.setPriority(Notification.PRIORITY_MAX);

            mNotificationManager =
                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);




            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }



// notificationId is a unique int for each notification that you must define
            mNotificationManager.notify(100, builder.build());

        }


    }

//    private void startcall(String body) {
//        Intent intent = new Intent(this, Videocall_incoming.class);
//        intent.putExtra("UID",body);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
////        ((Activity)getBaseContext()).finish();
    }




