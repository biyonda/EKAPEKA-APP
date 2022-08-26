package com.diskominfo.ekpkdisnaker.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.diskominfo.ekpkdisnaker.R;

public class NotificationHelper {

    public static void displatNotification(Context context, String title, String body) {

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                100,
                intent,
                0
        );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, "EKPK_DISNAKER")
                        .setSmallIcon(R.drawable.logo_jember)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(context);
        notifManager.notify(1, mBuilder.build());

    }

}
