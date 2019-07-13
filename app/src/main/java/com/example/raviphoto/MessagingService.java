package com.example.raviphoto;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(String message,String title)
{
    PendingIntent pi=PendingIntent.getActivity(this,0,new Intent(this,Splash_Screen.class),0);
    NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"My Notification")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pi)
            .setAutoCancel(true);


    NotificationManagerCompat notificationManager= NotificationManagerCompat.from(this);
    notificationManager.notify(999,builder.build());
}
}