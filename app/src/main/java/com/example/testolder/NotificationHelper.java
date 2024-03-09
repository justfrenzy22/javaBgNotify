package com.example.testolder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationHelper extends AppCompatActivity {
    public static void makeNotification(Context context, int checkValue) {
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder bld = new NotificationCompat.Builder(context, channelID);

        int notifyId = generateNotifyId();

        Uri soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm);

        bld
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle("Notification Title")
                .setContentText("Check: " + checkValue)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(soundUri);

        Intent intent = new Intent(context, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "some value for the name");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE);
        bld.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelID);

            if (channel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                channel = new NotificationChannel(channelID, "Some text", importance);
                channel.setLightColor(Color.GREEN);
                channel.enableVibration(true);
                manager.createNotificationChannel(channel);
            }
        }

        manager.notify(notifyId, bld.build());


    }

    private static int generateNotifyId () {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        return Integer.parseInt(timestamp.hashCode() + "");
    }
}