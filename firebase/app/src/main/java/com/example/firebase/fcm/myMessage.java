package com.example.firebase.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.firebase.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class myMessage extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull  String s) {
        super.onNewToken(s);
        Log.i("Token1",s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("from1",remoteMessage.getFrom());
        if (remoteMessage.getNotification()!=null){
            Log.i("message1",remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification());
        }
    }

    private void showNotification(RemoteMessage.Notification notification) {

        Intent intent = new Intent(this, FcmTargetActivity.class);
        PendingIntent intent1 = PendingIntent.getActivity(this,0,intent,0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"massage")
                .setContentText(notification.getTitle())
                .setContentText(notification.getBody())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(intent1);

        Notification notification1 = builder.build();

        NotificationManager manager=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            manager=getSystemService(NotificationManager.class);
        }else{
            manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("myMessage", "FCM Channel", importance);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(),notification1);

    }
}
