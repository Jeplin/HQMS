package com.jeplin.hackathonqm.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jeplin.hackathonqm.MainActivity;

/**
 * Created by jeplin on 23-09-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivities(this,0, new Intent[]{intent},PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificatioBuilder=new NotificationCompat.Builder(this);
        notificatioBuilder.setContentTitle("HQMS");
        notificatioBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificatioBuilder.setAutoCancel(true);
        //notificatioBuilder.setSmallIcon();
        notificatioBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificatioBuilder.build());
    }
}
