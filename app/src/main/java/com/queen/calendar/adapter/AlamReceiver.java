package com.queen.calendar.adapter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.queen.calendar.R;

import androidx.core.app.NotificationCompat;

import com.queen.calendar.ui.TimeActivity;

public class AlamReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "SAMPLE_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get id & message from intent

        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        //call TimeActivity when notification is tapped
        Intent timeIntent = new Intent(context, TimeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context,0,timeIntent,0
        );

        //NotificationManager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //for API 26 and above
            CharSequence name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(channel);
        }

        //Prepare notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.kawali)
                .setContentTitle("Reminder!")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        //notify
        notificationManager.notify(notificationId, builder.build());

//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(0,builder.build());
    }
}
