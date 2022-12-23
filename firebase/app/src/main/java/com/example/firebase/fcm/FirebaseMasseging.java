package com.example.firebase.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.firebase.MainActivity;
import com.example.firebase.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FirebaseMasseging extends FirebaseMessagingService {

    private String OTP;
    private static final String CHANNEL_ID = "Firebase Messaging";


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("Token", token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("form", "From : "+remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            this.OTP = remoteMessage.getNotification().getBody().split(": ")[1];
            Log.i("OTP",OTP );
            Log.i("message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showMyNotification(remoteMessage.getNotification());
        }
    }

    private void showMyNotification(RemoteMessage.Notification notification) {
        Bundle bundle = new Bundle();
        if (OTP != null && !OTP.isEmpty()) {
            bundle.putString("otp", OTP);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);



        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        if(notification.getImageUrl()!=null){
            try {
                URL url=new URL(notification.getImageUrl().toString());
                Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
                builder.setLargeIcon(bitmap)        // when collapse
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap)
                                .bigLargeIcon(null));       // when expland
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Notification mNotification = builder.build();

        NotificationManager manager=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            manager=getSystemService(NotificationManager.class);
        }else{
            manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "FCM Channel", importance);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(),mNotification);

    }


}
