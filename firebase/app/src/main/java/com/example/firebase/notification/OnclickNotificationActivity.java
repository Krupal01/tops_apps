package com.example.firebase.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityOnclickNotificationBinding;

public class OnclickNotificationActivity extends AppCompatActivity {

    private ActivityOnclickNotificationBinding binding;
    private String CHANNEL_ID = "channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnclickNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.SendNotification.setOnClickListener(v -> {

            String KEY_TEXT_REPLY = "key_text_reply";
            String replyLabel = "reply";
            RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                    .setLabel(replyLabel)
                    .build();

            PendingIntent replyPendingIntent =
                    PendingIntent.getBroadcast(getApplicationContext(),
                            0,
                            new Intent(this,OnclickNotificationActivity.class),
                            PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(R.drawable.ic_baseline_reply_24, "hii", replyPendingIntent)
                            .addRemoteInput(remoteInput)
                            .build();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My notification", importance);
                channel.setDescription("hiiiiiiiiiiiiiiiii");
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this,CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_baseline_school_24)
                            .setContentTitle("Notifications Example")
                            .addAction(action)
                            .setContentText("This is a test notification");

            Intent notificationIntent = new Intent(this, OnclickNotificationActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        });
    }

}